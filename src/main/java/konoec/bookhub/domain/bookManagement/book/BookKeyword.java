package konoec.bookhub.domain.bookManagement.book;

import jakarta.persistence.*;
import konoec.bookhub.domain.bookManagement.keyword.Keyword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_keywords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "keyword_id", nullable = false)
    private Keyword keyword;
}
