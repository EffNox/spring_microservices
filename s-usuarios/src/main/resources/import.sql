INSERT INTO usuario (username, password, enabled, nombre, apellido, correo) VALUES ('fernando','$2a$10$ToM1bFN/l24ZWnDZ5SkIbedwtAUmVwtGPpW7dE0dIChvX4dImCy2G',true, 'Fernando', 'Ticona','hdxtremo@bolsadeideas.com');
INSERT INTO usuario (username, password, enabled, nombre, apellido, correo) VALUES ('admin','$2a$10$8LGBnsB8gqzuO7UFiIzDEuFNlJflNiSaofP5gyJnudvBdlFwm.vdy',true, 'John', 'Doe','jhon.doe@bolsadeideas.com');

INSERT INTO perfil (nombre) VALUES ('ROLE_USER');
INSERT INTO perfil (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_perfiles (usuario_id, perfiles_id) VALUES (1, 1);
INSERT INTO usuario_perfiles (usuario_id, perfiles_id) VALUES (2, 2);
INSERT INTO usuario_perfiles (usuario_id, perfiles_id) VALUES (2, 1);
