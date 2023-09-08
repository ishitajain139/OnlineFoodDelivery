package com.sks.controller;

import com.sks.entity.MenuItem;
import com.sks.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/menuitems")
public class MenuItemController {

    private final MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @PostMapping("/create")
    public ResponseEntity<MenuItem> createMenuItem(@RequestBody MenuItem menuItem) {
        MenuItem savedMenuItem = menuItemService.createMenuItem(menuItem);
        return ResponseEntity.ok(savedMenuItem);
    }

    @GetMapping("/all")
    public Iterable<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long menuItemId) {
        Optional<MenuItem> menuItemOptional = menuItemService.getMenuItemById(menuItemId);
        return menuItemOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{menuItemId}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long menuItemId, @RequestBody MenuItem updatedMenuItem) {
        MenuItem savedMenuItem = menuItemService.updateMenuItem(menuItemId, updatedMenuItem);
        return ResponseEntity.ok(savedMenuItem);
    }

    @DeleteMapping("/{menuItemId}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long menuItemId) {
        menuItemService.deleteMenuItem(menuItemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItem> getMenuItemsByRestaurantId(@PathVariable Long restaurantId) {
        return menuItemService.getMenuItemsByRestaurantId(restaurantId);
    }

    @GetMapping("/availability/{availability}")
    public List<MenuItem> getMenuItemsByAvailability(@PathVariable boolean availability) {
        return menuItemService.getMenuItemsByAvailability(availability);
    }
}
