package com.sks.service;

import com.sks.entity.Customer;
import com.sks.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            Customer existingCustomer = customerOptional.get();
            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            existingCustomer.setDeliveryAddress(updatedCustomer.getDeliveryAddress());
            return customerRepository.save(existingCustomer);
        } else {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    public void deleteCustomer(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isPresent()) {
            customerRepository.deleteById(customerId);
        } else {
            throw new IllegalArgumentException("Customer not found");
        }
    }

    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
