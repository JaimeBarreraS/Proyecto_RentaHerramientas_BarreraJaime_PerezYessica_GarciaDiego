document.addEventListener("DOMContentLoaded", () => {
    const role = localStorage.getItem("role");
    const nombre = localStorage.getItem("nombre");

    // Mostrar el nombre y rol en la interfaz
    document.getElementById("user-info").innerText = `Hola, ${nombre}. Tu rol es: ${role}`;

    // Activar las opciones según el rol del usuario
    if (role === "ADMIN") {
        document.getElementById("admin-section").classList.remove("hidden");
    } else if (role === "PROVEEDOR") {
        document.getElementById("proveedor-section").classList.remove("hidden");
    } else if (role === "CLIENTE") {
        document.getElementById("cliente-section").classList.remove("hidden");
    } else {
        alert("Rol no identificado, volviendo al login.");
        window.location.href = "index.html";
    }
});

// Funciones según las opciones del usuario
function gestionarUsuarios() {
    alert("Redirigiendo a gestión de usuarios...");
}

function verPagos() {
    alert("Redirigiendo a historial de pagos...");
}

function gestionarHerramientas() {
    alert("Redirigiendo a gestión de herramientas...");
}

function verReservas() {
    alert("Redirigiendo a historial de reservas...");
}

function verHerramientas() {
    alert("Redirigiendo a herramientas disponibles...");
}

function hacerReserva() {
    alert("Redirigiendo a formulario de reserva...");
}

// Cierre de sesión
function cerrarSesion() {
    localStorage.clear(); // Limpia el almacenamiento local
    alert("Has cerrado sesión.");
    window.location.href = "index.html"; // Redirige al login
}
