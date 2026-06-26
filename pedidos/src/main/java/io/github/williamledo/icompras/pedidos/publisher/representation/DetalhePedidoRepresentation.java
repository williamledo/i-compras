package io.github.williamledo.icompras.pedidos.publisher.representation;

import java.math.BigDecimal;
import java.util.List;

import io.github.williamledo.icompras.pedidos.model.enums.StatusPedido;

public record DetalhePedidoRepresentation(
		Long codigo,
		Long codigoCliente,
		String nome,
		String cpf,
		String logradouro,
		String numero,
		String bairro,
		String email,
		String telefone,
		String dataPedido,
		BigDecimal total,
		StatusPedido status,
		List<DetalheItemPedidoRepresentation> itens
) {

}
