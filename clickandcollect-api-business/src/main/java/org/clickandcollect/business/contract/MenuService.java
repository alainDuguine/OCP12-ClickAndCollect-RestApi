package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenusByRestaurantId(Long restaurantId);
    Menu saveMenu(Long restaurantId, Menu menu);
    Menu updateMenu(Long restaurantId, Long menuId, Menu menu);
    void deleteMenu(Long restaurantId, Long menuId);
}
