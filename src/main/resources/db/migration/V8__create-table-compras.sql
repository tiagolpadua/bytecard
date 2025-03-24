create table compras (
    id bigint not null auto_increment,
    valor decimal(10, 2) not null,
    data_hora timestamp not null,
    estabelecimento varchar(100) not null,
    categoria varchar(50) not null,
    cartao_id bigint not null,

    primary key (id),
    foreign key (cartao_id) references cartoes(id)
);