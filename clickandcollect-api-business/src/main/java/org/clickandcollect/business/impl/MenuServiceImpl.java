package org.clickandcollect.business.impl;

import org.clickandcollect.business.contract.MenuService;
import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.model.entity.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> findMenusByRestaurantId(Long restaurantId) {
        return this.menuRepository.findAllByRestaurantId(restaurantId);
    }
}
