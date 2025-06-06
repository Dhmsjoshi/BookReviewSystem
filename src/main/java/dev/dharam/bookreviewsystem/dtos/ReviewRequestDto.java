package dev.dharam.bookreviewsystem.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {


    private Integer rating;

    private String comment;

    private Long userId; // For response
    private Long bookId;


}
