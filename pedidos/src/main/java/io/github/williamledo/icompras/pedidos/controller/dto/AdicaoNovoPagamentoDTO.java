package io.github.williamledo.icompras.pedidos.controller.dto;

import io.github.williamledo.icompras.pedidos.model.TipoPagamento;

public record AdicaoNovoPagamentoDTO(
		Long codigoPedido, String dados, TipoPagamento tipoPagamento) {

}
