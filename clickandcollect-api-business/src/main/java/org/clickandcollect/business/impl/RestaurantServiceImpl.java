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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    public static final String UNKNOWN_RESTAURANT = "Unknown restaurant '";
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
                .orElseThrow(() -> new UsernameNotFoundException(UNKNOWN_RESTAURANT + email + "'"));
    }

    @Override
    public List<Restaurant> findRestaurantsWithin(Double latitude, Double longitude, Integer radius) {
        log.info("Searching restaurants {} km to point {} lat., {} long.", radius, latitude, longitude);

        return this.restaurantRepository.findAll().stream()
                .filter(restaurant -> restaurant.getLatitude() != null)
                .peek(restaurant -> restaurant.setDistance(this.getDistance(Double.parseDouble(restaurant.getLatitude()), Double.parseDouble(restaurant.getLongitude()), latitude, longitude)))
                .filter(restaurant -> restaurant.getDistance() <= radius)
                .sorted(Comparator.comparing(Restaurant::getDistance))
//                .skip(size * page - 1)
//                .limit(size)
                .collect(Collectors.toList());
    }

    private Double getDistance(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        distance = Math.pow(distance, 2);

        return Math.sqrt(distance);
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        log.info("Retrieving restaurant id '{}'", restaurantId);
        return this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException(UNKNOWN_RESTAURANT + restaurantId + "'")
        );
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        log.info("Retrieving restaurant id '{}' for update", restaurantId);
        Restaurant restaurantInDb = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException(UNKNOWN_RESTAURANT + restaurantId + "'"));
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
                .orElseThrow(() -> new UnknownResourceException(UNKNOWN_RESTAURANT + restaurantId + "'"));
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
                    recoverFile(bkpUrl, e.getMessage());
                }
                throw new FileHandlingException("Could not write file on disk");
            }
        } else {
            throw new FileHandlingException("Photo is not of the appropriate format");
        }
    }

    private void recoverFile(String bkpUrl, String error) {
        try {
            Files.move(Paths.get(bkpUrl), Paths.get(bkpUrl.replace(".bkp", "")));
        } catch (IOException e) {
            throw new FileHandlingException(error);
        }
    }
}
