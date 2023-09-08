package com.sks.repository;

import org.springframework.data.repository.CrudRepository;
import com.sks.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByEmail(String email);
}
