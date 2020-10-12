package com.rns.testes.java.repository;

import com.rns.testes.java.model.Estoque;
import com.rns.testes.java.model.EstoquePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstoqueRepository extends JpaRepository<Estoque, EstoquePk> {
}
