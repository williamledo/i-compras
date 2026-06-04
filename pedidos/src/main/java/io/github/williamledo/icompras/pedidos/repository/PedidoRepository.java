package io.github.williamledo.icompras.pedidos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.williamledo.icompras.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	Optional<Pedido> findByCodigoAndChavePagamento(Long codigo, String chavePagamento);
	
}
