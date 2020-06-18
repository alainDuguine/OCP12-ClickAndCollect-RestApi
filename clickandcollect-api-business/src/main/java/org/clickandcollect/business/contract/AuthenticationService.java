package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Restaurant;

public interface AuthenticationService {
    Restaurant register(Restaurant restaurant);
    boolean checkEmailExistsBoolean(String email);
    Restaurant checkEmailExists(String email);
}
