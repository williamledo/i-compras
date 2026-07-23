package io.github.williamledo.icompras.faturamento.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.williamledo.icompras.faturamento.model.Cliente;
import io.github.williamledo.icompras.faturamento.model.ItemPedido;
import io.github.williamledo.icompras.faturamento.model.Pedido;
import io.github.williamledo.icompras.faturamento.subscriber.representation.DetalheItemPedidoRepresentation;
import io.github.williamledo.icompras.faturamento.subscriber.representation.DetalhePedidoRepresentation;

@Component
public class PedidoMapper {

	public Pedido map(DetalhePedidoRepresentation representation) {
		
		Cliente cliente = new Cliente(
				representation.nome(),
				representation.cpf(),
				representation.logradouro(),
				representation.numero(),
				representation.bairro(),
				representation.email(),
				representation.telefone()
		);
		
		List<ItemPedido> itens = representation.itens().stream().map(this::mapItem).toList();
		
		return new Pedido(
				representation.codigo(),
				cliente,
				representation.dataPedido(),
				representation.total(),
				itens
		);
		
	}
	
	private ItemPedido mapItem(DetalheItemPedidoRepresentation representation) {
		return new ItemPedido(representation.codigoProduto(), representation.nome(),
				representation.valorUnitario(), representation.quantidade(), representation.total());
	}
	
}
