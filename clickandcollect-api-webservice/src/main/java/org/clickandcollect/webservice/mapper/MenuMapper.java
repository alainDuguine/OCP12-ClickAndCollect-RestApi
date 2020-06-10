package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.model.entity.MenuCourse;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.ProductInCourse;
import org.clickandcollect.webservice.dto.MenuCourseDto;
import org.clickandcollect.webservice.dto.MenuDto;
import org.clickandcollect.webservice.dto.ProductDtoLight;
import org.clickandcollect.webservice.dto.ProductInCourseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", imports = ProductMapper.class)
public interface MenuMapper {

    @Mapping(target = "restaurantId", source = "restaurant.id")
    MenuDto menuToDto(Menu menu);

    @Mapping(target = "restaurant", ignore = true)
    Menu menuDtoToMenu(MenuDto menuDto);

    List<MenuDto> listMenuToListMenuDto(List<Menu> menus);

    @Mapping(target = "category", source = "category.name")
    MenuCourseDto menuCourseToDto(MenuCourse menuCourse);

    @Mapping(target = "menu", ignore = true)
    MenuCourse menuCourseDtoToModel(MenuCourseDto menuCourseDto);

    List<MenuCourseDto> listMenuCourseToListDto(List<MenuCourse> menuCourses);

    @Mapping(target = "productId", ignore = true)
    ProductInCourseDto productInMenuToDto(ProductInCourse productInCourse);

    @Mapping(target = "menuCourse", ignore = true)
    @Mapping(target = "product.id", source = "productId")
    ProductInCourse productInCourseDtoToModel(ProductInCourseDto productInCourseDto);

    ProductDtoLight productToDto(Product product);

    default Category map(String categoryName) {
        return new Category(categoryName);
    }

}
