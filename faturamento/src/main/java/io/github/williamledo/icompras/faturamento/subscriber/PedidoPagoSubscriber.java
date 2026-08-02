package io.github.williamledo.icompras.faturamento.subscriber;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.williamledo.icompras.faturamento.mapper.PedidoMapper;
import io.github.williamledo.icompras.faturamento.model.Pedido;
import io.github.williamledo.icompras.faturamento.service.GeradorNotaFiscalService;
import io.github.williamledo.icompras.faturamento.subscriber.representation.DetalhePedidoRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PedidoPagoSubscriber {

	private final ObjectMapper mapper;
	private final GeradorNotaFiscalService service;
	private final PedidoMapper pedidoMapper;
	
	@KafkaListener(
			topics = "${icompras.config.kafka.topic.faturamento}",
			groupId = "${spring.kafka.consumer.group-id}"
	)
	
	public void listen(String json) {
		
		try {
			
			log.info("Recebendo pedido para faturamento: {}", json);
			
			var representation = mapper.readValue(json, DetalhePedidoRepresentation.class);
			
			Pedido pedido = pedidoMapper.map(representation);
			service.gerar(pedido);
			
		}catch(Exception e) {
			log.error("Erro na consumação do topico de pedidos pagos", e);
		}
		
		
		
	}
	
}
