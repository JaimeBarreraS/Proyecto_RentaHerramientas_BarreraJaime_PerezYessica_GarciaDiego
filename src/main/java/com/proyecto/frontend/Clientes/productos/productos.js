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
    const API_RESERVAS = "http://localhost:8080/api/reservas";

    const usuarioInfo = {
        nombre: "Usuario ",
        role: "CLIENTE",
        token: "234grtbtrbe566b5b_5befbyttyb",
        id: "1"
    };

    document.getElementById("ad").innerText = usuarioInfo.role || "Rol Desconocido";

    const seleccionadas = [];
    let ultimaReserva = null;


    const contenedorPrincipal = document.getElementById("sesion");


    const resumenDiv = document.createElement("div");
    resumenDiv.id = "resumen";
    resumenDiv.style.backgroundColor = "white";
    resumenDiv.style.padding = "20px";
    resumenDiv.style.borderRadius = "10px";
    resumenDiv.style.boxShadow = "0 2px 10px rgba(0,0,0,0.1)";

    resumenDiv.innerHTML = `
        <h5>Herramientas Seleccionadas:</h5>
        <ul id="lista-seleccionadas" style="list-style:none; padding-left:0; background:#f8f9fa; padding:15px; border-radius:8px;"></ul>
        <p> $<span id="total-precio">0</span></p>
    `;

    contenedorPrincipal.appendChild(resumenDiv);


    const botonesDiv = document.createElement("div");
    botonesDiv.id = "botones-reserva";
    botonesDiv.style.display = "flex";
    botonesDiv.style.gap = "10px";
    botonesDiv.style.marginTop = "10px";
    botonesDiv.style.marginLeft = "62vw";

    contenedorPrincipal.appendChild(botonesDiv);


    const confirmarBtn = document.createElement("button");
    confirmarBtn.id = "confirmar-reserva";
    confirmarBtn.innerText = "Confirmar Reserva";
    confirmarBtn.className = "btn btn-success";
    botonesDiv.appendChild(confirmarBtn);


    const descargarFacturaBtn = document.createElement("button");
    descargarFacturaBtn.id = "descargar-factura";
    descargarFacturaBtn.innerText = "Descargar Factura";
    descargarFacturaBtn.className = "btn btn-outline-primary";
    descargarFacturaBtn.style.display = "none";
    botonesDiv.appendChild(descargarFacturaBtn);

    descargarFacturaBtn.addEventListener("click", () => {
        if (ultimaReserva) {
            generarFactura(ultimaReserva);
        } else {
            alert("Primero realiza una reserva.");
        }
    });

    confirmarBtn.addEventListener("click", async () => {
        if (seleccionadas.length === 0) {
            alert("No has seleccionado ninguna herramienta.");
            return;
        }

        const dias = parseInt(prompt("¿Por cuántos días deseas alquilar las herramientas?"), 10);
        if (isNaN(dias) || dias <= 0) {
            alert("Número de días inválido.");
            return;
        }

        const detalles = seleccionadas.map(h => ({
            herramientaId: h.id,
            cantidadDias: dias
        }));

        const reserva = {
            clienteId: usuarioInfo.id,
            herramientas: detalles
        };

        try {
            console.log("Enviando reserva:", reserva);
            
            const resultado = {
                id: Date.now(),
                clienteId: usuarioInfo.id,
                herramientas: seleccionadas.map(h => ({
                    ...h,
                    cantidadDias: dias,
                    subtotal: h.precioPorDia * dias
                })),
                total: seleccionadas.reduce((sum, h) => sum + (h.precioPorDia * dias), 0),
                fechaCreacion: new Date().toISOString()
            };
            
            ultimaReserva = resultado;
            alert("Reserva creada con éxito");
            descargarFacturaBtn.style.display = "inline-block";
        } catch (error) {
            console.error("Error al reservar:", error);
            alert("Error al realizar la reserva.");
        }
    });

    await cargarHerramientas();

    async function cargarHerramientas() {
        try {

            const herramientas = [
                {
                    id: 1,
                    nombre: "Taladro Eléctrico",
                    categoria: "Herramientas Eléctricas",
                    descripcion: "Taladro profesional de alta potencia para todo tipo de materiales",
                    precioPorDia: 25,
                    imagenUrl: "https://i.pinimg.com/736x/f9/91/e1/f991e12b371c06b396b8726df0ae5bc7.jpg"
                },
                {
                    id: 2,
                    nombre: "Sierra Circular",
                    categoria: "Herramientas de Corte",
                    descripcion: "Sierra circular para cortes precisos en madera y metal",
                    precioPorDia: 35,
                    imagenUrl: "https://i.pinimg.com/736x/a1/d4/9d/a1d49d3be82fe2146094003ab472cee5.jpg"
                },
                {
                    id: 3,
                    nombre: "Martillo Neumático",
                    categoria: "Herramientas de Demolición",
                    descripcion: "Martillo neumático para trabajos pesados de demolición",
                    precioPorDia: 50,
                    imagenUrl: "https://i.pinimg.com/736x/27/00/12/2700121dc3e4d0e632ee5b9f71b8c40a.jpg"
                },
                {
                    id: 4,
                    nombre: "Lijadora Orbital",
                    categoria: "Herramientas de Acabado",
                    descripcion: "Lijadora orbital para acabados perfectos en superficies",
                    precioPorDia: 20,
                    imagenUrl: "https://i.pinimg.com/736x/d2/3d/f5/d23df51274df2724943e3bcb50e82c15.jpg"
                },
                {
                    id: 5,
                    nombre: "Compresor de Aire",
                    categoria: "Herramientas Neumáticas",
                    descripcion: "Compresor portátil para herramientas neumáticas",
                    precioPorDia: 40,
                    imagenUrl: "https://i.pinimg.com/736x/1f/a7/45/1fa74545045d9e4d945df4c47bd906b6.jpg"
                },
                {
                    id: 6,
                    nombre: "Soldadora MIG",
                    categoria: "Equipos de Soldadura",
                    descripcion: "Soldadora MIG para trabajos profesionales de metal",
                    precioPorDia: 60,
                    imagenUrl: "https://i.pinimg.com/736x/14/07/d3/1407d39b66346e19f989250c494f6783.jpg"
                },

                {
                    id: 7,
                    nombre: "Amoladora Angular",
                    categoria: "Herramientas de Corte",
                    descripcion: "Amoladora potente para cortar, lijar y desbastar diversos materiales",
                    precioPorDia: 30,
                    imagenUrl: "https://i.pinimg.com/736x/3a/57/47/3a5747c98268cc876e9d372180d74555.jpg"
                },
                {
                    id: 8,
                    nombre: "Pistola de Calor",
                    categoria: "Herramientas Eléctricas",
                    descripcion: "Pistola de calor ideal para remover pintura y trabajos de bricolaje",
                    precioPorDia: 15,
                    imagenUrl: "https://i.pinimg.com/736x/d6/7e/70/d67e707ad4b711969d3a4312cbabb56c.jpg"
                },
                {
                    id: 9,
                    nombre: "Nivel Láser",
                    categoria: "Herramientas de Medición",
                    descripcion: "Nivel láser con proyección horizontal y vertical para máxima precisión",
                    precioPorDia: 28,
                    imagenUrl: "https://i.pinimg.com/736x/91/2b/33/912b33742e686cd382fd9a9799b745fd.jpg"
                },
                {
                    id: 10,
                    nombre: "Cortadora de Cerámica",
                    categoria: "Herramientas de Corte",
                    descripcion: "Cortadora de cerámica para trabajos en pisos y revestimientos",
                    precioPorDia: 32,
                    imagenUrl: "https://i.pinimg.com/736x/d0/0d/b9/d00db99bdf0fda6bb4f233c8b4ef7ac6.jpg"
                },
                {
                    id: 11,
                    nombre: "Generador Eléctrico",
                    categoria: "Equipos Eléctricos",
                    descripcion: "Generador de energía portátil para obras o emergencias",
                    precioPorDia: 65,
                    imagenUrl: "https://i.pinimg.com/736x/55/da/8e/55da8e39ba0cfb979a8e4feba05a948a.jpg"
                },
                {
                    id: 12,
                    nombre: "Andamio Metálico",
                    categoria: "Equipos de Construcción",
                    descripcion: "Andamio de acero ideal para trabajos en altura con seguridad",
                    precioPorDia: 45,
                    imagenUrl: "https://i.pinimg.com/736x/e7/b3/f5/e7b3f563ec60c6b7ff101603e97982cc.jpg"
                },
                {
                    id: 13,
                    nombre: "Pulidora de Piso",
                    categoria: "Herramientas de Acabado",
                    descripcion: "Pulidora de alta velocidad para acabado de pisos",
                    precioPorDia: 50,
                    imagenUrl: "https://i.pinimg.com/736x/f6/bf/ff/f6bfffd4d8d16087473f61e58e2b6119.jpg"
                },
                {
                    id: 14,
                    nombre: "Pistola de Pintura",
                    categoria: "Herramientas de Pintura",
                    descripcion: "Pistola de pintura para acabados finos y rápidos",
                    precioPorDia: 27,
                    imagenUrl: "https://i.pinimg.com/736x/a1/0b/9a/a10b9aa7ecf242b6a3d7991f114eb60e.jpg"
                },
                {
                    id: 15,
                    nombre: "Cortasetos Eléctrico",
                    categoria: "Herramientas de Jardinería",
                    descripcion: "Cortasetos ideal para dar forma y mantenimiento a arbustos",
                    precioPorDia: 22,
                    imagenUrl: "https://i.pinimg.com/736x/76/04/0a/76040ab03c3484781a1ce8213b07a071.jpg"
                },
                {
                    id: 16,
                    nombre: "Motoazada",
                    categoria: "Herramientas Agrícolas",
                    descripcion: "Motoazada para arar y remover tierra en pequeños terrenos",
                    precioPorDia: 55,
                    imagenUrl: "https://i.pinimg.com/736x/9d/b0/9d/9db09db70e4573c55bd15e7728e03180.jpg"
                },
                {
                    id: 17,
                    nombre: "Escalera Telescópica",
                    categoria: "Equipos de Acceso",
                    descripcion: "Escalera compacta y extensible para uso doméstico y profesional",
                    precioPorDia: 18,
                    imagenUrl: "https://i.pinimg.com/736x/90/32/c1/9032c18c84cf590b244387b99a753cff.jpg"
                },
                {
                    id: 18,
                    nombre: "Detector de Metales",
                    categoria: "Herramientas de Medición",
                    descripcion: "Detector para localizar objetos metálicos en muros y suelos",
                    precioPorDia: 26,
                    imagenUrl: "https://i.pinimg.com/736x/d5/47/ff/d547ffb99d14ec12d51241d007a562d3.jpg"
                },
                {
                    id: 19,
                    nombre: "Pala Compactadora",
                    categoria: "Herramientas de Compactación",
                    descripcion: "Pala para compactar terreno en obras pequeñas o medianas",
                    precioPorDia: 38,
                    imagenUrl: "https://i.pinimg.com/736x/e7/3f/f2/e73ff27740323ed088d3a9f7d35b3bf8.jpg"
                },
                {
                    id: 20,
                    nombre: "Carretilla de Obra",
                    categoria: "Herramientas de Transporte",
                    descripcion: "Carretilla resistente para transporte de materiales pesados",
                    precioPorDia: 16,
                    imagenUrl: "https://i.pinimg.com/736x/bf/f4/6b/bff46b833a3135d368d63c48b76c2baf.jpg"
                }
            ];
            
            mostrarHerramientas(herramientas);
            

        } catch (error) {
            console.error("Error al cargar herramientas:", error);
        }
    }

    function mostrarHerramientas(herramientas) {
        const contenedor = document.getElementById("ima");
        contenedor.innerHTML = "";
        contenedor.style.display = "grid";
        contenedor.style.gridTemplateColumns = "repeat(auto-fit, minmax(300px, 1fr))";
        contenedor.style.gap = "20px";
        contenedor.style.marginTop = "20px";

        herramientas.forEach((herramienta) => {
            const herramientaDiv = document.createElement("div");
            herramientaDiv.className = "herramienta-box";
            herramientaDiv.dataset.id = herramienta.id;
            herramientaDiv.style.border = "1px solid #ddd";
            herramientaDiv.style.borderRadius = "10px";
            herramientaDiv.style.padding = "15px";
            herramientaDiv.style.backgroundColor = "white";
            herramientaDiv.style.boxShadow = "0 2px 10px rgba(0,0,0,0.1)";
            herramientaDiv.style.transition = "transform 0.2s, box-shadow 0.2s";


            herramientaDiv.addEventListener("mouseenter", () => {
                herramientaDiv.style.transform = "translateY(-5px)";
                herramientaDiv.style.boxShadow = "0 5px 20px rgba(0,0,0,0.15)";
            });

            herramientaDiv.addEventListener("mouseleave", () => {
                herramientaDiv.style.transform = "translateY(0)";
                herramientaDiv.style.boxShadow = "0 2px 10px rgba(0,0,0,0.1)";
            });

            herramientaDiv.innerHTML = `
                <div class="info" style="text-align: center;">
                    <img src="${herramienta.imagenUrl}" class="foto" style="width:100px; height:100px; object-fit:cover; border-radius:8px; margin-bottom:15px;"><br>
                    <span class="nombre" style="font-size:1.2em; font-weight:bold; color:#333;"><strong>${herramienta.nombre} </strong></span><br><br>
                    <span class="categoria" style="color:#666; font-size:0.9em; background:#f0f0f0; padding:5px 10px; border-radius:15px; display:inline-block; margin-bottom:10px;">${herramienta.categoria}</span><br><br>
                    <span class="descripcion" style="color:#555; line-height:1.4; display:block; margin-bottom:15px;">${herramienta.descripcion}</span><br>
                    <span class="precio" style="font-size:1.3em; font-weight:bold; color:#4CAF50;">$${herramienta.precioPorDia} / día</span><br>
                </div>
            `;

            const btnSeleccionar = document.createElement("button");
            btnSeleccionar.id = "btn-seleccionar-";
            btnSeleccionar.textContent = "Seleccionar";
            btnSeleccionar.className = "btn btn-primary btn-sm mt-3";
            btnSeleccionar.style.width = "100%";
            btnSeleccionar.addEventListener("click", () => {
                if (!seleccionadas.find(h => h.id === herramienta.id)) {
                    seleccionadas.push(herramienta);
                    alert(`${herramienta.nombre} agregado a la reserva`);
                    actualizarResumen();
                    btnSeleccionar.textContent = "✓ Seleccionado";
                    btnSeleccionar.className = "btn btn-success btn-sm mt-3";
                    btnSeleccionar.disabled = true;
                } else {
                    alert("Esta herramienta ya está seleccionada");
                }
            });

            herramientaDiv.appendChild(btnSeleccionar);
            contenedor.appendChild(herramientaDiv);
        });
    }

    function actualizarResumen() {
        const lista = document.getElementById("lista-seleccionadas");
        const totalSpan = document.getElementById("total-precio");

        lista.innerHTML = "";
        let total = 0;

        if (seleccionadas.length === 0) {
            lista.innerHTML = "<li style='color:#999; font-style:italic;'>No hay herramientas seleccionadas</li>";
        } else {
            seleccionadas.forEach((h, index) => {
                const li = document.createElement("li");
                li.style.padding = "8px 0";
                li.style.borderBottom = index < seleccionadas.length - 1 ? "1px solid #eee" : "none";
                li.innerHTML = `
                    <div style="display:flex; justify-content:space-between; align-items:center;">
                        <span><strong>${h.nombre}</strong></span>
                        <span style="color:#4CAF50; font-weight:bold;">$${h.precioPorDia} / día</span>
                    </div>
                `;
                lista.appendChild(li);
                total += h.precioPorDia;
            });
        }

        totalSpan.textContent = total;
    }

    function generarFactura(reserva) {
        const { jsPDF } = window.jspdf;
        const doc = new jsPDF();


        doc.setFontSize(20);
        doc.setTextColor(40, 40, 40);
        doc.text("FACTURA DE RESERVA", 20, 20);
        
        doc.setFontSize(16);
        doc.setTextColor(100, 100, 100);
        doc.text("Indus Rental - Tools Rental", 20, 30);


        doc.setFontSize(12);
        doc.setTextColor(40, 40, 40);
        doc.text(`Cliente: ${usuarioInfo.nombre}`, 20, 50);
        doc.text(`Fecha: ${new Date().toLocaleDateString()}`, 20, 60);
        doc.text(`ID Reserva: ${reserva.id}`, 20, 70);


        doc.line(20, 80, 190, 80);


        doc.setFontSize(14);
        doc.text("Detalle de herramientas:", 20, 95);

        let y = 110;
        let total = 0;
        
        doc.setFontSize(10);
        reserva.herramientas.forEach((detalle, i) => {
            const subtotal = detalle.precioPorDia * detalle.cantidadDias;
            total += subtotal;
            
            doc.text(`${i + 1}. ${detalle.nombre}`, 25, y);
            doc.text(`$${detalle.precioPorDia} x ${detalle.cantidadDias} días = $${subtotal}`, 25, y + 8);
            y += 20;
        });


        doc.line(20, y, 190, y);
        y += 10;


        doc.setFontSize(14);
        doc.setTextColor(0, 150, 0);
        doc.text(`TOTAL: $${total}`, 20, y);


        doc.save("factura_reserva_indus_rental.pdf");
    }


    actualizarResumen();
});











