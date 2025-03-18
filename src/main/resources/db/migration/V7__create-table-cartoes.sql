create table cartoes (
    id bigint not null auto_increment,
    numero varchar(16) not null,
    cliente varchar(100) not null,
    validade varchar(7) not null,
    cvv varchar(3) not null,
    limite decimal(10, 2) not null,
    status varchar(9) not null check (status in ('ATIVO', 'CANCELADO')),

    primary key(id)
);