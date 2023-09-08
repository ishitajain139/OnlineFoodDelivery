package com.sks.service;

import com.sks.entity.MenuItem;
import com.sks.entity.Restaurant;
import com.sks.repository.MenuItemRepository;
import com.sks.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        Long restaurantId = menuItem.getRestaurant().getId();
        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
        if (restaurantOptional.isEmpty()) {
            throw new IllegalArgumentException("Restaurant not found");
        }

        menuItem.setRestaurant(restaurantOptional.get());
        return menuItemRepository.save(menuItem);
    }

    public Iterable<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(Long menuItemId) {
        return menuItemRepository.findById(menuItemId);
    }

    public MenuItem updateMenuItem(Long menuItemId, MenuItem updatedMenuItem) {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItemId);
        if (menuItemOptional.isPresent()) {
            MenuItem existingMenuItem = menuItemOptional.get();
            existingMenuItem.setName(updatedMenuItem.getName());
            existingMenuItem.setDescription(updatedMenuItem.getDescription());
            existingMenuItem.setPrice(updatedMenuItem.getPrice());
            existingMenuItem.setAvailability(updatedMenuItem.isAvailability());
            return menuItemRepository.save(existingMenuItem);
        } else {
            throw new IllegalArgumentException("MenuItem not found");
        }
    }

    public void deleteMenuItem(Long menuItemId) {
        Optional<MenuItem> menuItemOptional = menuItemRepository.findById(menuItemId);
        if (menuItemOptional.isPresent()) {
            menuItemRepository.deleteById(menuItemId);
        } else {
            throw new IllegalArgumentException("MenuItem not found");
        }
    }

    public List<MenuItem> getMenuItemsByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    public List<MenuItem> getMenuItemsByAvailability(boolean availability) {
        return menuItemRepository.findByAvailability(availability);
    }
}
