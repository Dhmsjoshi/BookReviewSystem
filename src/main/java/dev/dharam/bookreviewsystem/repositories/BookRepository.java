package dev.dharam.bookreviewsystem.repositories;

import dev.dharam.bookreviewsystem.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    Optional<Book> findById(Long bookId);

    Optional<Book> findByTitle(String title);
}
