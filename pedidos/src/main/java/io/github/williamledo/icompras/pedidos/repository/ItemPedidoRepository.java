package io.github.williamledo.icompras.pedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.williamledo.icompras.pedidos.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
