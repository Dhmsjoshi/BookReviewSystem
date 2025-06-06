package dev.dharam.bookreviewsystem.services;


import dev.dharam.bookreviewsystem.dtos.BookRequestDto;
import dev.dharam.bookreviewsystem.dtos.BookResponseDto;
import dev.dharam.bookreviewsystem.dtos.ReviewRequestDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookService {

    BookResponseDto addBook(BookRequestDto requestDto);

    BookResponseDto addReviewForBook(Long bookId, ReviewRequestDto requestDto);

    List<BookResponseDto> getAllBooks();
}
