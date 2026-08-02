package io.github.williamledo.icompras.faturamento.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import io.github.williamledo.icompras.faturamento.model.Pedido;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class NotaFiscalService {

	@Value("classpath:reports/nota-fiscal.jrxml")
	private Resource notaFiscal;
	
	@Value("classpath:reports/images.jpg")
	private Resource logo;
	
	public byte[] gerarNota(Pedido pedido) {

		try (InputStream inputStream = notaFiscal.getInputStream()){
			
			Map<String, Object> params = new HashMap<>();
			params.put("NOME", pedido.cliente().nome());
			params.put("CPF", pedido.cliente().cpf());
			params.put("LOGRADOURO", pedido.cliente().logradouro());
			params.put("NUMERO", pedido.cliente().numero());
			params.put("BAIRRO", pedido.cliente().bairro());
			params.put("EMAIL", pedido.cliente().email());
			params.put("TELEFONE", pedido.cliente().telefone());
			
			params.put("DATA_PEDIDO", pedido.data());
			params.put("TOTAL_PEDIDO", pedido.total());
			params.put("LOGO", logo.getURL().toString());
			
			var dataSource = new JRBeanCollectionDataSource(pedido.itens());
			
			JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
			
			return JasperExportManager.exportReportToPdf(jasperPrint);
			
		}catch (Exception e) {
			log.error("Falha ao montar PDF da nota fiscal para pedido {}", pedido.codigo(), e);
			throw new RuntimeException("Erro ao gerar nota fiscal", e);
		}
	}
	
}
