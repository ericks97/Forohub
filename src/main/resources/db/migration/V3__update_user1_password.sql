-- V3__update_user_password.sql
-- Actualiza la contrasena del usuario 'Prueba' con la version hasheada  (Para probar en POSTMAN utiliza 1234
UPDATE usuarios SET contrasena = '$2a$10$840yYbea4ahTdGwGiiAA/.UFFAKkJ4olOj4HGlfAjsXLR7IXY/Vw.' WHERE correo_electronico = 'test@test.com';