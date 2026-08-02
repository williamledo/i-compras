package io.github.williamledo.icompras.faturamento.model;

import java.math.BigDecimal;

public class ItemPedido {

	private final Long codigo;
	private final String nome;
	private final BigDecimal valorUnitario;
	private final Integer quantidade;
	private final BigDecimal total;

	public ItemPedido(Long codigo, String nome, BigDecimal valorUnitario, Integer quantidade, BigDecimal total) {
		this.codigo = codigo;
		this.nome = nome;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.total = total;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getTotal() {
		return total;
	}
}
