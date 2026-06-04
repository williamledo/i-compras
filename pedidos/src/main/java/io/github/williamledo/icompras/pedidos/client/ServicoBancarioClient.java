package io.github.williamledo.icompras.pedidos.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import io.github.williamledo.icompras.pedidos.model.Pedido;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ServicoBancarioClient {

	public String solicitarPagamento(Pedido pedido) {
		
		log.info("Solicitando pagamento para o pedido {}", pedido.getCodigo());
		return UUID.randomUUID().toString();
		
	}
	
}
