package io.github.williamledo.icompras.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.williamledo.icompras.clientes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
