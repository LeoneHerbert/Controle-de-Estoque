CREATE SEQUENCE FILIAL_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE filial (
             id INT NOT NULL DEFAULT NEXTVAL('FILIAL_SEQ') PRIMARY KEY ,
             cnpj VARCHAR(20) NOT NULL,
             endereco VARCHAR(100) NOT NULL,
             razao_social VARCHAR(100) NOT NULL,
             tipo_filial VARCHAR(12) NOT NULL,
             data_ult_alteracao DATE NOT NULL,
             versao INT NOT NULL,
             PRIMARY KEY (id)
);

insert into filial (cnpj, endereco, razao_social, tipo_filial, data_ult_alteracao, versao) values('83.230.227/0001-50', 'Rua Teste Galpao 187, São Paulo - SP', 'Galpão de estocagem de produtos LTDA', 'DEPOSITO',current_timestamp, 0);

insert into filial (cnpj, endereco, razao_social, tipo_filial, data_ult_alteracao, versao) values('72.286.494/0001-23', 'Av. Paulista, 111, São Paulo - SP', 'Loja São Paulo LTDA', 'LOJA_PF', current_timestamp, 0);

insert into filial (cnpj, endereco, razao_social, tipo_filial, data_ult_alteracao, versao) values('50.935.855/0001-82', 'Av. Barata Ribeiro, 199, Rio de Janeiro - RJ', 'Loja Rio de Janeiro LTDA', 'LOJA_PF', current_timestamp, 0);

insert into filial (cnpj, endereco, razao_social, tipo_filial, data_ult_alteracao, versao) values('82.602.541/0001-54', 'Av. Barata Ribeiro, 124, Rio de Janeiro - RJ', 'Loja To Loja LTDA', 'LOJA_PJ', current_timestamp, 0);
