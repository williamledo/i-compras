package io.github.williamledo.icompras.pedidos.validator;

import org.springframework.stereotype.Component;

import feign.FeignException;
import io.github.williamledo.icompras.pedidos.client.ClientesClient;
import io.github.williamledo.icompras.pedidos.client.ProdutosClient;
import io.github.williamledo.icompras.pedidos.client.representation.ClienteRepresentation;
import io.github.williamledo.icompras.pedidos.client.representation.ProdutoRepresentation;
import io.github.williamledo.icompras.pedidos.exception.ValidationException;
import io.github.williamledo.icompras.pedidos.model.ItemPedido;
import io.github.williamledo.icompras.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoValidator {

	private final ProdutosClient produtosClient;
	private final ClientesClient clientesClient;

	public void validar(Pedido pedido) {

		Long codigoCliente = pedido.getCodigoCliente();

		validarCliente(codigoCliente);
		pedido.getItens().forEach(this::validarItem);

	}

	private void validarCliente(Long codigoCliente) {

		try {

			var response = clientesClient.obterDados(codigoCliente);
			ClienteRepresentation cliente = response.getBody();
			log.info("Cliente de codigo {} encontrado: {}", cliente.getCodigo(), cliente.getNome());

		} catch (FeignException.NotFound e) {

			var message = String.format("Cliente de código %d não encontrado.", codigoCliente);

			throw new ValidationException("codigoCliente", message);
		}

	}

	private void validarItem(ItemPedido item) {

		try {

			var response = produtosClient.obterDados(item.getCodigoProduto());
			ProdutoRepresentation produto = response.getBody();
			log.info("Produto de codigo {} encontrado: {}", produto.getCodigo(), produto.getNome());

		} catch (FeignException.NotFound e) {

			var message = String.format("Produto de código %d não encontrado.", item.getCodigoProduto());

			throw new ValidationException("codigoProduto", message);

		}

	}

}
