package dev.dharam.bookreviewsystem.services;

import dev.dharam.bookreviewsystem.dtos.BookRequestDto;
import dev.dharam.bookreviewsystem.dtos.BookResponseDto;
import dev.dharam.bookreviewsystem.dtos.ReviewRequestDto;
import dev.dharam.bookreviewsystem.exceptions.ResourceAlreadyExistsException;
import dev.dharam.bookreviewsystem.exceptions.ResourceDoesNotExistException;
import dev.dharam.bookreviewsystem.models.Book;
import dev.dharam.bookreviewsystem.models.Review;
import dev.dharam.bookreviewsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReviewService reviewService;

    @Override
    public BookResponseDto addBook(BookRequestDto requestDto) {
        Optional<Book> bookOptional = bookRepository.findByTitle(requestDto.getTitle());
        if(bookOptional.isPresent()){
            throw new ResourceAlreadyExistsException("Book already exists with title "+requestDto.getTitle());
        }

        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setAuthor(requestDto.getAuthor());
        book.setPublishedYear(requestDto.getPublishedYear());

        Book savedBook = bookRepository.save(book);

        return BookResponseDto.fromBookToBookResponseDto(savedBook);
    }

    @Override
    public BookResponseDto addReviewForBook(Long bookId,ReviewRequestDto requestDto) {
        Book existingBook = bookRepository.findById(bookId).orElseThrow(
                () -> new ResourceDoesNotExistException("Book with id "+bookId+" does not exist")
        );

        Review review = reviewService.addReview(existingBook,requestDto);
        Set<Review> reviews = existingBook.getReviews();
        reviews.add(review);
        review.setBook(existingBook);

        existingBook.addReview(review);

        return BookResponseDto.fromBookToBookResponseDto(bookRepository.save(existingBook));
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();

        List<BookResponseDto> bookResponseDtos = books.stream().map(BookResponseDto::fromBookToBookResponseDto).toList();
        return bookResponseDtos;
    }
}
