package io.github.williamledo.icompras.pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.williamledo.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.williamledo.icompras.pedidos.controller.mapper.PedidoMapper;
import io.github.williamledo.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService service;
	private final PedidoMapper mapper;
	
	@PostMapping
	public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO dto ) {

		var pedido = mapper.map(dto);
		
		var novoPedido = service.criarPedido(pedido);
		
		return ResponseEntity.ok(novoPedido.getCodigo());
		
	}
	
}
