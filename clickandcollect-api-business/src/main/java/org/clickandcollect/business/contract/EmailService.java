package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.ClientOrder;

public interface EmailService {
    void sendClientOrderNotification(ClientOrder order);
    void sendRestaurantOrderNotification(ClientOrder order);
}
