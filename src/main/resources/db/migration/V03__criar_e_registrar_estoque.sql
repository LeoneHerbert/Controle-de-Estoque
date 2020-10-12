CREATE TABLE estoque (
  quantidade_produtos INT NOT NULL,
  data_ult_alteracao DATE NOT NULL,
  versao INT NOT NULL,
  filial_id INT NOT NULL,
  produto_id INT NOT NULL,

  KEY (filial_id),
  PRIMARY KEY (produto_id),

  CONSTRAINT fkEstoque_filialId FOREIGN KEY (filial_id)
    REFERENCES filial(id),
  CONSTRAINT fkEstoque_produtoId FOREIGN KEY (produto_id)
    REFERENCES produto(id)

)engine=InnoDB DEFAULT CHARSET=utf8;

insert into estoque (filial_id, produto_id, quantidade_produtos) values (1, "Cod-Produto-5", 1000);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (1, "Cod-Produto-3", 1000);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (2, "Cod-Produto-14", 2000);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (2, "Cod-Produto-18", 60);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (3, "Cod-Produto-12", 5000);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (3, "Cod-Produto-9", 100);

insert into estoque (filial_id, produto_id, quantidade_produtos) values (4, "Cod-Produto-20", 200);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (4, "Cod-Produto-7", 3000);
insert into estoque (filial_id, produto_id, quantidade_produtos) values (4, "Cod-Produto-11", 400);