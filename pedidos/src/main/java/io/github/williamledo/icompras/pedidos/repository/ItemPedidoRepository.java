package io.github.williamledo.icompras.pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.williamledo.icompras.pedidos.model.ItemPedido;
import io.github.williamledo.icompras.pedidos.model.Pedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

	List<ItemPedido> findByPedido(Pedido pedido);

}
