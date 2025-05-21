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

function generarFactura(referencia, monto, fecha) {
    const facturaDiv = document.createElement("div");
    facturaDiv.classList.add("factura-item");
    facturaDiv.dataset.id = referencia;

    facturaDiv.innerHTML = `
        <h3>Factura</h3>
        <p><strong>Referencia:</strong> ${referencia}</p>
        <p><strong>Monto:</strong> $${monto}</p>
        <p><strong>Fecha:</strong> ${fecha}</p>
        <button class="btnEliminar" onclick="eliminarFactura('${referencia}')">🗑️ Eliminar Factura</button>
    `;

    document.getElementById("lista-facturas").appendChild(facturaDiv);
}

function eliminarFactura(referencia) {
    const factura = document.querySelector(`.factura-item[data-id='${referencia}']`);
    if (factura) factura.remove();

    eliminarPago(referencia); 
}

function eliminarPago(referencia) {
    const pago = document.querySelector(`.pago-item[data-id='${referencia}']`);
    if (pago) pago.remove();
}

document.addEventListener("DOMContentLoaded", async () => {
    const API_USERS = "http://localhost:8080/api/usuarios";
    const usuarioInfo = {
        nombre: localStorage.getItem("nombre"),
        role: localStorage.getItem("role"),
        token: localStorage.getItem("token"),
    };

    document.getElementById("ad").innerText = usuarioInfo.role || "Rol Desconocido";

    document.getElementById("btnBuscarDia").addEventListener("click", () => {
        const dia = document.getElementById("inputDia").value;
        cargarPagos(`dia/${dia}`);
    });

    document.getElementById("btnBuscarMes").addEventListener("click", () => {
        const mes = document.getElementById("inputMes").value;
        cargarPagos(`mes/${mes}`);
    });

    document.getElementById("btnBuscarAno").addEventListener("click", () => {
        const ano = document.getElementById("inputAno").value;
        cargarPagos(`ano/${ano}`);
    });

    async function cargarPagos(tipo) {
        try {
            const response = await fetch(`http://localhost:8080/api/pagos/${tipo}`, {
                method: "GET",
                headers: { "Authorization": `Bearer ${localStorage.getItem("token")}` },
            });

            if (!response.ok) throw new Error("Error al obtener pagos");

            const pagos = await response.json();
            mostrarPagos(pagos);
        } catch (error) {
            console.error("Error al cargar pagos:", error);
        }
    }

    function mostrarPagos(pagos) {
        const contenedor = document.getElementById("lista-pagos");
        contenedor.innerHTML = pagos.length > 0 ? pagos.map(pago => {
            const fechaPago = new Date(pago.fechaPago);
            const fechaFormateada = !isNaN(fechaPago.getTime())
                ? fechaPago.toLocaleDateString("es-ES", { year: "numeric", month: "long", day: "numeric" })
                : "Fecha no válida";
    
            return `
                <div class="pago-item" data-id="${pago.referenciaPago}">
                    <p><strong>Referencia:</strong> ${pago.referenciaPago}</p>
                    <p><strong>Monto:</strong> $${pago.monto}</p>
                    <p><strong>Método:</strong> ${pago.metodoPago}</p>
                    <p><strong>Estado:</strong> ${pago.estado}</p>
                    <p><strong>Fecha:</strong> ${fechaFormateada}</p>
                    <div class="factura-container">
                        <button class="btnFactura" onclick="generarFactura('${pago.referenciaPago}', '${pago.monto}', '${fechaFormateada}')">🧾 Generar Factura</button>
                        <button class="btnEliminar" onclick="eliminarPago('${pago.referenciaPago}')">🗑️ Eliminar Pago</button>
                        <img src="Imagenes/factura.png" alt="Factura" class="factura-imagen">
                    </div>
                </div>
            `;
        }).join("") : "<p>No se encontraron pagos en este período.</p>";
    }
    
});












