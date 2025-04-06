package konoec.bookhub.domain.bookManagement.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.genres g WHERE g.id = :genreId")
    List<Book> findByGenreId(@Param("genreId") Long genreId);
    @Query("SELECT b FROM Book b JOIN b.keywords k WHERE k.id = :keywordId")
    List<Book> findByKeywordId(@Param("keywordId") Long keywordId);
}