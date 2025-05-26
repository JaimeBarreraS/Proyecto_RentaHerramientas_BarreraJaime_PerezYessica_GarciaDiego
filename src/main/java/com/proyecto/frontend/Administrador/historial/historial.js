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

    async function cargarReservas() {
        try {
            const response = await fetch(API_RESERVAS, {
                method: "GET",
                headers: { "Authorization": `Bearer ${localStorage.getItem("token")}` },
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
        const estadoSeleccionado = document.getElementById("estadoReserva").value.toLowerCase(); // 🔥 Corrección

        console.log("Estado seleccionado para filtrar:", estadoSeleccionado);

        const reservasFiltradas = estadoSeleccionado === "todos" 
            ? reservas 
            : reservas.filter(reserva => reserva.estado?.toLowerCase() === estadoSeleccionado); // 🔥 Comparación segura

        contenedor.innerHTML = reservasFiltradas.length > 0 
            ? reservasFiltradas.map(reserva => {
                const fechaReserva = new Date(reserva.fechaReserva);
                const fechaInicio = new Date(reserva.fechaInicio);
                const fechaFin = new Date(reserva.fechaFin);

                const reservaFormateada = !isNaN(fechaReserva.getTime()) ? fechaReserva.toLocaleString() : "Fecha no válida";
                const inicioFormateada = !isNaN(fechaInicio.getTime()) ? fechaInicio.toLocaleString() : "Fecha no válida";
                const finFormateada = !isNaN(fechaFin.getTime()) ? fechaFin.toLocaleString() : "Fecha no válida";

                return `
                    <div class="reserva-item">
                        <p><strong>Cliente:</strong> ${reserva.clienteNombre}</p>
                        <p><strong>Herramienta:</strong> ${reserva.herramientaNombre}</p>
                        <p><strong>Estado:</strong> ${reserva.estado}</p>
                        <p><strong>Fecha de Reserva:</strong> ${reservaFormateada}</p>
                        <p><strong>Fecha Inicio:</strong> ${inicioFormateada}</p>
                        <p><strong>Fecha Fin:</strong> ${finFormateada}</p>
                        <p><strong>Total Pago:</strong> $${reserva.totalPago}</p>
                        <p><strong>Observaciones:</strong> ${reserva.observaciones || "Sin observaciones"}</p>
                        <div class="herramienta"> 
                            <img src="${reserva.herramientaImagen || 'Imagenes/default.jpg'}" 
                                alt="${reserva.herramientaNombre}" class="herramienta-imagen">
                        </div>
                    </div>
                `;
            }).join("")
            : "<p>No se encontraron reservas con este estado.</p>";
    }

    document.getElementById("estadoReserva").addEventListener("change", cargarReservas);

    await cargarReservas();
});

