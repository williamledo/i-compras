package io.github.williamledo.icompras.pedidos.controller.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import io.github.williamledo.icompras.pedidos.controller.dto.ItemPedidoDTO;
import io.github.williamledo.icompras.pedidos.controller.dto.NovoPedidoDTO;
import io.github.williamledo.icompras.pedidos.model.ItemPedido;
import io.github.williamledo.icompras.pedidos.model.Pedido;
import io.github.williamledo.icompras.pedidos.model.enums.StatusPedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {

	ItemPedidoMapper ITEM_PEDIDO_MAPPER = Mappers.getMapper(ItemPedidoMapper.class);
	
	@Mapping(source = "itens", target = "itens", qualifiedByName = "mapItens")
	@Mapping(source = "dadosPagamento", target = "dadosPagamento")
	Pedido map(NovoPedidoDTO dto);
	
	@Named("mapItens")
	default List<ItemPedido> mapItens(List<ItemPedidoDTO> dtos) {
		
		return dtos.stream().map(ITEM_PEDIDO_MAPPER::map).toList();
		
	}
	
	@AfterMapping
	default void afterMapping(@MappingTarget Pedido pedido) {
		
		pedido.setStatus(StatusPedido.REALIZADO);
		pedido.setDataPedido(LocalDateTime.now());
		
		var total = calcularTotal(pedido);
		
		pedido.setTotal(total);
		
		pedido.getItens().forEach(item -> item.setPedido(pedido) );
		
	}

	private static BigDecimal calcularTotal(Pedido pedido) {
		return pedido.getItens().stream().map(item -> 
					item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade()) )
				).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
	}
	
}
