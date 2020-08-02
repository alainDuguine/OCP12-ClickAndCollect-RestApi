package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.OrderService;
import org.clickandcollect.model.entity.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Order saveOrder(Long restaurantId, Order order) {
        return null;
    }
}
