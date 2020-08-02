package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Order;

public interface OrderService {
    Order saveOrder(Long restaurantId, Order order);
}
