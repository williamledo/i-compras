package io.github.williamledo.icompras.pedidos.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.williamledo.icompras.pedidos.client.ServicoBancarioClient;
import io.github.williamledo.icompras.pedidos.exception.ItemNaoEncontradoException;
import io.github.williamledo.icompras.pedidos.model.DadosPagamento;
import io.github.williamledo.icompras.pedidos.model.Pedido;
import io.github.williamledo.icompras.pedidos.model.TipoPagamento;
import io.github.williamledo.icompras.pedidos.model.enums.StatusPedido;
import io.github.williamledo.icompras.pedidos.repository.ItemPedidoRepository;
import io.github.williamledo.icompras.pedidos.repository.PedidoRepository;
import io.github.williamledo.icompras.pedidos.validator.PedidoValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final ItemPedidoRepository itemPedidoRepository;
	private final PedidoValidator pedidoValidator;
	private final ServicoBancarioClient servicoBancarioClient;
	
	@Transactional
	public Pedido criarPedido(Pedido pedido) {
		
		pedidoValidator.validar(pedido);
		
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setTotal(calcularTotal(pedido));
		pedido.getItens().forEach(item -> item.setPedido(pedido));
		
		realizarPersistencia(pedido);
		enviarSolicitacaoPagamento(pedido);
		
		return pedido;
		
	}

	private void enviarSolicitacaoPagamento(Pedido pedido) {
		var chavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
		pedido.setChavePagamento(chavePagamento);
	}

	private void realizarPersistencia(Pedido pedido) {
		pedidoRepository.save(pedido);
		itemPedidoRepository.saveAll(pedido.getItens());
	}

	private BigDecimal calcularTotal(Pedido pedido) {
		return pedido.getItens().stream()
				.map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void atualizarStatusPagamento(
			Long codigoPedido, String chavePagamento, boolean sucesso, String observacoes) {
		
		var pedidoEncontrado = pedidoRepository.findByCodigoAndChavePagamento(codigoPedido, chavePagamento);
		
		if(pedidoEncontrado.isEmpty()) {
			var msg= String.format("Pedido não encontrado para o código %d e chave pgmto %s", codigoPedido, chavePagamento);
			log.error(msg);
			return;
		}
		
		Pedido pedido = pedidoEncontrado.get();
		
		if(sucesso) {
			pedido.setStatus(StatusPedido.PAGO);
		}else {
			pedido.setStatus(StatusPedido.ERRO_PAGAMENTO);
			pedido.setObservacoes(observacoes);
		}
		
		pedidoRepository.save(pedido);
		
	}
	
	@Transactional
	public void adicionarNovoPagamento(Long codigoPedido, String dadosCartao, TipoPagamento tipoPagamento) {

		var pedidoEncontrado = pedidoRepository.findById(codigoPedido);
		
		if (pedidoEncontrado.isEmpty()) {
			throw new ItemNaoEncontradoException("Pedido não encontrado para o código informado");
		}
		
		var pedido = pedidoEncontrado.get();
		
		DadosPagamento dadosPagamento = new DadosPagamento();
		
		dadosPagamento.setTipoPagamento(tipoPagamento);
		dadosPagamento.setDados(dadosCartao);
		
		pedido.setDadosPagamento(dadosPagamento);
		
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setObservacoes("Novo pagamento realizado, aguardando novo processamento");
		
		
		String novaChavePagamento = servicoBancarioClient.solicitarPagamento(pedido);
		
		pedido.setChavePagamento(novaChavePagamento);
		
		pedidoRepository.save(pedido);
		
	}
	
}
