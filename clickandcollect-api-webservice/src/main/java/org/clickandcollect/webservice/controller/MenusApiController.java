package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.MenuService;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.webservice.dto.MenuDto;
import org.clickandcollect.webservice.mapper.MenuMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = MenusApiController.RESOURCE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class MenusApiController {

    public static final String RESOURCE_URL = "/restaurants/{restaurantId}/menus";

    private final MenuService menuService;
    private final MenuMapper menuMapper;

    public MenusApiController(MenuService menuService, MenuMapper menuMapper) {
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
}
