package com.sks;

import com.sks.controller.RestaurantController;
import com.sks.entity.Restaurant;
import com.sks.entity.MenuItem;
import com.sks.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestaurantServiceTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRestaurant() {
        Restaurant newRestaurant = new Restaurant(1L, "Test Restaurant", "Italian", "123 Main Street", 4.5);
        when(restaurantService.createRestaurant(any(Restaurant.class))).thenReturn(newRestaurant);

        Restaurant result = restaurantController.createRestaurant(newRestaurant);

        assertNotNull(result);
        assertEquals(newRestaurant.getName(), result.getName());
        assertEquals(newRestaurant.getCuisineType(), result.getCuisineType());
        assertEquals(newRestaurant.getLocation(), result.getLocation());
        assertEquals(newRestaurant.getRating(), result.getRating());
    }

    @Test
    public void testUpdateRestaurant() {
        Long restaurantId = 1L;
        Restaurant updatedRestaurant = new Restaurant(restaurantId, "Updated Restaurant", "Italian", "456 New Street", 4.3);

        when(restaurantService.updateRestaurant(eq(restaurantId), any(Restaurant.class))).thenReturn(updatedRestaurant);

        ResponseEntity<Restaurant> response = restaurantController.updateRestaurant(restaurantId, updatedRestaurant);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedRestaurant.getName(), response.getBody().getName());
        assertEquals(updatedRestaurant.getCuisineType(), response.getBody().getCuisineType());
        assertEquals(updatedRestaurant.getLocation(), response.getBody().getLocation());
        assertEquals(updatedRestaurant.getRating(), response.getBody().getRating());
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(new Restaurant(1L, "Restaurant 1", "Italian", "123 Main Street", 4.0));
        restaurantList.add(new Restaurant(2L, "Restaurant 2", "Mexican", "456 New Street", 3.5));

        when(restaurantService.getAllRestaurants()).thenReturn(restaurantList);

        Iterable<Restaurant> result = restaurantController.getAllRestaurants();

        assertNotNull(result);
        assertEquals(restaurantList.size(), ((List<Restaurant>) result).size());
    }

    @Test
    public void testGetRestaurantById() {
        Long restaurantId = 1L;
        Restaurant mockRestaurant = new Restaurant(restaurantId, "Mock Restaurant", "Italian", "123 Main Street", 4.2);
        when(restaurantService.getRestaurantById(restaurantId)).thenReturn(Optional.of(mockRestaurant));

        ResponseEntity<Restaurant> response = restaurantController.getRestaurantById(restaurantId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRestaurant.getName(), response.getBody().getName());
    }

    @Test
    public void testGetRestaurantsByCuisineType() {
        String cuisineType = "Italian";
        List<Restaurant> mockRestaurants = new ArrayList<>();
        mockRestaurants.add(new Restaurant(1L, "Restaurant 1", cuisineType, "123 Main Street", 4.0));
        mockRestaurants.add(new Restaurant(2L, "Restaurant 2", cuisineType, "456 New Street", 4.2));

        when(restaurantService.getRestaurantsByCuisineType(cuisineType)).thenReturn(mockRestaurants);

        List<Restaurant> result = restaurantController.getRestaurantsByCuisineType(cuisineType);

        assertNotNull(result);
        assertEquals(mockRestaurants.size(), result.size());
    }

    @Test
    public void testGetRestaurantsByLocation() {
        String location = "123 Main Street";
        List<Restaurant> mockRestaurants = new ArrayList<>();
        mockRestaurants.add(new Restaurant(1L, "Restaurant 1", "Italian", location, 4.0));
        mockRestaurants.add(new Restaurant(2L, "Restaurant 2", "Mexican", location, 3.5));

        when(restaurantService.getRestaurantsByLocation(location)).thenReturn(mockRestaurants);

        List<Restaurant> result = restaurantController.getRestaurantsByLocation(location);

        assertNotNull(result);
        assertEquals(mockRestaurants.size(), result.size());
    }

    @Test
    public void testGetRestaurantsByRating() {
        double minRating = 4.0;
        List<Restaurant> mockRestaurants = new ArrayList<>();
        mockRestaurants.add(new Restaurant(1L, "Restaurant 1", "Italian", "123 Main Street", 4.5));
        mockRestaurants.add(new Restaurant(2L, "Restaurant 2", "Mexican", "456 New Street", 4.2));

        when(restaurantService.getRestaurantsByRating(minRating)).thenReturn(mockRestaurants);

        List<Restaurant> result = restaurantController.getRestaurantsByRating(minRating);

        assertNotNull(result);
        assertEquals(mockRestaurants.size(), result.size());
    }

    @Test
    public void testGetMenuItemsByRestaurantId() {
        Long restaurantId = 1L;
        Restaurant mockRestaurant = new Restaurant(restaurantId, "Test Restaurant", "Italian", "123 Main Street", 4.5);
        
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1L, mockRestaurant, "Pizza", "Delicious pizza", 10.0, true));
        mockMenuItems.add(new MenuItem(2L, mockRestaurant, "Pasta", "Tasty pasta", 8.0, true));

        when(restaurantService.getMenuItemsByRestaurantId(restaurantId)).thenReturn(mockMenuItems);

        List<MenuItem> result = restaurantController.getMenuItemsByRestaurantId(restaurantId);

        assertNotNull(result);
        assertEquals(mockMenuItems.size(), result.size());
    }


}
