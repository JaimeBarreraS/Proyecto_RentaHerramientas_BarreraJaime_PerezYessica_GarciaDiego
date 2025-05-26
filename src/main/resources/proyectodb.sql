
INSERT INTO usuarios (email, password, nombre, telefono, role, activo, fecha_registro) VALUES
('andres.moreno@gmail.com', 'pass01', 'Andrés Moreno', '3001234567', 'CLIENTE', true, '2021-01-10T09:15:00'),
('laura.gomez@gmail.com', 'pass02', 'Laura Gómez', '3012345678', 'CLIENTE', true, '2021-02-11T10:30:00'),
('carlos.perez@gmail.com', 'pass03', 'Carlos Pérez', '3023456789', 'CLIENTE', true, '2021-03-12T11:45:00'),
('maria.rodriguez@gmail.com', 'pass04', 'María Rodríguez', '3034567890', 'CLIENTE', true, '2021-04-13T13:00:00'),
('julian.ramirez@gmail.com', 'pass05', 'Julián Ramírez', '3045678901', 'CLIENTE', true, '2021-05-14T14:15:00'),
('camila.ortiz@gmail.com', 'pass06', 'Camila Ortiz', '3056789012', 'PROVEEDOR', true, '2022-01-01T10:10:00'),
('sebastian.arias@gmail.com', 'pass07', 'Sebastián Arias', '3067890123', 'PROVEEDOR', true, '2022-02-02T11:20:00'),
('diana.vera@gmail.com', 'pass08', 'Diana Vera', '3078901234', 'PROVEEDOR', true, '2022-03-03T12:30:00'),
('juan.sanchez@gmail.com', 'pass09', 'Juan Sánchez', '3089012345', 'ADMIN', true, '2022-04-04T13:40:00'),
('paola.mendez@gmail.com', 'pass10', 'Paola Méndez', '3090123456', 'ADMIN', true, '2022-05-05T14:50:00'),
('felipe.rios@gmail.com', 'pass11', 'Felipe Ríos', '3101234567', 'CLIENTE', true, '2023-01-11T09:00:00'),
('valentina.luna@gmail.com', 'pass12', 'Valentina Luna', '3112345678', 'CLIENTE', true, '2023-02-12T10:15:00'),
('daniel.castillo@gmail.com', 'pass13', 'Daniel Castillo', '3123456789', 'CLIENTE', true, '2023-03-13T11:30:00'),
('angelica.soto@gmail.com', 'pass14', 'Angélica Soto', '3134567890', 'CLIENTE', true, '2023-04-14T12:45:00'),
('mateo.vargas@gmail.com', 'pass15', 'Mateo Vargas', '3145678901', 'CLIENTE', true, '2023-05-15T14:00:00'),
('sofia.castro@gmail.com', 'pass16', 'Sofía Castro', '3156789012', 'PROVEEDOR', true, '2024-01-16T08:10:00'),
('alejandro.nieto@gmail.com', 'pass17', 'Alejandro Nieto', '3167890123', 'PROVEEDOR', true, '2024-02-17T09:20:00'),
('carolina.martinez@gmail.com', 'pass18', 'Carolina Martínez', '3178901234', 'ADMIN', true, '2024-03-18T10:30:00'),
('nicolas.torres@gmail.com', 'pass19', 'Nicolás Torres', '3189012345', 'ADMIN', true, '2024-04-19T11:40:00'),
('luisa.fernandez@gmail.com', 'pass20', 'Luisa Fernández', '3190123456', 'CLIENTE', true, '2024-05-20T12:50:00');


