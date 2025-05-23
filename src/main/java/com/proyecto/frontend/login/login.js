const API_URL = "http://localhost:8080/api/auth/login";

document.querySelector(".login-box").addEventListener("submit", async (event) => {
    event.preventDefault();

    const email = document.querySelector("input[placeholder='gmail']").value.trim();
    const password = document.querySelector("input[placeholder='password']").value.trim();

    if (!email || !password) {
        alert("Por favor, ingresa tu correo y contraseña.");
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json();
        console.log("Respuesta del servidor:", data); 

        if (response.ok) {
            localStorage.setItem("token", data.token);  
            localStorage.setItem("role", data.role);    

            alert("Login exitoso. Redirigiendo...");

            // Redirigir segun el rol
            switch (data.role) {
                case "CLIENTE":
                    window.location.href = "../Administrador/administrador/admin.html";
                    break;
                case "PROVEEDOR":
                    window.location.href = "../Proveedor/Inventario/inventario.html";
                    break;
                case "ADMIN":
                    window.location.href = "../Administrador/administrador/admin.html";
                    break;
                default:
                    alert("Rol desconocido, contacta al soporte.");
            }
        } else {
            alert("Error en el login: " + (data.error || "Verifica tus credenciales."));
        }
    } catch (error) {
        alert("Error al conectarse con el servidor.");
        console.error("Error de conexión:", error);
    }
});
