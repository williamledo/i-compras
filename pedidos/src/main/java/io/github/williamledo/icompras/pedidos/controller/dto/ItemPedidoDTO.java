package io.github.williamledo.icompras.pedidos.controller.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {

	private Long codigoProduto;
	private Integer quantidade;
	private BigDecimal valorUnitario;

}
