const API_URL = "http://localhost:8080/api/auth";

// Funcionalidad para mostrar/ocultar contraseña
const passInput = document.getElementById("pass");
const ojo = document.getElementById("ojo");

if (ojo) {
    ojo.addEventListener("click", function() {
        const isPassword = passInput.type === "password";
        passInput.type = isPassword ? "text" : "password";
    });
}

// REGISTRO 
document.getElementById("signup-btn").addEventListener("click", async (event) => {
    event.preventDefault();
    
    console.log("Iniciando proceso de registro...");
    
    // Obtener valores del formulario
    const nombre = document.getElementById("register-nombre").value;
    const email = document.getElementById("register-email").value;
    const password = document.getElementById("pass").value;
    const telefono = document.getElementById("tel").value;
    const role = document.getElementById("opciones").value;
    
    const userData = {
        nombre: nombre,
        email: email,
        password: password,
        telefono: telefono,
        role: role
    };
    
    console.log("Datos a enviar:", userData);
    
    try {
        const response = await fetch(`${API_URL}/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Accept": "application/json"
            },
            body: JSON.stringify(userData)
        });
        
        console.log("Respuesta del servidor:", response.status);
        
        let data;
        try {
            const textResponse = await response.text();
            console.log("Respuesta como texto:", textResponse);

            if (textResponse.trim().startsWith('{') || textResponse.trim().startsWith('[')) {
                data = JSON.parse(textResponse);
            } else {
                data = { message: textResponse };
            }
        } catch (parseError) {
            console.error("Error al parsear respuesta:", parseError);
            data = { error: "Error al procesar la respuesta del servidor" };
        }
        
        if (response.ok) {
            alert("Registro exitoso, redirigiendo al login...");
            setTimeout(() => {
                window.location.href = "../login/login.html";
            }, 2000);
        } else {
            alert(`Error en el registro (${response.status}): ${data.message || JSON.stringify(data)}`);
        }
    } catch (error) {
        console.error("Error de conexión:", error);
        alert("Error de conexión con el servidor. Verifica que el servidor esté activo.");
    }
});

console.log("URL actual:", window.location.href);
console.log("API URL:", API_URL + "/register");


if (ojo) {
    ojo.style.cursor = "pointer";
    ojo.title = "Mostrar/Ocultar contraseña";
}