package dev.dharam.bookreviewsystem.services;

import dev.dharam.bookreviewsystem.dtos.ReviewRequestDto;
import dev.dharam.bookreviewsystem.dtos.ReviewResponseDto;
import dev.dharam.bookreviewsystem.dtos.UpdateReviewDto;
import dev.dharam.bookreviewsystem.exceptions.ResourceAlreadyExistsException;
import dev.dharam.bookreviewsystem.exceptions.ResourceDoesNotExistException;
import dev.dharam.bookreviewsystem.models.Book;
import dev.dharam.bookreviewsystem.models.Review;
import dev.dharam.bookreviewsystem.models.User;
import dev.dharam.bookreviewsystem.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserService userService;

    @Override
    public Review addReview(Book book, ReviewRequestDto requestDto) {

        User user = userService.findById(requestDto.getUserId());

        Optional<Review> reviewOptional = reviewRepository.findByUser(user);
        if(reviewOptional.isPresent()){
            throw new ResourceAlreadyExistsException("User already has a review");
        }

        Review review = new Review();
        review.setBook(book);
        review.setUser(user);
        review.setRating(requestDto.getRating());
        review.setComment(requestDto.getComment());


        return reviewRepository.save(review);
    }

    @Override
    public ReviewResponseDto UpdateReview(Long reviewId, UpdateReviewDto requestDto) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ResourceDoesNotExistException("Review with id "+reviewId+" does not exist")
        );

        existingReview.setRating(requestDto.getRating());
        existingReview.setComment(requestDto.getComment());

        return ReviewResponseDto.fromReviewToReviewResponseDto(reviewRepository.save(existingReview));
    }

    @Override
    public String deleteReview(Long reviewId) {
        Review existingReview = reviewRepository.findById(reviewId).orElseThrow(
                ()-> new ResourceDoesNotExistException("Review with id "+reviewId+" does not exist")
        );

        reviewRepository.deleteById(reviewId);
        return "Deleted review with id "+reviewId+" Successfully";
    }
}