-- Insertar herramientas
INSERT INTO herramientas (nombre, descripcion, categoria, precio_dia, estado, imagen_url, proveedor_id, fecha_registro) VALUES 
('Destornillador', 'Herramienta para tornillos', 'Manuales', 2.99, 'DISPONIBLE', 'https://i.pinimg.com/736x/d4/31/6e/d4316e0f485a3cdaa67101fc5333ef9c.jpg', 6, '2023-01-05T10:00:00'),
('Pala', 'Pala para cavar', 'Construccion', 4.50, 'DISPONIBLE', 'https://i.pinimg.com/736x/8e/29/e1/8e29e1fbd7d271cf0832acaf2592205d.jpg', 7, '2023-02-06T11:00:00'),
('Cortadora', 'Corta concreto', 'Construccion', 20.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/c8/90/58/c8905874ac5a5ad02843a31a33d28719.jpg', 8, '2023-03-07T12:00:00'),
('Lijadora', 'Para superficies lisas', 'Acabados', 8.75, 'DISPONIBLE', 'https://i.pinimg.com/736x/d2/3d/f5/d23df51274df2724943e3bcb50e82c15.jpg', 6, '2023-04-08T13:00:00'),
('Pulidora', 'Para dar brillo', 'Acabados', 9.50, 'DISPONIBLE', 'https://i.pinimg.com/736x/64/9d/73/649d730bb7c3df7284b970af4330e872.jpg', 7, '2023-05-09T14:00:00'),
('Andamio', 'Andamio metalico', 'Estructuras', 15.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/e7/b3/f5/e7b3f563ec60c6b7ff101603e97982cc.jpg', 8, '2023-06-10T15:00:00'),
('Cinta metrica', 'Medicion precisa', 'Medicion', 1.99, 'DISPONIBLE', 'https://i.pinimg.com/736x/e9/4b/4f/e94b4fc4c1262ec217efcd03a9440d91.jpg', 6, '2023-07-11T16:00:00'),
('Taladro Percutor', 'Taladro de impacto', 'Electricidad', 18.99, 'DISPONIBLE', 'https://i.pinimg.com/736x/df/05/f4/df05f4108fd0117f1aca01d0009b5a6f.jpg', 7, '2023-08-12T17:00:00'),
('Escalera', 'Escalera de aluminio', 'Acceso', 12.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/43/98/10/439810297c03ec1185ce76ea9ef73cb5.jpg', 8, '2023-09-13T18:00:00'),
('Nivel', 'Nivel de burbuja', 'Medicion', 3.50, 'DISPONIBLE', 'https://i.pinimg.com/736x/b4/86/5e/b4865ea66f140f90eedc0b092436886b.jpg', 6, '2023-10-14T19:00:00'),
('Rotomartillo', 'Perforacion fuerte', 'Electricidad', 22.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/67/11/cb/6711cbbe5f74ec8aee3b97a64c7f7283.jpg', 7, '2024-01-15T10:10:00'),
('Compresor', 'Aire a presion', 'Neumatica', 30.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/1f/a7/45/1fa74545045d9e4d945df4c47bd906b6.jpg', 8, '2024-02-16T11:20:00'),
('Soldador', 'Soldadura electrica', 'Soldadura', 25.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/80/ed/fe/80edfe279f841988dfb7e5dfd44d5fa9.jpg', 6, '2024-03-17T12:30:00'),
('Generador', 'Energia electrica portatil', 'Electricidad', 40.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/dd/8c/14/dd8c145c582e1d244f6e15e9504665d1.jpg', 7, '2024-04-18T13:40:00'),
('Compactadora', 'Compactacion de suelos', 'Construccion', 35.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/e7/3f/f2/e73ff27740323ed088d3a9f7d35b3bf8.jpg', 8, '2024-05-19T14:50:00'),
('Broca', 'Broca de concreto', 'Accesorios', 1.50, 'DISPONIBLE', 'https://i.pinimg.com/736x/34/d9/38/34d93841dc5ddb86fb50c10e3927d712.jpg', 6, '2025-01-20T15:00:00'),
('Multimetro', 'Medidor de corriente', 'Electricidad', 6.99, 'DISPONIBLE', 'https://i.pinimg.com/736x/a5/a6/9e/a5a69e5ebb9a5a92647f1f144ecbf027.jpg', 7, '2025-02-21T16:10:00'),
('Cepillo electrico', 'Para madera', 'Carpinteria', 13.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/b3/9a/ab/b39aab59d1cf83d5d326dcf15269a457.jpg', 8, '2025-03-22T17:20:00'),
('Sierra circular', 'Corte recto madera', 'Carpinteria', 19.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/a1/d4/9d/a1d49d3be82fe2146094003ab472cee5.jpg', 6, '2025-04-23T18:30:00'),
('Aspiradora industrial', 'Limpieza pesada', 'Limpieza', 28.00, 'DISPONIBLE', 'https://i.pinimg.com/736x/d8/b4/18/d8b4184c159eab3f55c25f9e6c0dc925.jpg', 7, '2025-05-24T19:40:00');

INSERT INTO reservas (cliente_id, herramienta_id, fecha_inicio, fecha_fin, total_pago, estado, fecha_reserva, observaciones)
VALUES 
(3, 1, '2025-05-20 08:00:00', '2025-05-25 17:00:00', 29.95, 'CONFIRMADA', '2025-05-18 09:15:00', 'Uso en obra pequena'),
(3, 2, '2025-05-22 07:30:00', '2025-05-24 18:00:00', 31.98, 'PENDIENTE', '2025-05-20 10:45:00', 'Uso en remodelacion'),
(4, 3, '2025-06-01 08:00:00', '2025-06-05 17:00:00', 45.50, 'CONFIRMADA', '2025-05-30 14:00:00', 'Proyecto electrico residencial'),
(5, 1, '2025-05-30 09:00:00', '2025-06-02 17:00:00', 18.75, 'CANCELADA', '2025-05-29 11:00:00', 'Cancelada por mal clima'),
(6, 2, '2025-05-25 08:30:00', '2025-05-28 18:00:00', 60.00, 'CONFIRMADA', '2025-05-23 15:10:00', 'Alquiler para empresa constructora'),
(7, 4, '2025-05-27 08:00:00', '2025-05-30 16:00:00', 74.99, 'PENDIENTE', '2025-05-26 09:00:00', 'Trabajo en carretera rural'),
(8, 3, '2025-05-29 07:45:00', '2025-06-03 17:30:00', 50.00, 'COMPLETADA', '2025-05-27 08:30:00', 'Obra de ampliacion'),
(9, 5, '2025-06-01 08:00:00', '2025-06-07 18:00:00', 89.95, 'CANCELADA', '2025-05-29 14:20:00', 'Cliente no confirmo pago'),
(10, 1, '2025-06-02 09:00:00', '2025-06-05 17:00:00', 40.00, 'CONFIRMADA', '2025-06-01 12:00:00', 'Tarea domestica'),
(11, 2, '2025-05-23 08:00:00', '2025-05-27 18:00:00', 37.25, 'PENDIENTE', '2025-05-22 08:30:00', 'Reparacion estructural'),
(12, 3, '2025-05-21 07:30:00', '2025-05-24 17:30:00', 22.80, 'EN_CURSO', '2025-05-20 09:30:00', 'Mantenimiento de finca'),
(13, 4, '2025-06-05 08:00:00', '2025-06-10 18:00:00', 66.60, 'CONFIRMADA', '2025-06-03 11:45:00', 'Renovacion de local comercial'),
(14, 5, '2025-05-28 08:15:00', '2025-05-30 16:45:00', 33.33, 'CANCELADA', '2025-05-26 10:30:00', 'Cliente cambio fechas'),
(15, 2, '2025-06-03 09:00:00', '2025-06-06 17:00:00', 48.88, 'EN_CURSO', '2025-06-01 13:15:00', 'Trabajos de pintura'),
(16, 3, '2025-06-07 08:00:00', '2025-06-10 17:30:00', 72.00, 'COMPLETADA', '2025-06-06 08:00:00', 'Instalacion de vallas'),
(17, 1, '2025-05-22 09:00:00', '2025-05-25 17:00:00', 26.50, 'CONFIRMADA', '2025-05-21 10:20:00', 'Obra publica'),
(18, 4, '2025-05-24 08:30:00', '2025-05-26 17:30:00', 39.90, 'COMPLETADA', '2025-05-23 09:00:00', 'Uso en taller temporal');

-- Insertar pagos
INSERT INTO pagos (reserva_id, monto, metodo_pago, fecha_pago, estado, referencia_pago) VALUES
(1, 29.95, 'EFECTIVO', '2025-05-19 10:00:00', 'COMPLETADO', '245Y264Y6TEYBYT'),
(2, 15.99, 'TARJETA_CREDITO', '2025-05-20 09:30:00', 'PENDIENTE', '425Y246HRETBRTEB'),
(3, 45.50, 'TRANSFERENCIA', '2025-05-31 08:45:00', 'COMPLETADO', '25T425GRBRTBRT'),
(4, 0.00, 'TRANSFERENCIA', '2025-05-29 14:00:00', 'FALLIDO', 'rtnrwtnwrnrn'),
(5, 60.00, 'EFECTIVO', '2025-05-24 16:10:00', 'COMPLETADO', 'TRBRTNTRNTRNY'),
(6, 0.00, 'TARJETA_DEBITO', '2025-05-26 17:30:00', 'PENDIENTE', 'ARYNRYN456565TN'),
(7, 50.00, 'TRANSFERENCIA', '2025-05-28 13:00:00', 'COMPLETADO', 'RYNRYNET5635'),
(8, 0.00, 'TARJETA_CREDITO', '2025-05-30 11:30:00', 'FALLIDO', 'TYNTEYN356356N'),
(9, 40.00, 'EFECTIVO', '2025-06-01 10:15:00', 'COMPLETADO', '5N6J35NTNTNY'),
(10, 37.25, 'TARJETA_DEBITO', '2025-05-20 08:50:00', 'PENDIENTE', '53N567N6TNYUN'),
(11, 22.80, 'TRANSFERENCIA', '2025-05-20 15:25:00', 'COMPLETADO', 'N365N67NRTJNRHJ'),
(12, 66.60, 'EFECTIVO', '2025-06-04 17:00:00', 'COMPLETADO', '53J7JRNTUNY'),
(13, 0.00, 'EFECTIVO', '2025-05-27 12:00:00', 'FALLIDO', '53575JTYJTRJ'),
(14, 48.88, 'TARJETA_CREDITO', '2025-06-02 10:40:00', 'PENDIENTE', '56JTRJTRJRTYNGHN'),
(15, 72.00, 'TRANSFERENCIA', '2025-06-06 09:15:00', 'COMPLETADO', '56J56JTYNTYNTY'),
(16, 26.50, 'EFECTIVO', '2025-05-21 14:30:00', 'COMPLETADO', '356J53JTNJTN'),
(17, 39.90, 'EFECTIVO', '2026-05-23 13:20:00', 'PENDIENTE', '536H357JHTYNTYN');


