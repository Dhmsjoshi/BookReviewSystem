package dev.dharam.bookreviewsystem.services;

import dev.dharam.bookreviewsystem.dtos.ReviewRequestDto;
import dev.dharam.bookreviewsystem.dtos.ReviewResponseDto;
import dev.dharam.bookreviewsystem.dtos.UpdateReviewDto;
import dev.dharam.bookreviewsystem.models.Book;
import dev.dharam.bookreviewsystem.models.Review;

public interface ReviewService {

    Review addReview(Book book, ReviewRequestDto requestDto);
    ReviewResponseDto UpdateReview(Long reviewId,UpdateReviewDto requestDto);
    String deleteReview(Long reviewId);
}
