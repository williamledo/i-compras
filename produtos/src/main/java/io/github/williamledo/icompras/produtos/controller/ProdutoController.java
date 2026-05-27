package io.github.williamledo.icompras.produtos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.williamledo.icompras.produtos.model.Produto;
import io.github.williamledo.icompras.produtos.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoService service;
	
	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
		service.salvar(produto);
		return ResponseEntity.ok(produto);
	}
	
	@GetMapping("{codigo}")
	public ResponseEntity<Produto> obterDados(@PathVariable("codigo") Long codigo) {
		return service
				.buscarPorCodigo(codigo)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
