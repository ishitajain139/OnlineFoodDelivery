package com.sks.service;

import com.sks.entity.Restaurant;
import com.sks.entity.MenuItem;
import com.sks.repository.RestaurantRepository;
import com.sks.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(Long restaurantId, Restaurant updatedRestaurant) {
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isPresent()) {
            Restaurant existingRestaurant = restaurantOptional.get();
            existingRestaurant.setName(updatedRestaurant.getName());
            existingRestaurant.setCuisineType(updatedRestaurant.getCuisineType());
            existingRestaurant.setLocation(updatedRestaurant.getLocation());
            existingRestaurant.setRating(updatedRestaurant.getRating());
            return restaurantRepository.save(existingRestaurant);
        } else {
            throw new IllegalArgumentException("Restaurant not found");
        }
    }

    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

    public List<Restaurant> getRestaurantsByCuisineType(String cuisineType) {
        return restaurantRepository.findByCuisineType(cuisineType);
    }

    public List<Restaurant> getRestaurantsByLocation(String location) {
        return restaurantRepository.findByLocation(location);
    }

    public List<Restaurant> getRestaurantsByRating(double minRating) {
        return restaurantRepository.findByRatingGreaterThanEqual(minRating);
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }
}
