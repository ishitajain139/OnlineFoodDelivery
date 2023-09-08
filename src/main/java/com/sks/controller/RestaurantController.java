package com.sks.controller;

import com.sks.entity.Restaurant;
import com.sks.entity.MenuItem;
import com.sks.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/create")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long restaurantId, @RequestBody Restaurant updatedRestaurant) {
        Restaurant savedRestaurant = restaurantService.updateRestaurant(restaurantId, updatedRestaurant);
        return ResponseEntity.ok(savedRestaurant);
    }

    @GetMapping("/all")
    public Iterable<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable Long restaurantId) {
        Optional<Restaurant> restaurantOptional = restaurantService.getRestaurantById(restaurantId);
        return restaurantOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/cuisine/{cuisineType}")
    public List<Restaurant> getRestaurantsByCuisineType(@PathVariable String cuisineType) {
        return restaurantService.getRestaurantsByCuisineType(cuisineType);
    }

    @GetMapping("/location/{location}")
    public List<Restaurant> getRestaurantsByLocation(@PathVariable String location) {
        return restaurantService.getRestaurantsByLocation(location);
    }

    @GetMapping("/rating/{minRating}")
    public List<Restaurant> getRestaurantsByRating(@PathVariable double minRating) {
        return restaurantService.getRestaurantsByRating(minRating);
    }

    @GetMapping("/{restaurantId}/menu")
    public List<MenuItem> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        return restaurantService.getMenuItemsByRestaurantId(restaurantId);
    }
}
