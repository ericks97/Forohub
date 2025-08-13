-- =================================================================
-- V1__Create_initial_schema.sql
-- Script de migración inicial para la base de datos de ForoHub
-- Respetando las dependencias de las claves foraneas.
-- =================================================================

-- 1. TABLAS MAESTRAS (SIN DEPENDENCIAS)
-- -----------------------------------------------------------------

-- Creación de la tabla de cursos
CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- Creación de la tabla de perfiles (roles)
CREATE TABLE perfiles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

-- Creación de la tabla de usuarios
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(255) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);


-- 2. TABLAS DE UNIÓN Y TABLAS CON DEPENDENCIAS DE PRIMER NIVEL
-- -----------------------------------------------------------------

-- Creación de la tabla de unión para la relación Muchos a Muchos con Perfil
-- Depende de: usuarios, perfiles
CREATE TABLE usuarios_perfiles (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,
    PRIMARY KEY (usuario_id, perfil_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (perfil_id) REFERENCES perfiles(id)
);

-- Creación de la tabla de topicos
-- Depende de: usuarios, cursos
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    status BOOLEAN NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    FOREIGN KEY (curso_id) REFERENCES cursos(id)
);


-- 3. TABLAS CON DEPENDENCIAS DE SEGUNDO NIVEL
-- -----------------------------------------------------------------

-- Creación de la tabla de respuestas
-- Depende de: topicos, usuarios
CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    topico_id BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    autor_id BIGINT NOT NULL,
    solucion TEXT,
    PRIMARY KEY (id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id),
    FOREIGN KEY (autor_id) REFERENCES usuarios(id)
);