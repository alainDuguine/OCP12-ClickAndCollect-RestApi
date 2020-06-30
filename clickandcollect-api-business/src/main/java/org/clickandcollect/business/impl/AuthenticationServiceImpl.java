package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.AuthenticationService;
import org.clickandcollect.business.exception.ResourceDuplicationException;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("authService")
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService, UserDetailsService {

    private final RestaurantRepository restaurantRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Restaurant register(Restaurant restaurant) {
        log.info("Saving restaurant '{}'", restaurant.getEmail());
        restaurant.setPassword(this.passwordEncoder.encode(restaurant.getPassword()));
        restaurant.setRoles("ROLE_USER");
        try {
            return this.restaurantRepository.save(restaurant);
        } catch (DataIntegrityViolationException e) {
            throw new ResourceDuplicationException("Email '" + restaurant.getEmail() + "' already exists");
        }
    }

    @Override
    public boolean checkEmailExistsBoolean(String email) {
        log.info("Searching email '{}'", email);
        Optional<Restaurant> restaurant = this.restaurantRepository.findRestaurantByEmail(email);
        return restaurant.isPresent();
    }

    @Override
    public Restaurant checkEmailExists(String email) {
        log.info("Searching email '{}'", email);
        return this.restaurantRepository.findRestaurantByEmail(email).orElseThrow( () ->
                new UnknownResourceException("Email '" + email + "' is not present in database")
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return this.restaurantRepository.findRestaurantByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("Invalid credentials")
        );
    }
}
