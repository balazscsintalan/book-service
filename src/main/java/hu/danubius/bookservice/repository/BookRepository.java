package hu.danubius.bookservice.repository;

import hu.danubius.bookservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    @Query("SELECT b FROM BookEntity b " +
        "WHERE b.isbn = :isbn " +
        "AND b.title = :title")
    Optional<BookEntity> asadasdasda(String isbn, String title);
}
