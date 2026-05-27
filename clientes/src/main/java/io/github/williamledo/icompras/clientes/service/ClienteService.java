package io.github.williamledo.icompras.clientes.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import io.github.williamledo.icompras.clientes.model.Cliente;
import io.github.williamledo.icompras.clientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> obterPorCodigo(Long codigo) {
		return clienteRepository.findById(codigo);
	}
	
}
