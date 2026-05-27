package io.github.williamledo.icompras.pedidos.controller.dto;

import io.github.williamledo.icompras.pedidos.model.TipoPagamento;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosPagamentoDTO {

	private String dados;
	private TipoPagamento tipoPagamento;

}
