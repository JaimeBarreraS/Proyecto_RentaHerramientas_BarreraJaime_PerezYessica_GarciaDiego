document.addEventListener("DOMContentLoaded", function() {
    const menuBtn = document.querySelector(".menu-btn");
    const sidebar = document.querySelector(".sidebar");

    menuBtn.addEventListener("click", function(event) {
        sidebar.classList.toggle("show");
        event.stopPropagation(); 
    });

    document.addEventListener("click", function(event) {
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
});  

document.addEventListener("DOMContentLoaded", async () => {
    const API_HERRAMIENTAS = "http://localhost:8080/api/herramientas";
    const usuarioInfo = {
        nombre: localStorage.getItem("nombre"),
        role: localStorage.getItem("role"),
        token: localStorage.getItem("token"),
    };

    await cargarHerramientas();

    async function cargarHerramientas() {
        try {
            const response = await fetch(API_HERRAMIENTAS, {
                method: "GET",
                headers: { "Authorization": `Bearer ${usuarioInfo.token}` },
            });

            if (!response.ok) throw new Error("Error al obtener herramientas");

            const herramientas = await response.json();
            mostrarHerramientas(herramientas);
        } catch (error) {
            console.error("Error al cargar herramientas:", error);
        }
    }

    function mostrarHerramientas(herramientas) {
        const contenedor = document.getElementById("ima");
        contenedor.innerHTML = "";

        herramientas.forEach((herramienta) => {
            const herramientaDiv = document.createElement("div");
            herramientaDiv.className = "herramienta-box";
            herramientaDiv.dataset.id = herramienta.id;
            herramientaDiv.innerHTML = `
                
                <p class="info">
                    <img src="${herramienta.imagenUrl}" class="foto"><br>
                    <span class="nombre">${herramienta.nombre}</span> <br> 
                    <span class="categoria">${herramienta.categoria}</span> <br>
                    <span class="descripcion">${herramienta.descripcion}</span> <br> 
                    <span class="precio">$${herramienta.precioPorDia} / día</span> <br> 
                    <span class="proveedor">${herramienta.proveedorNombre}</span> <br>
                </p>
            `;
            herramientaDiv.addEventListener("click", () => actualizarCard(herramienta));
            contenedor.appendChild(herramientaDiv);
        });

        document.getElementById("cu1").addEventListener("click", () => filtrarHerramientas(herramientas, "PROVEEDOR"));
        document.getElementById("cu2").addEventListener("click", () => filtrarHerramientas(herramientas, "CLIENTE"));
    }

    function filtrarHerramientas(herramientas, role) {
        const contenedor = document.getElementById("ima");
        contenedor.innerHTML = "";

        herramientas.filter(herramienta => herramienta.proveedorRole === role)
            .forEach((herramienta) => {
                const herramientaDiv = document.createElement("div");
                herramientaDiv.className = "herramienta-box";
                herramientaDiv.innerHTML = `
                    <img src="${herramienta.imagenUrl}" class="foto">
                    <p class="info">
                        <img src="${herramienta.imagenUrl}" class="foto"><br>
                        <span class="nombre">${herramienta.nombre}</span> <br> 
                        <span class="categoria">${herramienta.categoria}</span> <br>
                        <span class="descripcion">${herramienta.descripcion}</span> <br> 
                        <span class="precio">$${herramienta.precioPorDia} / día</span>
                        <span class="proveedor">${herramienta.proveedorNombre}</span> <br>
                    </p>
                `;
                herramientaDiv.addEventListener("click", () => actualizarCard(herramienta));
                contenedor.appendChild(herramientaDiv);
            });
    }
    function actualizarCard(herramienta) {
        const cardContent = `
            <img src="${herramienta.imagenUrl}" class="foto2">
            <div class='xr'>
                <p><strong>Nombre:</strong> <input type="text" id="edit-nombre" value="${herramienta.nombre}"></p>
                <p><strong>Categoria:</strong> <input type="text" id="edit-categoria" value="${herramienta.categoria}"></p>
                <p><strong>Descripción:</strong> <input type="text" id="edit-descripcion" value="${herramienta.descripcion}"></p>
                <p><strong>Precio por Día:</strong> <input type="number" id="edit-precio" value="${herramienta.precioPorDia}"></p>
                <p><strong>URL Imagen:</strong> <input type="text" id="edit-imagenUrl" value="${herramienta.imagenUrl}"></p>
                <button id="create-btn">➕</button>
                <button id="clear-btn">🗑️</button>
                <button id="update-btn">✏️</button>
                <button id="delete-btn">❌</button>
            </div>
        `;
    
        document.querySelector(".card-text").innerHTML = cardContent;
    
        document.getElementById("update-btn").addEventListener("click", () => actualizarHerramienta(herramienta));
        document.getElementById("create-btn").addEventListener("click", crearHerramienta);
        document.getElementById("delete-btn").addEventListener("click", () => eliminarHerramienta(herramienta.id));
        document.getElementById("clear-btn").addEventListener("click", vaciarCampos);
    }
    

    async function crearHerramienta() {
        const nuevoNombre = document.getElementById("edit-nombre").value.trim();
        const nuevaCategoria = document.getElementById("edit-categoria").value.trim();
        const nuevaDescripcion = document.getElementById("edit-descripcion").value.trim();
        const nuevoPrecio = document.getElementById("edit-precio").value.trim();
        const nuevaImagenUrl = document.getElementById("edit-imagenUrl").value.trim();
        try {
            const response = await fetch(API_HERRAMIENTAS, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${usuarioInfo.token}`,
                },
                body: JSON.stringify({
                    nombre: nuevoNombre,
                    categoria: nuevaCategoria,
                    descripcion: nuevaDescripcion,
                    precioPorDia: nuevoPrecio,
                    imagenUrl: nuevaImagenUrl
                }),
            });
    
            if (response.ok) {
                alert("Herramienta creada correctamente.");
                mostrarHerramientas(await obtenerHerramientas());
            } else {
                alert("Error al crear herramienta.");
            }
        } catch (error) {
            console.error("Error al crear herramienta:", error);
        }
    }

    async function actualizarHerramienta(herramienta) {
        const nuevoNombre = document.getElementById("edit-nombre").value.trim();
        const nuevaCategoria = document.getElementById("edit-categoria").value.trim();
        const nuevaDescripcion = document.getElementById("edit-descripcion").value.trim();
        const nuevoPrecio = document.getElementById("edit-precio").value.trim();
        const nuevaImagenUrl = document.getElementById("edit-imagenUrl").value.trim();
    
        try {
            const response = await fetch(`${API_HERRAMIENTAS}/${herramienta.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${usuarioInfo.token}`,
                },
                body: JSON.stringify({
                    nombre: nuevoNombre,
                    categoria: nuevaCategoria,
                    descripcion: nuevaDescripcion,
                    precioPorDia: nuevoPrecio,
                    imagenUrl: nuevaImagenUrl
                }),
            });
    
            if (response.ok) {
                alert("Herramienta actualizada correctamente.");
                mostrarHerramientas(await obtenerHerramientas());
            } else {
                alert("Error al actualizar herramienta.");
            }
        } catch (error) {
            console.error("Error al actualizar herramienta:", error);
        }
    }
    

    async function eliminarHerramienta(id) {
        if (confirm("¿Estás seguro que deseas eliminar esta herramienta?")) {
            try {
                const response = await fetch(`${API_HERRAMIENTAS}/${id}`, {
                    method: "DELETE",
                    headers: { "Authorization": `Bearer ${usuarioInfo.token}` },
                });

                if (response.ok) {
                    alert("Herramienta eliminada correctamente.");
                    mostrarHerramientas(await obtenerHerramientas());
                } else {
                    alert("Error al eliminar herramienta.");
                }
            } catch (error) {
                console.error("Error al eliminar herramienta:", error);
            }
        }
    }

    async function obtenerHerramientas() {
        try {
            const response = await fetch(API_HERRAMIENTAS, {
                method: "GET",
                headers: { "Authorization": `Bearer ${usuarioInfo.token}` },
            });

            if (!response.ok) throw new Error("Error al obtener herramientas");

            return await response.json();
        } catch (error) {
            console.error("Error al obtener herramientas:", error);
            return [];
        }
    }
    console.log("Token en la solicitud PUT:", usuarioInfo.token);


    document.getElementById("buscador1").addEventListener("input", function() {
        const filtro = this.value.toLowerCase(); 
        const herramientas = document.querySelectorAll(".herramienta-box"); 
    
        let encontrados = false; 
        herramientas.forEach(herramienta => {
            const nombreHerramienta = herramienta.querySelector(".nombre").innerText.toLowerCase();
    
            if (nombreHerramienta.includes(filtro)) {
                herramienta.style.display = "block";
                encontrados = true;
            } else {
                herramienta.style.display = "none"; 
            }
        });
    
        if (!encontrados) {
            document.getElementById("ima").innerHTML = "<p>No se encontraron herramientas</p>";
        }
    });
    

    function vaciarCampos() {
        document.getElementById("edit-nombre").value = "";
        document.getElementById("edit-categoria").value = "";
        document.getElementById("edit-descripcion").value = "";
        document.getElementById("edit-precio").value = "";
        document.getElementById("edit-imagenUrl").value = "";
    }
});










