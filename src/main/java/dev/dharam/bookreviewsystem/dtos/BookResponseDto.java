package dev.dharam.bookreviewsystem.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.dharam.bookreviewsystem.models.Book;
import dev.dharam.bookreviewsystem.models.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BookResponseDto {

    private String title;

    private String author;

    private Integer publishedYear;


    private Set<Review> reviews = new HashSet<>();

    private Double averageRating;


    public static BookResponseDto fromBookToBookResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setAuthor(book.getAuthor());
        bookResponseDto.setPublishedYear(book.getPublishedYear());
        if(book.getReviews() != null){
            bookResponseDto.setReviews(book.getReviews());
        }
        if(book.getAverageRating() != null){
            bookResponseDto.setAverageRating(book.getAverageRating());
        }
        return bookResponseDto;
    }
}
