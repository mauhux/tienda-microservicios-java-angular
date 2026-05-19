CREATE DATABASE venta_db;

-- Conectar a la BD:
-- \c venta_db;

-- =========================
-- TABLAS DEL NEGOCIO
-- =========================

CREATE TABLE ventas (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATE NOT NULL,
    total NUMERIC(10,2) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE detalle_ventas (
    id SERIAL PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    producto_descripcion VARCHAR(150) NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_detalle_ventas_ventas
        FOREIGN KEY (venta_id) REFERENCES ventas(id)
);

-- =========================
-- INSERTS DEL NEGOCIO
-- =========================

INSERT INTO ventas (cliente_id, fecha, total, estado) VALUES
(1, '2026-04-25', 2580.00, 'REGISTRADA'),
(2, '2026-04-25', 180.00, 'REGISTRADA'),
(3, '2026-04-25', 260.00, 'REGISTRADA');

INSERT INTO detalle_ventas (venta_id, producto_id, producto_descripcion, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 'Laptop Lenovo IdeaPad', 1, 2500.00, 2500.00),
(1, 2, 'Mouse Logitech', 1, 80.00, 80.00),

(2, 3, 'Teclado Mecánico Redragon', 1, 180.00, 180.00),

(3, 2, 'Mouse Logitech', 1, 80.00, 80.00),
(3, 3, 'Teclado Mecánico Redragon', 1, 180.00, 180.00);

-- =========================
-- CONSULTAS
-- =========================

SELECT * FROM ventas;

SELECT * FROM detalle_ventas;

