package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.ClientOrder;
import org.clickandcollect.model.entity.MenuOrder;
import org.clickandcollect.model.entity.ProductOrder;
import org.clickandcollect.model.entity.SelectedProduct;
import org.clickandcollect.webservice.dto.MenuOrderDto;
import org.clickandcollect.webservice.dto.OrderDto;
import org.clickandcollect.webservice.dto.ProductOrderDto;
import org.clickandcollect.webservice.dto.SelectedProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    ClientOrder orderDtoToOrder(OrderDto orderDto);


    @Mapping(target = "clientOrder", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    ProductOrder dtoToProductOrder(ProductOrderDto productOrderDto);


    @Mapping(target = "clientOrder", ignore = true)
    @Mapping(target = "menu.id", source = "menuId")
    @Mapping(target = "id", ignore = true)
    MenuOrder dtoToMenuOrder(MenuOrderDto menuOrderDto);

    @Mapping(target = "productInCourse.id", source = "productId")
    @Mapping(target = "menuOrder", ignore = true)
    @Mapping(target = "id", ignore = true)
    SelectedProduct dtoToSelectedProduct(SelectedProductDto selectedProductDto);
}
