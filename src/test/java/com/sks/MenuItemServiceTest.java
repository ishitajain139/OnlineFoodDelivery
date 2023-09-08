package com.sks;

import com.sks.entity.MenuItem;
import com.sks.entity.Restaurant;
import com.sks.repository.MenuItemRepository;
import com.sks.repository.RestaurantRepository;
import com.sks.service.MenuItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuItemServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuItemService menuItemService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMenuItem() {
        Long restaurantId = 1L;
        Restaurant mockRestaurant = new Restaurant(restaurantId, "Test Restaurant", "Italian", "123 Main Street", 4.5);
        MenuItem newMenuItem = new MenuItem(1L, mockRestaurant, "Pizza", "Delicious pizza", 10.0, true);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(mockRestaurant));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(newMenuItem);

        MenuItem result = menuItemService.createMenuItem(newMenuItem);

        assertNotNull(result);
        assertEquals(newMenuItem.getName(), result.getName());
        assertEquals(newMenuItem.getDescription(), result.getDescription());
        assertEquals(newMenuItem.getPrice(), result.getPrice());
        assertEquals(newMenuItem.isAvailability(), result.isAvailability());
    }

    @Test
    public void testUpdateMenuItem() {
        Long menuItemId = 1L;
        MenuItem existingMenuItem = new MenuItem(menuItemId, null, "Pizza", "Delicious pizza", 10.0, true);
        MenuItem updatedMenuItem = new MenuItem(menuItemId, null, "Updated Pizza", "Tasty pizza", 12.0, false);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(existingMenuItem));
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(updatedMenuItem);

        MenuItem result = menuItemService.updateMenuItem(menuItemId, updatedMenuItem);

        assertNotNull(result);
        assertEquals(updatedMenuItem.getName(), result.getName());
        assertEquals(updatedMenuItem.getDescription(), result.getDescription());
        assertEquals(updatedMenuItem.getPrice(), result.getPrice());
        assertEquals(updatedMenuItem.isAvailability(), result.isAvailability());
    }

    @Test
    public void testGetAllMenuItems() {
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1L, null, "Pizza", "Delicious pizza", 10.0, true));
        mockMenuItems.add(new MenuItem(2L, null, "Pasta", "Tasty pasta", 8.0, true));

        when(menuItemRepository.findAll()).thenReturn(mockMenuItems);

        Iterable<MenuItem> result = menuItemService.getAllMenuItems();

        assertNotNull(result);
        assertEquals(mockMenuItems.size(), ((List<MenuItem>) result).size());
    }

    @Test
    public void testGetMenuItemById() {
        Long menuItemId = 1L;
        MenuItem mockMenuItem = new MenuItem(menuItemId, null, "Pizza", "Delicious pizza", 10.0, true);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(mockMenuItem));

        Optional<MenuItem> result = menuItemService.getMenuItemById(menuItemId);

        assertTrue(result.isPresent());
        assertEquals(mockMenuItem.getName(), result.get().getName());
    }

    @Test
    public void testDeleteMenuItem() {
        Long menuItemId = 1L;
        MenuItem existingMenuItem = new MenuItem(menuItemId, null, "Pizza", "Delicious pizza", 10.0, true);

        when(menuItemRepository.findById(menuItemId)).thenReturn(Optional.of(existingMenuItem));

        assertDoesNotThrow(() -> menuItemService.deleteMenuItem(menuItemId));
    }

    @Test
    public void testGetMenuItemsByRestaurantId() {
        Long restaurantId = 1L;
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1L, null, "Pizza", "Delicious pizza", 10.0, true));
        mockMenuItems.add(new MenuItem(2L, null, "Pasta", "Tasty pasta", 8.0, true));

        when(menuItemRepository.findByRestaurantId(restaurantId)).thenReturn(mockMenuItems);

        List<MenuItem> result = menuItemService.getMenuItemsByRestaurantId(restaurantId);

        assertNotNull(result);
        assertEquals(mockMenuItems.size(), result.size());
    }

    @Test
    public void testGetMenuItemsByAvailability() {
        boolean availability = true;
        List<MenuItem> mockMenuItems = new ArrayList<>();
        mockMenuItems.add(new MenuItem(1L, null, "Pizza", "Delicious pizza", 10.0, availability));
        mockMenuItems.add(new MenuItem(2L, null, "Pasta", "Tasty pasta", 8.0, availability));

        when(menuItemRepository.findByAvailability(availability)).thenReturn(mockMenuItems);

        List<MenuItem> result = menuItemService.getMenuItemsByAvailability(availability);

        assertNotNull(result);
        assertEquals(mockMenuItems.size(), result.size());
    }

}
