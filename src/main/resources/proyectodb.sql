-- Insertar usuarios (Admin, Proveedor, Cliente)
INSERT INTO usuarios (email, password, nombre, telefono, role, activo, fecha_registro) 
VALUES 
('admin@mail.com', 'admin123', 'Administrador', '123456789', 'ADMIN', true, NOW()),
('proveedor@mail.com', 'proveedor123', 'Proveedor Principal', '987654321', 'PROVEEDOR', true, NOW()),
('cliente@mail.com', 'cliente123', 'Cliente Ejemplo', '456123789', 'CLIENTE', true, NOW());

-- Insertar herramientas
INSERT INTO herramientas (nombre, descripcion, categoria, precio_dia, estado, imagen_url, proveedor_id, fecha_registro) 
VALUES 
('Martillo', 'Martillo de construcción resistente', 'Construcción', 5.99, 'DISPONIBLE', 'imagen_url_1.jpg', 2, NOW()),
('Taladro', 'Taladro eléctrico con múltiples velocidades', 'Electricidad', 15.99, 'DISPONIBLE', 'imagen_url_2.jpg', 2, NOW());

-- Insertar reservas
INSERT INTO reservas (cliente_id, herramienta_id, fecha_inicio, fecha_fin, total_pago, estado, fecha_reserva, observaciones) 
VALUES 
(3, 1, '2025-05-20', '2025-05-25', 29.95, 'CONFIRMADA', NOW(), 'Uso en obra pequeña'),
(3, 2, '2025-05-22', '2025-05-24', 31.98, 'PENDIENTE', NOW(), 'Uso en remodelación');

-- Insertar pagos
INSERT INTO pagos (reserva_id, monto, metodo_pago, estado, fecha_pago, referencia_pago) 
VALUES 
(1, 29.95, 'TARJETA_CREDITO', 'COMPLETADO', NOW(), 'REF123ABC'),
(2, 31.98, 'TRANSFERENCIA', 'PENDIENTE', NOW(), 'REF456DEF');