const API_URL = "http://localhost:8080/api/auth";

// LOGIN
document.getElementById("login-form").addEventListener("submit", async (event) => {
    event.preventDefault();
    
    const email = document.getElementById("login-email").value;
    const password = document.getElementById("login-password").value;
    
    const response = await fetch(`${API_URL}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password })
    });

    const data = await response.json();
    
    if (response.ok) {
        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);  
        alert(`Login exitoso, bienvenido ${data.nombre}.`);
        window.location.href = "dashboard.html";  
    } else {
        alert("Error en el login: " + data);
    }
});

// REGISTRO
document.getElementById("register-form").addEventListener("submit", async (event) => {
    event.preventDefault();

    const nombre = document.getElementById("register-nombre").value;
    const email = document.getElementById("register-email").value;
    const password = document.getElementById("register-password").value;
    const role = document.getElementById("register-role").value;

    if (!role) {
        alert("Selecciona un rol antes de registrarte.");
        return;
    }

    const response = await fetch(`${API_URL}/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nombre, email, password, role })
    });

    const data = await response.json();

    if (response.ok) {
        alert("Registro exitoso, ahora inicia sesión.");
    } else {
        alert("Error en el registro: " + data);
    }
});
