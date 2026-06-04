package io.github.williamledo.icompras.pedidos.client.representation;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoRepresentation {

	private Long codigo;
	private String nome;
	private BigDecimal valorUnitario;
	
	
}
