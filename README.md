![image](https://github.com/user-attachments/assets/bafad487-dcfe-4aac-8bbc-a8dc4b52ab44)

# Indus Rental 🛠️

## Descripción del proyecto 📝
Indus Rental Es una plataforma digital diseñada para facilitar el alquiler de herramientas y maquinaria de construcción, dirigida tanto a proveedores como a particulares. 
Este proyecto tiene como objetivo optimizar la gestión del inventario, reservas, disponibilidad y pagos mediante un sistema de información.

El sistema propuesto permite:

* Gestión de Inventario: Registro detallado de herramientas y equipos disponibles para alquiler, incluyendo nombre, categoría, estado, descripción, precio por día e imágenes.
* Control de Reservas: Módulo para que los clientes puedan consultar disponibilidad y confirmar sus reservas en línea.
* Administración de Pagos: Integración de pagos digitales con generación automática de facturas.
* Gestión de Proveedores: Registro de usuarios con rol de proveedor que pueden añadir, modificar o eliminar herramientas desde su panel de administración.
* Control de Usuarios: Gestión de roles (clientes y proveedores), autenticación segura y personalización de la experiencia según el tipo de usuario.
* Generación de Reportes: Exportación de reportes en PDF para facturación.

## Objetivo del proyecto 
Diseñar e implementar una plataforma digital para la gestión integral del alquiler de herramientas y equipos de construcción, 
que optimice los procesos de inventario, reservas, pagos y comunicación entre proveedores y clientes.

## Metodología 
El proyecto utiliza un enfoque relacional para modelar la base de datos y tener mejores prácticas de diseño.

## Beneficios del sistema
* Optimización del tiempo y recursos: Automatiza procesos como reservas, pagos y generación de facturas, reduciendo
la carga operativa manual para proveedores y clientes.
* Acceso en tiempo real:Los usuarios pueden consultar disponibilidad, precios y realizar reservas desde cualquier lugar
y en cualquier momento, a través de una interfaz web.
* Mejora en la toma de decisiones: Los reportes generados por el sistema permiten analizar tendencias, identificar herramientas
* más demandadas y tomar decisiones estratégicas basadas en datos.

## Requisitos del sistema ⚙️
* Software necesarios:
  * Java 21.
  * Spring Boot.
  * PostgreSQL.
  * WebSockets.
  * JWT.

## Archivos adicionales
- **`README.md`:**  
  Documento escrito con explicaciones del proyecto, instrucciones de uso y otros detalles esenciales.  
- **`Diagrama.jpg`:**  
  Imagen del modelo de datos para referencia visual.  

## **Estructura de la Base de Datos**  📚
El sistema de base de datos relacional para **Indus Rental** está compuesto por múltiples tablas que 
representan las principales entidades y procesos de la empresa. Estas tablas han sido diseñadas siguiendo 
principios de normalización para garantizar la consistencia, integridad y eficiencia en la gestión de datos.  

A continuación, se detalla la descripción de cada tabla, sus atributos. 

| **Tablas**          | **Descripción**                                                                                                                                 |
|-----------------------|-------------------------------------------------------------------------------------------------------------------------------------------------|
| **Herramienta**          | Gestión de herramientas con datos como id, nombre, descripción, categoría, precio por día, estado e imagen.                                  |
| **Pago**       | Gestión de pagos con datos como id, monto, método de pago, estado, fecha de pago, y referencia de pago                                             |
| **Reserva**       | Gestión de reservas con datos como id, fecha de inicio, fecha de fin, total de pago, estado, fecha reserva y observaciones.                   |
| **Usuario**            | Gestión de usuarios con datos como id, email, password, nombre, teléfono y fecha de registro.                                                 |


## **Contribuciones** 🎉
Este proyecto fue desarrollado en colaboración:
   - 👑 **Jaime Enrique Barrera Sandoval:** Diseño y desarrollo de la base de datos, inserciones, backend y frontend.
   - 👑 **Yessica Andrea Perez Machuca:** Diseño y desarrollo de la base de datos, backend y frontend.
   - 👑 **Diego Alexander García Rodriguez** Diseño y desarrollo de la base de datos, backend y frontend.

