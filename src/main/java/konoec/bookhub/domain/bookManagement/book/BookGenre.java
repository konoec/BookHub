package konoec.bookhub.domain.bookManagement.book;

import jakarta.persistence.*;
import konoec.bookhub.domain.bookManagement.genre.Genre;
import lombok.*;

@Entity
@Table(name = "book_genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}