package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.business.exception.FileHandlingException;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Value("${path-photo-storage}")
    private String pathPhotoStorage;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Restaurant findRestaurantByEmail(String email) {
        log.info("Retrieving restaurant by email '{}'", email);
        return this.restaurantRepository.findRestaurantByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown restaurant '" + email + "'"));
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        log.info("Retrieving restaurant id '{}'", restaurantId);
        return this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException("Unknown restaurant '" + restaurantId + "'")
        );
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        log.info("Retrieving restaurant id '{}' for update", restaurantId);
        Restaurant restaurantInDb = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException("Unknown restaurant '" + restaurantId + "'"));
        log.info("Restaurant found");
        restaurantInDb.setName(restaurant.getName());
        restaurantInDb.setDescription(restaurant.getDescription());
        restaurantInDb.setTypeCuisine(restaurant.getTypeCuisine());
        restaurantInDb.setFormattedAddress(restaurant.getFormattedAddress());
        restaurantInDb.setLatitude(restaurant.getLatitude());
        restaurantInDb.setLongitude(restaurant.getLongitude());
        if (restaurant.getBusinessHours() != null) {
            restaurantInDb.addAllBusinessHours(restaurant.getBusinessHours());
        }
        return this.restaurantRepository.save(restaurantInDb);
    }

    @Override
    public Restaurant uploadPhotoRestaurant(Long restaurantId, MultipartFile photo) {
        log.info("Retrieving restaurant id '{}' for photo upload", restaurantId);
        Restaurant restaurantInDb = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException("Unknown restaurant '" + restaurantId + "'"));
        log.info("Restaurant found");
        if (photo != null && photo.getContentType() != null && photo.getContentType().toLowerCase().startsWith("image")) {
            String extension;
            if (photo.getOriginalFilename() != null) {
                extension = photo.getOriginalFilename().split("\\.")[1].toLowerCase();
            } else {
                extension = "jpg";
            }
            String photoPath = pathPhotoStorage + restaurantId + "." + extension;
            String bkpUrl = "";
            log.info("Setting photo name '{}'", restaurantInDb.getPhoto());
            try {
                if (restaurantInDb.getPhoto() != null) {
                    bkpUrl = restaurantInDb.getPhoto() + ".bkp";
                    Files.move(Paths.get(restaurantInDb.getPhoto()), Paths.get(bkpUrl));
                }
                Files.write(Paths.get(photoPath), photo.getBytes());
                log.info("Photo wrote on disk to '{}'", photoPath);
                if(!bkpUrl.isEmpty()) {
                    Files.delete(Paths.get(bkpUrl));
                }
                restaurantInDb.setPhoto(photoPath);
                return this.restaurantRepository.save(restaurantInDb);
            } catch (IOException e) {
                if (!bkpUrl.isEmpty()) {
                    recoverFile(bkpUrl);
                }
                throw new FileHandlingException("Could not write file on disk");
            }
        } else {
            throw new FileHandlingException("Photo is not of the appropriate format");
        }
    }

    private void recoverFile(String bkpUrl) {
        try {
            Files.move(Paths.get(bkpUrl), Paths.get(bkpUrl.replace(".bkp", "")));
        } catch (IOException e) {
            throw new FileHandlingException("File could'nt be recovered");
        }
    }
}
