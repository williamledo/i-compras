package io.github.williamledo.icompras.pedidos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import io.github.williamledo.icompras.pedidos.model.Pedido;
import io.github.williamledo.icompras.pedidos.model.enums.StatusPedido;
import io.github.williamledo.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.williamledo.icompras.pedidos.repository.PedidoRepository;
import io.github.williamledo.icompras.pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	
	private final PedidoValidator pedidoValidator;
	
	public Pedido criarPedido(Pedido pedido) {
		
//		pedidoValidator.validar(pedido);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setTotal(calcularTotal(pedido));
		pedido.getItens().forEach(item -> item.setPedido(pedido));
		
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
		
	}

	private BigDecimal calcularTotal(Pedido pedido) {
		return pedido.getItens().stream()
				.map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
