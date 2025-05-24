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
    const API_RESERVAS = "http://localhost:8080/api/reservas";
    const usuarioInfo = {
        token: localStorage.getItem("token"),
    };

    await cargarReservas();

    async function cargarReservas() {
        try {
            const response = await fetch(API_RESERVAS, {
                method: "GET",
                headers: {
                    "Authorization": `Bearer ${usuarioInfo.token}`,
                },
            });

            if (!response.ok) throw new Error("Error al obtener reservas");

            const reservas = await response.json();
            mostrarReservas(reservas);
        } catch (error) {
            console.error("Error al cargar reservas:", error);
        }
    }

    function mostrarReservas(reservas) {
        const contenedor = document.getElementById("lista-reservas");
        contenedor.innerHTML = "";

        reservas.forEach((reserva) => {
            const reservaDiv = document.createElement("div");
            reservaDiv.className = "reserva-box";

            const imagenUrl = reserva.herramientaImagen || "ruta/por_defecto.jpg";

            reservaDiv.innerHTML = `
                <img src="${imagenUrl}" class="herramienta-img" alt="Imagen de herramienta">
                <p><strong>Herramienta:</strong> ${reserva.herramientaNombre}</p>
                <p><strong>Fecha Inicio:</strong> ${reserva.fechaInicio}</p>
                <p><strong>Fecha Fin:</strong> ${reserva.fechaFin}</p>
                <p><strong>Cliente:</strong> ${reserva.clienteNombre}</p>
                <select id="estado-${reserva.id}" class="estado-select">
                    <option value="PENDIENTE" ${reserva.estado === "PENDIENTE" ? "selected" : ""}>Pendiente</option>
                    <option value="CONFIRMADA" ${reserva.estado === "CONFIRMADA" ? "selected" : ""}>Confirmada</option>
                    <option value="EN_CURSO" ${reserva.estado === "EN_CURSO" ? "selected" : ""}>En curso</option>
                    <option value="COMPLETADA" ${reserva.estado === "COMPLETADA" ? "selected" : ""}>Completada</option>
                    <option value="CANCELADA" ${reserva.estado === "CANCELADA" ? "selected" : ""}>Cancelada</option>
                </select>

                <button class="confirmar-btn" data-id="${reserva.id}">Confirmar</button>
            `;

            contenedor.appendChild(reservaDiv);
        });

        document.querySelectorAll(".confirmar-btn").forEach((btn) => {
            btn.addEventListener("click", () => actualizarEstadoReserva(btn.dataset.id));
        });
    }

    async function actualizarEstadoReserva(id) {
        const nuevoEstado = document.getElementById(`estado-${id}`).value;

        try {
            const response = await fetch(`${API_RESERVAS}/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${usuarioInfo.token}`,
                },
                body: JSON.stringify({ 
                    estado: nuevoEstado }),
            });

            if (response.ok) {
                alert("Estado actualizado correctamente.");
                cargarReservas();
            } else {
                alert("Error al actualizar estado.");
            }
        } catch (error) {
            console.error("Error al actualizar estado de reserva:", error);
        }
    }
});
