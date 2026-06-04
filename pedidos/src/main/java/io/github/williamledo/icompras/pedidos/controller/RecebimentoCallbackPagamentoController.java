package io.github.williamledo.icompras.pedidos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.williamledo.icompras.pedidos.controller.dto.RecebimentoCallbackPagamentoDTO;
import io.github.williamledo.icompras.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pedidos/callback-pagamentos")
@RequiredArgsConstructor
public class RecebimentoCallbackPagamentoController {

	private final PedidoService pedidoService;
	
	public ResponseEntity<Object> atualizarStatusPagamento(
			@RequestBody RecebimentoCallbackPagamentoDTO body,
			@RequestHeader(required=true, name="apiKey") String apiKey) {
		
		pedidoService.atualizarStatusPagamento(
				body.codigo(),
				body.chavePagamento(),
				body.status(),
				body.observacoes()
				);
		
		return ResponseEntity.ok().build();
	}
	
}
