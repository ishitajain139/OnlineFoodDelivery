package com.sks.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.sks.entity.Restaurant;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

    List<Restaurant> findByCuisineType(String cuisineType);

    List<Restaurant> findByLocation(String location);

    List<Restaurant> findByRatingGreaterThanEqual(double minRating);

}
