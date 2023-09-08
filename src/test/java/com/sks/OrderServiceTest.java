package com.sks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sks.entity.Customer;
import com.sks.entity.Order;
import com.sks.entity.Restaurant;
import com.sks.repository.OrderRepository;
import com.sks.service.OrderService;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);

        Order newOrder = new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending");
        when(orderRepository.save(any(Order.class))).thenReturn(newOrder);

        Order result = orderService.createOrder(newOrder);

        assertNotNull(result);
        assertEquals(newOrder.getCustomer(), result.getCustomer());
        assertEquals(newOrder.getRestaurant(), result.getRestaurant());
    }

    @Test
    public void testGetAllOrders() {
        List<Order> mockOrders = new ArrayList<>();
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        mockOrders.add(new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending"));
        mockOrders.add(new Order(2L, customer, restaurant, "456 Elm St, Town", "Card", 150.0, "Delivered"));

        when(orderRepository.findAll()).thenReturn(mockOrders);

        Iterable<Order> result = orderService.getAllOrders();

        assertNotNull(result);
        assertEquals(mockOrders.size(), ((List<Order>) result).size());
    }
    
    @Test
    public void testUpdateOrder() {
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        Order existingOrder = new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending");
        Order updatedOrder = new Order(1L, customer, restaurant, "456 Elm St, Town", "Card", 150.0, "Delivered");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrder);

        Order result = orderService.updateOrder(1L, updatedOrder);

        assertNotNull(result);
        assertEquals(updatedOrder.getCustomer(), result.getCustomer());
        assertEquals(updatedOrder.getRestaurant(), result.getRestaurant());
        assertEquals(updatedOrder.getDeliveryAddress(), result.getDeliveryAddress());
        assertEquals(updatedOrder.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(updatedOrder.getTotalAmount(), result.getTotalAmount());
        assertEquals(updatedOrder.getOrderStatus(), result.getOrderStatus());
    }

    @Test
    public void testDeleteOrder() {
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        Order existingOrder = new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(existingOrder));

        assertDoesNotThrow(() -> orderService.deleteOrder(1L));
    }

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        Order mockOrder = new Order(orderId, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending");

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));

        Optional<Order> result = orderService.getOrderById(orderId);

        assertTrue(result.isPresent());
        assertEquals(mockOrder.getCustomer(), result.get().getCustomer());
        assertEquals(mockOrder.getRestaurant(), result.get().getRestaurant());
    }

    @Test
    public void testGetOrdersByCustomerId() {
        Long customerId = 1L;
        Customer customer = new Customer(customerId, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending"));
        mockOrders.add(new Order(2L, customer, restaurant, "456 Elm St, Town", "Card", 150.0, "Delivered"));

        when(orderRepository.findByCustomerId(customerId)).thenReturn(mockOrders);

        List<Order> result = orderService.getOrdersByCustomerId(customerId);

        assertNotNull(result);
        assertEquals(mockOrders.size(), result.size());
    }

    @Test
    public void testGetOrdersByRestaurantId() {
        Long restaurantId = 1L;
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(restaurantId, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending"));
        mockOrders.add(new Order(2L, customer, restaurant, "456 Elm St, Town", "Card", 150.0, "Delivered"));

        when(orderRepository.findByRestaurantId(restaurantId)).thenReturn(mockOrders);

        List<Order> result = orderService.getOrdersByRestaurantId(restaurantId);

        assertNotNull(result);
        assertEquals(mockOrders.size(), result.size());
    }
    
    @Test
    public void testGetOrdersByOrderDate() {
        LocalDateTime orderDate = LocalDateTime.of(2023, 8, 24, 12, 0);
        List<Order> mockOrders = new ArrayList<>();
        Customer customer = new Customer(1L, "Ishita Jain", "ishi@example.com", "1234567890", "123 Main St");
        Restaurant restaurant = new Restaurant(1L, "Sample Restaurant", "Italian", "123 Main St", 4.5);
        mockOrders.add(new Order(1L, customer, restaurant, "123 Oak St, Village", "COD", 130.0, "Pending"));
        mockOrders.add(new Order(2L, customer, restaurant, "456 Elm St, Town", "Card", 150.0, "Delivered"));

        when(orderRepository.findByOrderDate(orderDate)).thenReturn(mockOrders);

        List<Order> result = orderService.getOrdersByOrderDate(orderDate);

        assertNotNull(result);
        assertEquals(mockOrders.size(), result.size());
    }
   
}
