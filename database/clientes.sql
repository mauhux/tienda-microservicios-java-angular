CREATE DATABASE cliente_db;

-- Conectar a la BD:
-- \c cliente_db;

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


-- =========================
-- INSERTS DEL NEGOCIO
-- =========================

INSERT INTO clientes (nombres, apellidos, dni, email, telefono, direccion) VALUES
('Carlos', 'Ramirez Soto', '12345678', 'carlos.ramirez@gmail.com', '987654321', 'Av. España 123'),
('Lucia', 'Fernandez Ruiz', '87654321', 'lucia.fernandez@gmail.com', '912345678', 'Jr. Pizarro 456'),
('Miguel', 'Torres Vega', '45678912', 'miguel.torres@gmail.com', '956123789', 'Urb. Primavera 789');

-- =========================
-- CONSULTAS
-- =========================

SELECT * FROM clientes;