CREATE DATABASE tienda_db;

-- Conectar a la BD:
-- \c tienda_db;

-- =========================
-- TABLAS DEL NEGOCIO
-- =========================

CREATE TABLE clientes (
    id SERIAL PRIMARY KEY,
    nombres VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    telefono CHAR(9) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    descripcion VARCHAR(150) NOT NULL,
    precio_unitario NUMERIC(10,2) NOT NULL,
    stock INT NOT NULL,
    estado VARCHAR(30) NOT NULL,
    img_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE ventas (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATE NOT NULL,
    total NUMERIC(10,2) NOT NULL,
    estado VARCHAR(30) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ventas_clientes
        FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

CREATE TABLE detalle_ventas (
    id SERIAL PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_detalle_ventas_ventas
        FOREIGN KEY (venta_id) REFERENCES ventas(id),
    CONSTRAINT fk_detalle_ventas_productos
        FOREIGN KEY (producto_id) REFERENCES productos(id)
);

-- =========================
-- INSERTS DEL NEGOCIO
-- =========================

INSERT INTO clientes (nombres, apellidos, dni, email, telefono, direccion) VALUES
('Carlos', 'Ramirez Soto', '12345678', 'carlos.ramirez@gmail.com', '987654321', 'Av. España 123'),
('Lucia', 'Fernandez Ruiz', '87654321', 'lucia.fernandez@gmail.com', '912345678', 'Jr. Pizarro 456'),
('Miguel', 'Torres Vega', '45678912', 'miguel.torres@gmail.com', '956123789', 'Urb. Primavera 789');

INSERT INTO productos (descripcion, precio_unitario, stock, estado, img_url) VALUES
('Laptop Lenovo IdeaPad', 2500.00, 10, 'DISPONIBLE', 'https://http2.mlstatic.com/D_NQ_NP_756520-MLA99510228944_112025-O.webp'),
('Mouse Logitech', 80.00, 50, 'DISPONIBLE', 'https://www.syscaribe.com/wp-content/uploads/2024/02/D_NQ_NP_2X_830336-MLU72605986040.png'),
('Teclado Mecánico Redragon', 180.00, 25, 'DISPONIBLE', 'https://redragon.es/content/uploads/2021/04/YAMA-BLACK.png');

INSERT INTO ventas (cliente_id, fecha, total, estado) VALUES
(1, '2026-04-25', 2580.00, 'REGISTRADA'),
(2, '2026-04-25', 180.00, 'REGISTRADA'),
(3, '2026-04-25', 260.00, 'REGISTRADA');

INSERT INTO detalle_ventas (venta_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
(1, 1, 1, 2500.00, 2500.00),
(1, 2, 1, 80.00, 80.00),
(2, 3, 1, 180.00, 180.00);