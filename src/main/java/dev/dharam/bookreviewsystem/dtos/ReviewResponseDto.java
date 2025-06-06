package dev.dharam.bookreviewsystem.dtos;

import dev.dharam.bookreviewsystem.models.Review;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private String comment;
    private Integer rating;

    public static ReviewResponseDto fromReviewToReviewResponseDto(Review review) {
        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
        reviewResponseDto.setComment(review.getComment());
        reviewResponseDto.setRating(review.getRating());
        return reviewResponseDto;
    }

}
