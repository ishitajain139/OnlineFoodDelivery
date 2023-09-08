package com.sks;

import com.sks.entity.Customer;
import com.sks.repository.CustomerRepository;
import com.sks.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCustomer() {
        Customer newCustomer = new Customer(4L, "abc", "abc@gmail.com", "9876543210", "456 Main Street");
        when(customerRepository.save(any(Customer.class))).thenReturn(newCustomer);

        Customer result = customerService.createCustomer(newCustomer);

        assertNotNull(result);
        assertEquals(newCustomer.getName(), result.getName());
        assertEquals(newCustomer.getEmail(), result.getEmail());
        assertEquals(newCustomer.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(newCustomer.getDeliveryAddress(), result.getDeliveryAddress());
    }

    @Test
    public void testUpdateCustomer() {
        Customer existingCustomer = new Customer(1L, "Ishita Jain", "ishi@gmail.com", "1234567890", "123 Main Street");
        Customer updatedCustomer = new Customer(1L, "Ishi Jain", "ishi@example.com", "987654322", "Address 2");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(updatedCustomer);

        Customer result = customerService.updateCustomer(1L, updatedCustomer);

        assertNotNull(result);
        assertEquals(updatedCustomer.getName(), result.getName());
        assertEquals(updatedCustomer.getEmail(), result.getEmail());
        assertEquals(updatedCustomer.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(updatedCustomer.getDeliveryAddress(), result.getDeliveryAddress());
    }

    @Test
    public void testGetCustomerById() {
        Customer mockCustomer = new Customer(2L, "Tanya Rawat", "tanya@gmail.com", "1234567890", "123 Main Street");
        when(customerRepository.findById(2L)).thenReturn(Optional.of(mockCustomer));

        Optional<Customer> result = customerService.getCustomerById(2L);

        assertTrue(result.isPresent());
        assertEquals(mockCustomer.getName(), result.get().getName());
    }
    
    @Test
    public void testDeleteCustomer() {
        Customer existingCustomer = new Customer(3L, "Aditya Maltare", "adi@gmail.com", "1234567890", "123 Main Street");
        when(customerRepository.findById(3L)).thenReturn(Optional.of(existingCustomer));

        assertDoesNotThrow(() -> customerService.deleteCustomer(3L));
    }
}


