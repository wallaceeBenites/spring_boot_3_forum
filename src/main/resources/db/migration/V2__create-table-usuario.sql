create table usuario(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,
    data_abertura_conta varchar(10) not null ,
    primary key(id)

);