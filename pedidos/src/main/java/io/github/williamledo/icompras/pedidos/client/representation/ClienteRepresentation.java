package io.github.williamledo.icompras.pedidos.client.representation;

import lombok.Data;

@Data
public class ClienteRepresentation {

	private Long codigo;
	
	private String nome;
	
	private String cpf;
	
	private String logradouro;
	
	private String numero;
	
	private String bairro;
	
	private String email;
	
	private String telefone;
	
}
