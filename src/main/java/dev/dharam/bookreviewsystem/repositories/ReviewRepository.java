package dev.dharam.bookreviewsystem.repositories;

import dev.dharam.bookreviewsystem.models.Review;
import dev.dharam.bookreviewsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {


    Optional<Review> findByUser(User user);
}
