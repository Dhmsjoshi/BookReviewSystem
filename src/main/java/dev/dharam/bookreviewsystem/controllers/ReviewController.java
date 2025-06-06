package dev.dharam.bookreviewsystem.controllers;

import dev.dharam.bookreviewsystem.dtos.ReviewRequestDto;
import dev.dharam.bookreviewsystem.dtos.ReviewResponseDto;
import dev.dharam.bookreviewsystem.dtos.UpdateReviewDto;
import dev.dharam.bookreviewsystem.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable("reviewId") Long reviewId,@RequestBody UpdateReviewDto requestDto){

        return ResponseEntity.ok(reviewService.UpdateReview(reviewId,requestDto));
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(
            @PathVariable("reviewId") Long reviewId){

        return ResponseEntity.ok(reviewService.deleteReview(reviewId));
    }
}
