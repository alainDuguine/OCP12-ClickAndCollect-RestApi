package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.MenuService;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.webservice.dto.MenuDto;
import org.clickandcollect.webservice.mapper.MenuMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
@Slf4j
public class MenuApiController {

    private final MenuService menuService;
    private final MenuMapper menuMapper;

    public MenuApiController(MenuService menuService, MenuMapper menuMapper) {
        this.menuService = menuService;
        this.menuMapper = menuMapper;
    }

    @GetMapping()
    public ResponseEntity<List<MenuDto>> getMenus(@PathVariable(value = "restaurantId") Long restaurantId) {
        log.info("Retrieving list of menus for restaurant id '{}'", restaurantId);
        List<Menu> menus = this.menuService.findMenusByRestaurantId(restaurantId);
        log.info("{} menus found", menus.size());
        return new ResponseEntity<>(menuMapper.listMenuToListMenuDto(menus), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<MenuDto> addMenu(@PathVariable Long restaurantId,
                                           @Valid @RequestBody MenuDto menuDto)  {
        log.info("Adding a new menu to restaurant id '{}'", restaurantId);
        Menu menu = this.menuService.saveMenu(restaurantId, this.menuMapper.menuDtoToMenu(menuDto));
        log.info("Menu '{}' created", menu.getId());
        return new ResponseEntity<>(this.menuMapper.menuToDto(menu), HttpStatus.CREATED);
    }

    @PutMapping("{menuId}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long restaurantId,
                                              @PathVariable Long menuId,
                                              @Valid @RequestBody MenuDto menuDto) {
        log.info("Updating menu id '{}' for restaurant id '{}'", menuId, restaurantId);
        Menu menu = this.menuService.updateMenu(
                restaurantId,
                menuId,
                this.menuMapper.menuDtoToMenu(menuDto)
        );
        log.info("Menu '{}' updated", menuId);
        return new ResponseEntity<>(this.menuMapper.menuToDto(menu), HttpStatus.OK);
    }

    @DeleteMapping("{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long restaurantId,
                                           @PathVariable Long menuId) {
        log.info("Delete menu id '{}' for restaurant id '{}'", menuId, restaurantId);
        this.menuService.deleteMenu(restaurantId, menuId);
        log.info("Menu '{}' deleted", menuId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
