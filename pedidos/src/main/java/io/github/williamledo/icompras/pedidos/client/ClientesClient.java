package io.github.williamledo.icompras.pedidos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.williamledo.icompras.pedidos.client.representation.ClienteRepresentation;

@FeignClient(name = "clientes", url = "${icompras.pedidos.clients.clientes.url}")
public interface ClientesClient {

	@GetMapping("{codigo}")
	public ResponseEntity<ClienteRepresentation> obterDados(@PathVariable Long codigo);
	
}
