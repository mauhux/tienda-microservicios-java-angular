CREATE DATABASE producto_db;

-- Conectar a la BD:
-- \c producto_db;

-- =========================
-- TABLAS DEL NEGOCIO
-- =========================

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


-- =========================
-- INSERTS DEL NEGOCIO
-- =========================

INSERT INTO productos (descripcion, precio_unitario, stock, estado, img_url) VALUES
('Laptop Lenovo IdeaPad', 2500.00, 10, 'DISPONIBLE', 'https://http2.mlstatic.com/D_NQ_NP_756520-MLA99510228944_112025-O.webp'),
('Mouse Logitech', 80.00, 50, 'DISPONIBLE', 'https://www.syscaribe.com/wp-content/uploads/2024/02/D_NQ_NP_2X_830336-MLU72605986040.png'),
('Teclado Mecánico Redragon', 180.00, 25, 'DISPONIBLE', 'https://redragon.es/content/uploads/2021/04/YAMA-BLACK.png');

-- =========================
-- CONSULTAS
-- =========================

SELECT * FROM productos;