package io.github.williamledo.icompras.produtos.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.williamledo.icompras.produtos.model.Produto;
import io.github.williamledo.icompras.produtos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository repository;
	
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}
	
	public Optional<Produto> buscarPorCodigo(Long codigo) {
		return repository.findById(codigo);
	}
	
}
