package io.github.williamledo.icompras.pedidos.controller.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NovoPedidoDTO {

	private Long codigoCliente;
	private DadosPagamentoDTO dadosPagamento;
	private List<ItemPedidoDTO> itens;


}
