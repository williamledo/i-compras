package io.github.williamledo.icompras.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.williamledo.icompras.pedidos.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
