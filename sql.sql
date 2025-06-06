CREATE TABLE fornecedor(
	id_for serial,
	nome_for character varying(50),
	cnpj_for character varying(18),
	tel_for character varying(14),
	data_cad_for date,
	CONSTRAINT fornecedor_pkey PRIMARY KEY (id_for)
)

CREATE TABLE produto(
	id_prod serial,
	nome_prod character varying(50),
	desc_prod character varying(50),
	cod_bar_prod character varying(13),
	p_custo_prod double precision,
	p_venda_prod double precision,
	id_for integer,
	CONSTRAINT produto_pkey PRIMARY KEY (id_prod),
	CONSTRAINT fornecedor_fk FOREIGN KEY (id_for) REFERENCES fornecedor (id_for)
)

CREATE TABLE cliente(
	id_cli serial,
	nome_cli character varying(50),
	logradouro_cli character varying(50),
	numero_cli integer,
	bairro_cli character varying(30),
	cidade_cli character varying(30),
	estado_cli character varying(2),
	cep_cli character varying(9),
	cpf_cli character varying(14) unique,
	rg_cli character varying(15),
	CONSTRAINT cliente_pkey PRIMARY KEY (id_cli)
)

CREATE TABLE venda(
    id_vend SERIAL,
    dat_vend DATE,
    val_vend DOUBLE PRECISION,
    id_cli INTEGER,
    CONSTRAINT venda_pkey PRIMARY KEY (id_vend),
    CONSTRAINT venda_id_cli_fkey FOREIGN KEY (id_cli) REFERENCES cliente (id_cli)
);

CREATE TABLE produto_venda(
    id_prod INTEGER,
    id_vend INTEGER,
    val_prod DOUBLE PRECISION,
    qtd_prod INTEGER,
    CONSTRAINT produto_venda_id_prod_fkey FOREIGN KEY (id_prod) REFERENCES produto (id_prod),
    CONSTRAINT produto_venda_id_vend_fkey FOREIGN KEY (id_vend) REFERENCES venda (id_vend)
);

drop table produto_venda
drop table venda
drop table cliente

select * from cliente