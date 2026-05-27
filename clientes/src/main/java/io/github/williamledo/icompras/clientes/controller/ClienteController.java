package io.github.williamledo.icompras.clientes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.williamledo.icompras.clientes.model.Cliente;
import io.github.williamledo.icompras.clientes.service.ClienteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

	private final ClienteService service;
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente cliente) {
		service.salvar(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@GetMapping("{codigo}")
	public ResponseEntity<Cliente> obterDados(@PathVariable Long codigo) {
		return service.obterPorCodigo(codigo)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
}
