package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.AuthenticationService;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.AuthToken;
import org.clickandcollect.webservice.dto.LoginFormDto;
import org.clickandcollect.webservice.dto.RegistrationFormDto;
import org.clickandcollect.webservice.dto.RestaurantDto;
import org.clickandcollect.webservice.mapper.RestaurantMapper;
import org.clickandcollect.webservice.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthenticationApiController {

    public static final String BASE_URL = "/auth";

    private final AuthenticationService authenticationService;
    private final RestaurantService restaurantService;
    private final AuthenticationManager authenticationManager;
    private final RestaurantMapper restaurantMapper;
    private final JwtUtil jwtUtil;

    public AuthenticationApiController(AuthenticationService authenticationService, RestaurantService restaurantService, AuthenticationManager authenticationManager, RestaurantMapper restaurantMapper, JwtUtil jwtUtil) {
        this.authenticationService = authenticationService;
        this.restaurantService = restaurantService;
        this.authenticationManager = authenticationManager;
        this.restaurantMapper = restaurantMapper;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("register")
    public ResponseEntity<RestaurantDto> register(@Valid @RequestBody RegistrationFormDto registerForm) {
        log.info("Requesting register new restaurant {}", registerForm.getEmail());
        Restaurant restaurant = this.authenticationService.register(this.restaurantMapper.registerFormToRestaurant(registerForm));
        log.info("Restaurant '{}' created", restaurant.getId());
        return new ResponseEntity<>(this.restaurantMapper.restaurantToRestaurantDto(restaurant), HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthToken> login(@Valid @RequestBody LoginFormDto loginFormDto) {
        log.info("User login attempt '{}'", loginFormDto.getEmail());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginFormDto.getEmail(),
                        loginFormDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Restaurant restaurant = this.restaurantService.findRestaurantByEmail(loginFormDto.getEmail());
        final String token = jwtUtil.generateToken(restaurant);
        return new ResponseEntity<>(new AuthToken(restaurant.getId(), restaurant.getEmail(), token), HttpStatus.OK);
    }

    @GetMapping("register")
    public ResponseEntity<Boolean> emailExistsBoolean(@RequestParam(value = "email") String email) {
        log.info("Checking if email '{}' is present in database", email);
        boolean response = this.authenticationService.checkEmailExistsBoolean(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.HEAD, value="register")
    public ResponseEntity<Void> emailExists(@RequestParam(value = "email") String email) {
        log.info("Checking if email '{}' is present in database", email);
        this.authenticationService.checkEmailExists(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
