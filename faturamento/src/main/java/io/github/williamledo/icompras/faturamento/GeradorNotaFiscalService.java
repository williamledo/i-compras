package io.github.williamledo.icompras.faturamento;

import org.springframework.stereotype.Component;

import io.github.williamledo.icompras.faturamento.model.Pedido;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class GeradorNotaFiscalService {

	public void gerar(Pedido pedido) {
		log.info("Gerando nota fiscal para o pedido: {}", pedido.codigo());
	}
	
}
