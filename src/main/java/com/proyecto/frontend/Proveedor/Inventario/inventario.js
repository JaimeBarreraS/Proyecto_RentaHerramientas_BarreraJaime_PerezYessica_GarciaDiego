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

    const herramientasImagenes = {
        "Destornillador": "Imagenes/destornillador.jpg",
        "Pala": "Imagenes/pala.jpg",
        "Cortadora": "Imagenes/cortadora.jpg",
        "Lijadora": "Imagenes/lijadora.jpg",
        "Pulidora": "Imagenes/pulidora.jpg",
        "Andamio": "Imagenes/andamio.jpg",
        "Cinta metrica": "Imagenes/cinta_metrica.jpg",
        "Taladro Percutor": "Imagenes/taladro_percutor.jpg",
        "Escalera": "Imagenes/escalera.jpg",
        "Nivel": "Imagenes/nivel.jpg",
        "Rotomartillo": "Imagenes/rotomartillo.jpg",
        "Compresor": "Imagenes/compresor.jpg",
        "Soldador": "Imagenes/soldador.jpg",
        "Generador": "Imagenes/generador.jpg",
        "Compactadora": "Imagenes/compactadora.jpg",
        "Broca": "Imagenes/broca.jpg",
        "Multimetro": "Imagenes/multimetro.jpg",
        "Cepillo electrico": "Imagenes/cepillo_electrico.jpg",
        "Sierra circular": "Imagenes/sierra_circular.jpg",
        "Aspiradora industrial": "Imagenes/aspiradora.jpg"
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
                    <span class="nombre">${herramienta.nombre}</span> <br> 
                    <span class="descripcion">${herramienta.descripcion}</span> <br> 
                    <span class="precio">$${herramienta.precioPorDia} / día</span>
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
                    <img src="${herramienta.imagenUrl || 'Imagenes/default.jpg'}" class="foto">
                    <p class="info">
                        <span class="nombre">${herramienta.nombre}</span> <br> 
                        <span class="descripcion">${herramienta.descripcion}</span> <br> 
                        <span class="precio">$${herramienta.precioPorDia} / día</span>
                    </p>
                `;
                herramientaDiv.addEventListener("click", () => actualizarCard(herramienta));
                contenedor.appendChild(herramientaDiv);
            });
    }
    function actualizarCard(herramienta) {
        const cardContent = `
            
            <div class='xr'>
            <p><strong>Nombre:</strong> <input type="text" id="edit-nombre" value="${herramienta.nombre}"></p>
            <p><strong>Descripción:</strong> <input type="text" id="edit-descripcion" value="${herramienta.descripcion}"></p>
            <p><strong>Precio por Día:</strong> <input type="number" id="edit-precio" value="${herramienta.precioPorDia}"></p>
            <button id="create-btn">➕ Crear</button>
            <button id="clear-btn">🗑️ Vaciar</button>
            <button id="update-btn">✏️ Actualizar</button>
            <button id="delete-btn">❌ Eliminar</button>
            </div>
        `;

        document.querySelector(".card-text").innerHTML = cardContent;

        document.getElementById("update-btn").addEventListener("click", () => actualizarHerramienta(herramienta));
        document.getElementById("delete-btn").addEventListener("click", () => eliminarHerramienta(herramienta.id));
        document.getElementById("clear-btn").addEventListener("click", vaciarCampos);
    }

    async function actualizarHerramienta(herramienta) {
        const nuevoNombre = document.getElementById("edit-nombre").value.trim();
        const nuevaDescripcion = document.getElementById("edit-descripcion").value.trim();
        const nuevoPrecio = document.getElementById("edit-precio").value.trim();

        try {
            const response = await fetch(`${API_HERRAMIENTAS}/${herramienta.id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${usuarioInfo.token}`,
                },
                body: JSON.stringify({ nombre: nuevoNombre, descripcion: nuevaDescripcion, precioPorDia: nuevoPrecio }),
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
        document.getElementById("edit-descripcion").value = "";
        document.getElementById("edit-precio").value = "";
    }
});










