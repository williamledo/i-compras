package io.github.williamledo.icompras.pedidos.controller.mapper;

import org.mapstruct.Mapper;

import io.github.williamledo.icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.williamledo.icompras.pedidos.model.ItemPedido;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

	ItemPedido map(ItemPedidoDTO dto);
	
}
