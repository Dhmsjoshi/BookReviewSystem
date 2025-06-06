package dev.dharam.bookreviewsystem.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDto {

    private String title;


    private String author;

    private Integer publishedYear;
}
