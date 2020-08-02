package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Order;
import org.clickandcollect.webservice.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderDtoToOrder(OrderDto orderDto);
}
