package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.ClientOrder;

public interface OrderService {
    ClientOrder saveOrder(Long restaurantId, ClientOrder clientOrder);
}
