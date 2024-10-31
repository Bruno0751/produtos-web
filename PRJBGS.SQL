create DATABASE db_produtos;

USE db_produtos;

show tables;

create table db_produtos.autorizacao (
id_autorizacao int(8) auto_increment not null,
sid varchar(256) not null,
constraint pk_id_autorizaca primary key (id_autorizacao)
);
select * from db_produtos.autorizacao;

###################################################################################################

create table db_produtos.produto (
id_produto int(8) auto_increment not null,
nome varchar(100) not null,
categoria enum('FRUTA', 'OUTROS') not null,
quantidade int not null,
preco decimal(10.2) null,
constraint pk_id_produto primary key (id_produto)
);
insert into db_produtos.produto values
(null, 'BANANA', 'FRUTA', 50, 22.22),
(null, 'ABACAXI', 'FRUTA', 21, 33.33),
(null, 'ABACATE', 'FRUTA', 49, 455.32);
select * from db_produtos.produto;

###################################################################################################

create table db_produtos.pessoa (
id_pessoa int(8) auto_increment not null,
nome varchar(100) not null,
documento VARCHAR(14) not null UNIQUE,
tipo enum('ADM', 'FORNECEDOR') not null,
data_registro timestamp not null,
constraint pk_id_pessoa primary key (id_pessoa)
);
insert into db_produtos.pessoa values
(null, 'BRUNO GRESSLER DA SILVEIRA', '03154619003', 'ADM', NOW()),
(null, 'ALESSANDRO GRESSLER DA SILVEIRA', '00000000000', 'FORNECEDOR', NOW());
select * from db_produtos.pessoa where tipo like 'FORNECEDOR';

###################################################################################################

create table db_produtos.historico_compra (
id_historico_compra int(8) auto_increment not null,
nome varchar(100) not null,
documento VARCHAR(14) not null UNIQUE,
aniversario date null,
tipo enum('ADM', 'FORNECEDOR') not null,
data_registro timestamp not null,
constraint pk_id_historico_compra primary key (id_historico_compra)
)

drop table db_produtos.pessoa;
-- drop table db_produtos.produto;

##############################################################################################

