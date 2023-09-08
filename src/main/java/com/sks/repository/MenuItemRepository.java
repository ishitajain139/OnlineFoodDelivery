package com.sks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.sks.entity.MenuItem;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

    List<MenuItem> findByRestaurantId(Long restaurantId);

    List<MenuItem> findByAvailability(boolean availability);

}
