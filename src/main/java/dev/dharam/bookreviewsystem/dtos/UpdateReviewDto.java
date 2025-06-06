package dev.dharam.bookreviewsystem.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReviewDto {
    private String comment;
    private Integer rating;
}
