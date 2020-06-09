package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.model.entity.MenuCourse;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.ProductInCourse;
import org.clickandcollect.webservice.dto.MenuCourseDto;
import org.clickandcollect.webservice.dto.MenuDto;
import org.clickandcollect.webservice.dto.ProductDto;
import org.clickandcollect.webservice.dto.ProductInCourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = ProductMapper.class)
public interface MenuMapper {

    @Mapping(target = "restaurantId", source = "restaurant.id")
    MenuDto menuToDto(Menu menu);

    List<MenuDto> listMenuToListMenuDto(List<Menu> menus);

    @Mapping(target = "categoryName", source = "category.name")
    MenuCourseDto menuCourseToDto(MenuCourse menuCourse);
    List<MenuCourseDto> listMenuCourseToListDto(List<MenuCourse> menuCourses);

    ProductInCourseDto productInMenuToDto(ProductInCourse productInCourse);

    @Mapping(target = "restaurantId", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "price", ignore = true)
    ProductDto productToDto(Product product);
}
