CREATE TABLE usuarios
(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correo varchar(100) not null,
    login varchar(100) not null,
    clave varchar(300) not null,
    perfil varchar(100) not null,

    primary key(id)
)