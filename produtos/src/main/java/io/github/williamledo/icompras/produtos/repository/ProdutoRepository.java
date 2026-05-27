package io.github.williamledo.icompras.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.williamledo.icompras.produtos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
