package dev.dharam.bookreviewsystem.controllers;

import dev.dharam.bookreviewsystem.dtos.BookRequestDto;
import dev.dharam.bookreviewsystem.dtos.BookResponseDto;
import dev.dharam.bookreviewsystem.dtos.ReviewRequestDto;
import dev.dharam.bookreviewsystem.models.Book;
import dev.dharam.bookreviewsystem.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<BookResponseDto> addBook(@RequestBody BookRequestDto requestDto){
        return ResponseEntity.ok(bookService.addBook(requestDto));
    }

    @PostMapping("/{bookId}/reviews")
    public ResponseEntity<BookResponseDto> addReviewForBook(@PathVariable("bookId") Long bookId, @RequestBody ReviewRequestDto requestDto){
        return ResponseEntity.ok(bookService.addReviewForBook(bookId,requestDto));
    }


    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
