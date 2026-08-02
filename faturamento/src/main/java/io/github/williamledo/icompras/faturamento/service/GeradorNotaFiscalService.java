package io.github.williamledo.icompras.faturamento.service;

import java.io.ByteArrayInputStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import io.github.williamledo.icompras.faturamento.bucket.BucketFile;
import io.github.williamledo.icompras.faturamento.bucket.BucketService;
import io.github.williamledo.icompras.faturamento.model.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class GeradorNotaFiscalService {

	private final NotaFiscalService notaFiscalService;
	private final BucketService bucketService;

	public void gerar(Pedido pedido) {

		try {
			
			log.info("Gerando nota fiscal para o pedido: {}", pedido.codigo());

			byte[] byteArray = notaFiscalService.gerarNota(pedido);

			String nomeArquivo = String.format("notafiscal-pedido-%d.pdf", pedido.codigo());

			var file = new BucketFile(nomeArquivo, new ByteArrayInputStream(byteArray), MediaType.APPLICATION_PDF,
					byteArray.length);

			bucketService.upload(file);
			
			
			
			log.info("Nota fiscal gerada, nome do arquivo: {}, tamanho: {} bytes", nomeArquivo, byteArray.length);
			
		}catch (Exception e) {
			log.error("Erro ao gerar nota fiscal para o pedido {}", pedido.codigo(), e);
		}

	}

}
