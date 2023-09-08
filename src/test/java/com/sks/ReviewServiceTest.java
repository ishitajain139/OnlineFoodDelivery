package com.sks;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sks.entity.Review;
import com.sks.repository.ReviewRepository;
import com.sks.service.ReviewService;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReview() {
        Review newReview = new Review(1L, 1L, 4.5, "Great food!", new Date());
        when(reviewRepository.save(any(Review.class))).thenReturn(newReview);

        Review result = reviewService.createReview(newReview);

        assertNotNull(result);
        assertEquals(newReview.getCustomerId(), result.getCustomerId());
        assertEquals(newReview.getRestaurantId(), result.getRestaurantId());
        assertEquals(newReview.getRating(), result.getRating());
    }

    @Test
    public void testGetAllReviews() {
        List<Review> mockReviews = new ArrayList<>();
        mockReviews.add(new Review(1L, 1L, 4.5, "Great food!", new Date()));
        mockReviews.add(new Review(2L, 2L, 3.0, "Good service!", new Date()));

        when(reviewRepository.findAll()).thenReturn(mockReviews);

        Iterable<Review> result = reviewService.getAllReviews();

        assertNotNull(result);
        assertEquals(mockReviews.size(), ((List<Review>) result).size());
    }

    @Test
    public void testUpdateReview() {
        Review existingReview = new Review(1L, 1L, 4.5, "Great food!", new Date());
        Review updatedReview = new Review(1L, 1L, 5.0, "Excellent food!", new Date());

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));
        when(reviewRepository.save(any(Review.class))).thenReturn(updatedReview);

        Review result = reviewService.updateReview(updatedReview);

        assertNotNull(result);
        assertEquals(updatedReview.getCustomerId(), result.getCustomerId());
        assertEquals(updatedReview.getRestaurantId(), result.getRestaurantId());
        assertEquals(updatedReview.getRating(), result.getRating());
    }

    @Test
    public void testDeleteReview() {
        Review existingReview = new Review(1L, 1L, 4.5, "Great food!", new Date());

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(existingReview));

        assertDoesNotThrow(() -> reviewService.deleteReview(1L));
    }

    @Test
    public void testGetReviewById() {
        Long reviewId = 1L;
        Review mockReview = new Review(reviewId, 1L, 4.5, "Great food!", new Date());

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(mockReview));

        Optional<Review> result = reviewService.getReviewById(reviewId);

        assertTrue(result.isPresent());
        assertEquals(mockReview.getCustomerId(), result.get().getCustomerId());
        assertEquals(mockReview.getRestaurantId(), result.get().getRestaurantId());
    }
}
