package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findMenusByRestaurantId(Long restaurantId);
    Menu saveMenu(Long restaurantId, Menu menu);
}
