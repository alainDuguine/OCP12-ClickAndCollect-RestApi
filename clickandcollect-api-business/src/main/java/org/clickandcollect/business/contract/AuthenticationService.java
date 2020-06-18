package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Restaurant;

public interface AuthenticationService {
    Restaurant register(Restaurant restaurant);
    boolean checkEmailExists(String email);
}
