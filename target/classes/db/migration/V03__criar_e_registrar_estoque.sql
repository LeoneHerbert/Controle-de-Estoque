CREATE TABLE estoque (
     filial_id INT NOT NULL,
     produto_id VARCHAR(20) NOT NULL,
     quantidade_produtos INT(20) NOT NULL,
     data_ult_alteracao DATE NOT NULL,
     versao INT NOT NULL,
     CONSTRAINT produto_id KEY (produto_id),
     CONSTRAINT filial_id KEY (filial_id)

);

insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (1, 'Cod-Produto-5', 1000, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao)values (1, 'Cod-Produto-3', 1000, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (2, 'Cod-Produto-14', 2000, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (2, 'Cod-Produto-18', 60, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (3, 'Cod-Produto-12', 5000, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (3, 'Cod-Produto-9', 100, current_timestamp, 0);

insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (4, 'Cod-Produto-20', 200, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (4, 'Cod-Produto-7', 3000, current_timestamp, 0);
insert into estoque (filial_id, produto_id, quantidade_produtos, data_ult_alteracao, versao) values (4, 'Cod-Produto-11', 400, current_timestamp, 0);