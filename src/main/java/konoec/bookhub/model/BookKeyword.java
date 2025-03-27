package konoec.bookhub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_keywords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookKeyword {

    @EmbeddedId
    private BookKeywordId id;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
