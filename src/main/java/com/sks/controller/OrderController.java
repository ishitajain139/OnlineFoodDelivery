package com.sks.controller;

import com.sks.entity.Order;
import com.sks.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
    	order.setOrderStatus("Pending");
    	Order savedOrder = orderService.createOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/all")
    public Iterable<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        Optional<Order> orderOptional = orderService.getOrderById(orderId);
        return orderOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}")
    public List<Order> getOrdersByCustomerId(@PathVariable Long customerId) {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<Order> getOrdersByRestaurantId(@PathVariable Long restaurantId) {
        return orderService.getOrdersByRestaurantId(restaurantId);
    }

    @GetMapping("/date/{orderDate}")
    public List<Order> getOrdersByOrderDate(@PathVariable LocalDateTime orderDate) {
        return orderService.getOrdersByOrderDate(orderDate);
    }

    @GetMapping("/date")
    public List<Order> getOrdersBetweenDates(@RequestParam("startDate") LocalDateTime startDate,
                                             @RequestParam("endDate") LocalDateTime endDate) {
        return orderService.getOrdersBetweenDates(startDate, endDate);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        Order savedOrder = orderService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
