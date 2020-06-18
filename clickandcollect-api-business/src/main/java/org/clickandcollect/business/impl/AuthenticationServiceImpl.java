package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.AuthenticationService;
import org.clickandcollect.business.exception.ResourceDuplicationException;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestaurantRepository restaurantRepository;

    public AuthenticationServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant register(Restaurant restaurant) {
        log.info("Saving restaurant '{}'", restaurant.getEmail());
        // TODO add BcryptPasswordEncoding with Spring security
        try {
            return this.restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceDuplicationException("Email '" + restaurant.getEmail() + "' already exists");
        }
    }

    @Override
    public boolean checkEmailExists(String email) {
        log.info("Searching email '{}'", email);
        Optional<Restaurant> restaurant = this.restaurantRepository.findRestaurantByEmail(email);
        return restaurant.isPresent();
    }
}
