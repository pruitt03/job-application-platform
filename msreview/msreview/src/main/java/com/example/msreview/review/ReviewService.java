package com.example.msreview.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review findReviewById(Long reviewId);
    boolean addReview(Long CompanyId,Review review);
    boolean updateReview(Long reviewId,Review review);
    boolean deleteReviewById(Long reviewId);
}
