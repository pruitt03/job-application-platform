package com.example.msreview.review;

import com.example.msreview.review.messaging.ReviewMessageProducer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewService reviewService,ReviewMessageProducer reviewMessageProducer)
    {
        this.reviewService = reviewService;
        this.reviewMessageProducer = reviewMessageProducer;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId){
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> findReviewById(@PathVariable Long reviewId){
        Review review = reviewService.findReviewById(reviewId);
        if(review != null) return new ResponseEntity<>(review,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addReview(@RequestParam Long companyId,@RequestBody Review review){
        boolean add = reviewService.addReview(companyId, review);
        if(add){
            //rabbitmq
            reviewMessageProducer.sendMessage(review);
            return new ResponseEntity<>("Review added successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Company doesnt exist to add review",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long reviewId,@RequestBody Review review){
        boolean exists = reviewService.updateReview(reviewId, review);
        if(exists  )
            return new ResponseEntity<>("Review updated succesfully",HttpStatus.OK);
        return new ResponseEntity<>("Review with "+reviewId+" doesnt exist.",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId){
        boolean exists = reviewService.deleteReviewById(reviewId);
        if(exists)
            return new ResponseEntity<>("Review deleted succesfully",HttpStatus.OK);
        return new ResponseEntity<>("Review with reviewId"+reviewId+" doesnt exist.",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/averageRating")
    public Double getAverageRating(@RequestParam("companyId") Long companyId){
        List<Review> reviewList = reviewService.getAllReviews(companyId);
        return reviewList.stream().mapToDouble(Review::getRating).average().orElse(0.0);
    }
}
