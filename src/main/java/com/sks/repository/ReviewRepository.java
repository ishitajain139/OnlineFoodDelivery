package com.sks.repository;

import com.sks.entity.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByRestaurantId(Long restaurantId);

    List<Review> findByRatingGreaterThanEqual(int minRating);

}
