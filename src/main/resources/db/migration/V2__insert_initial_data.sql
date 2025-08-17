-- Insertar un par de cursos para poder usarlos
INSERT INTO cursos(nombre, categoria) VALUES('Spring Boot Básico', 'Desarrollo');
INSERT INTO cursos(nombre, categoria) VALUES('JPA Avanzado', 'Desarrollo');

-- Insertar un perfil/rol básico
INSERT INTO perfiles(nombre) VALUES('ROLE_USER');

-- Insertar un usuario de prueba
INSERT INTO usuarios(nombre, correo_electronico, contrasena) VALUES('Prueba', 'test@test.com', '1234');

-- Asignar el perfil al usuario de prueba
INSERT INTO usuarios_perfiles(usuario_id, perfil_id) VALUES(1, 1);