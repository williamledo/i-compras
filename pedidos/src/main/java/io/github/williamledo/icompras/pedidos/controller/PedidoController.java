package io.github.williamledo.icompras.pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.williamledo.icompras.pedidos.controller.dto.AdicaoNovoPagamentoDTO;
import io.github.williamledo.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.williamledo.icompras.pedidos.controller.mapper.PedidoMapper;
import io.github.williamledo.icompras.pedidos.exception.ItemNaoEncontradoException;
import io.github.williamledo.icompras.pedidos.exception.ValidationException;
import io.github.williamledo.icompras.pedidos.model.ErroResposta;
import io.github.williamledo.icompras.pedidos.publisher.DetalhePedidoMapper;
import io.github.williamledo.icompras.pedidos.publisher.representation.DetalhePedidoRepresentation;
import io.github.williamledo.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

	private final PedidoService service;
	private final PedidoMapper mapper;
	private final DetalhePedidoMapper detalhePedidoMapper;
	
	@PostMapping
	public ResponseEntity<Object> criar(@RequestBody NovoPedidoDTO dto ) {

		try {
			
			var pedido = mapper.map(dto);
			
			var novoPedido = service.criarPedido(pedido);
			
			return ResponseEntity.ok(novoPedido.getCodigo());
			
		} catch (ValidationException e) {
			var erro = new ErroResposta("Erro validação", e.getField(), e.getMessage());
			return ResponseEntity.badRequest().body(erro);
		}
		
	}
	
	@PostMapping("/pagamentos")
	public ResponseEntity<Object> adicionarNovoPagamento(
			@RequestBody AdicaoNovoPagamentoDTO dto) {
		
		try {
			service.adicionarNovoPagamento(dto.codigoPedido(), dto.dados(), dto.tipoPagamento());
			return ResponseEntity.noContent().build();
			
		}catch(ItemNaoEncontradoException e) {
			var erro = new ErroResposta("Item não encontrado", "codigoPedido", e.getMessage());
			return ResponseEntity.badRequest().body(erro);
			
		}
		
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<DetalhePedidoRepresentation> obterDetalhesPedido(@PathVariable Long codigo) {

		return service
				.carregarDadosCompletosPedido(codigo)
				.map(detalhePedidoMapper::map)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
}
