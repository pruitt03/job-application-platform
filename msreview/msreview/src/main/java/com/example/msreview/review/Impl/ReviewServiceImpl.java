package com.example.msreview.review.Impl;


import com.example.msreview.review.Review;
import com.example.msreview.review.ReviewRepository;
import com.example.msreview.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    //to allow auto rejection at run  time


    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review findReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if(companyId != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        //if company doesnt exist
        return false;
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedreview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review !=null){
            review.setId(updatedreview.getId());
            review.setTitle(updatedreview.getTitle());
            review.setDescription(updatedreview.getDescription());
            review.setRating(updatedreview.getRating());
            review.setCompanyId(updatedreview.getCompanyId());

            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    //delete review im company as well
    public boolean deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null){
            reviewRepository.delete(review);
            return true;
        }

        return false;
    }
}
