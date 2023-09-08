package com.sks.service;

import com.sks.entity.Review;
import com.sks.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    
	public Iterable<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	public Optional<Review> getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId);
    }

	public Review createReview(Review review) {
		return reviewRepository.save(review);
	}

	public Review updateReview(Review review) {
		return reviewRepository.save(review);
	}

	public void deleteReview(Long reviewId) {
		reviewRepository.deleteById(reviewId);
	}
}