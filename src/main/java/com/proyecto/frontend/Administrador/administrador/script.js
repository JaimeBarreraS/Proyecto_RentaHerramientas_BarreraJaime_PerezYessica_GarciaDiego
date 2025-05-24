document.addEventListener("DOMContentLoaded", function() {
    const menuBtn = document.querySelector(".menu-btn");
    const sidebar = document.querySelector(".sidebar");

    menuBtn.addEventListener("click", function(event) {
        sidebar.classList.toggle("show");
        event.stopPropagation(); // Evita que el clic se propague al documento
    });

    document.addEventListener("click", function(event) {
        // Si el clic es fuera de la sidebar y el menú está abierto, se oculta
        if (!sidebar.contains(event.target) && sidebar.classList.contains("show")) {
            sidebar.classList.remove("show");
        }
    });
});



document.addEventListener("DOMContentLoaded", async () => {
    const API_USERS = "http://localhost:8080/api/usuarios";
    const usuarioInfo = {
        nombre: localStorage.getItem("nombre"),
        role: localStorage.getItem("role"),
        token: localStorage.getItem("token"),
    }; 

    document.getElementById("ad").innerText = usuarioInfo.role || "Rol Desconocido";

    await cargarUsuarios(); 

    async function cargarUsuarios() {
        try {
            const response = await fetch(API_USERS, {
                method: "GET",
                headers: { "Authorization": `Bearer ${usuarioInfo.token}` },
            });

            if (!response.ok) throw new Error("Error al obtener usuarios");

            const usuarios = await response.json();
            mostrarUsuarios(usuarios);
        } catch (error) {
            console.error("Error al cargar usuarios:", error);
        }
    }

    function mostrarUsuarios(usuarios) {
        
        const contenedorUsuarios = document.getElementById("ima");
        contenedorUsuarios.innerHTML = "";

        usuarios.forEach((usuario) => {
            const userDiv = document.createElement("div");
            userDiv.className = "user-box";
            userDiv.dataset.id = usuario.id; 
            userDiv.innerHTML = `
                <img src="Imagenes/image 79.png" class="foto">
                <p class="info">
                    <span class="nombre">${usuario.nombre}</span> <br> 
                    <span class="role">${usuario.role}</span> <br> 
                    <span class="email">${usuario.email}</span> <br> 
                    <span class="telefono">${usuario.telefono}</span>
                </p>
            `;
            userDiv.addEventListener("click", () => actualizarCard(usuario));
            contenedorUsuarios.appendChild(userDiv);
        });

        document.getElementById("cu1").addEventListener("click", () => filtrarUsuarios(usuarios, "PROVEEDOR"));
        document.getElementById("cu2").addEventListener("click", () => filtrarUsuarios(usuarios, "CLIENTE"));
    }

    function filtrarUsuarios(usuarios, role) {
        const contenedorUsuarios = document.getElementById("ima");
        contenedorUsuarios.innerHTML = "";
        usuarios
            .filter(usuario => usuario.role === role)
            .forEach((usuario) => {
                const userDiv = document.createElement("div");
                userDiv.className = "user-box";
                userDiv.innerHTML = `
                    <img src="Imagenes/image 79.png" class="foto">
                    <p class="info">
                        <span class="nombre">${usuario.nombre}</span> <br> 
                        <span class="role">${usuario.role}</span> <br> 
                        <span class="email">${usuario.email}</span> <br> 
                        <span class="telefono">${usuario.telefono}</span>
                    </p>
                `;
                userDiv.addEventListener("click", () => actualizarCard(usuario));
                contenedorUsuarios.appendChild(userDiv);
            });
    }

    function actualizarCard(usuario) {
        const cardContent = `
            <img src="Imagenes/image 79.png" class="card-img-top" alt="...">
            <div class='xr'>
            <p>
                <strong>Nombre:</strong> 
                <input type="text" id="edit-nombre" value="${usuario.nombre}">
            </p>
            <p>
                <strong>Rol:</strong> 
                <select id="edit-role">
                    <option value="CLIENTE" ${usuario.role === "CLIENTE" ? "selected" : ""}>Cliente</option>
                    <option value="PROVEEDOR" ${usuario.role === "PROVEEDOR" ? "selected" : ""}>Proveedor</option>
                    <option value="ADMIN" ${usuario.role === "ADMIN" ? "selected" : ""}>Administrador</option>
                </select>
            </p>
            <p><strong>Email:</strong> <input type="email" id="edit-email" value="${usuario.email}"></p>
            <p><strong>Teléfono:</strong> <input type="text" id="edit-telefono" value="${usuario.telefono}"></p>
            <button id="update-btn">Actualizar</button>
            <button id="delete-btn">Eliminar</button>
            </div>
        `;

        document.querySelector(".card-text").innerHTML = cardContent;

        document.getElementById("update-btn").addEventListener("click", () => actualizarUsuario(usuario));
        document.getElementById("delete-btn").addEventListener("click", () => eliminarUsuario(usuario.id));
    }

    async function actualizarUsuario(usuario) {
        const nuevoNombre = document.getElementById("edit-nombre").value.trim();
        const nuevoEmail = document.getElementById("edit-email").value.trim();
        const nuevoTelefono = document.getElementById("edit-telefono").value.trim();
        const nuevoRol = document.getElementById("edit-role").value;

        try {
            const response = await fetch(`${API_USERS}/${usuario.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${usuarioInfo.token}`,
                },
                body: JSON.stringify({ nombre: nuevoNombre, email: nuevoEmail, telefono: nuevoTelefono, role: nuevoRol }),
            });

            if (response.ok) {
                alert("Usuario actualizado correctamente.");

                usuario.nombre = nuevoNombre;
                usuario.email = nuevoEmail;
                usuario.telefono = nuevoTelefono;
                usuario.role = nuevoRol;

                mostrarUsuarios(await obtenerUsuarios()); 
            } else {
                alert("Error al actualizar usuario.");
            }
        } catch (error) {
            console.error("Error al actualizar usuario:", error);
        }
    }

    async function eliminarUsuario(id) {
        if (confirm("¿Estás seguro que deseas eliminar este usuario?")) {
            try {
                const response = await fetch(`${API_USERS}/${id}`, {
                    method: "DELETE",
                    headers: { "Authorization": `Bearer ${usuarioInfo.token}` },
                });

                if (response.ok) {
                    alert("Usuario eliminado correctamente.");
                    mostrarUsuarios(await obtenerUsuarios()); 
                } else {
                    alert("Error al eliminar usuario.");
                }
            } catch (error) {
                console.error("Error de conexión al eliminar usuario:", error);
            }
        }
    }

    async function obtenerUsuarios() {
        try {
            const response = await fetch(API_USERS, {
                method: "GET",
                headers: { "Authorization": `Bearer ${usuarioInfo.token}` },
            });

            if (!response.ok) throw new Error("Error al obtener usuarios");

            return await response.json();
        } catch (error) {
            console.error("Error al obtener usuarios:", error);
            return [];
        }
    }

    document.getElementById("buscador1").addEventListener("input", function() {
        const filtro = this.value.toLowerCase(); 
        const usuarios = document.querySelectorAll(".user-box"); 
    
        let encontrados = false; 
        usuarios.forEach(usuario => {
            const nombreUsuario = usuario.querySelector(".nombre").innerText.toLowerCase();
    
            if (nombreUsuario.includes(filtro)) {
                usuario.style.display = "block";
                encontrados = true;
            } else {
                usuario.style.display = "none"; 
            }
        });
    
        if (!encontrados) {
            document.getElementById("ima").innerHTML = "<p>No se encontraron usuarios</p>";
        }
    });
});








