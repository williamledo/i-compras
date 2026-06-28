package io.github.williamledo.icompras.pedidos.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.williamledo.icompras.pedidos.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class PagamentoPublisher {

	private final DetalhePedidoMapper mapper;
	private final ObjectMapper objectMapper;
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${icompras.config.kafka.topics.pedidos-pagos}")
	private String topico;
	
	public void publicar (Pedido pedido) {
		
		log.info("Publicando pedido pago: {}", pedido.getCodigo());
		
		try {
			
			var representation = mapper.map(pedido);
			var json = objectMapper.writeValueAsString(representation); //transforma o objeto em json
			
			kafkaTemplate.send(topico, "dados", json);
			
		}catch (JsonProcessingException e) {
			log.error("Erro ao processar o json", e);
		}catch (Exception e) {
			log.error("Erro ao publicar o pedido pago", e);
		}
		
	}
	
}
