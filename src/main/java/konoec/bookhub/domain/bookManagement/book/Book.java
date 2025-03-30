package konoec.bookhub.domain.bookManagement.book;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import konoec.bookhub.domain.bookManagement.format.Format;
import konoec.bookhub.domain.bookManagement.genre.Genre;
import konoec.bookhub.domain.bookManagement.language.Language;
import konoec.bookhub.domain.bookManagement.author.Author;
import konoec.bookhub.domain.bookManagement.publisher.Publisher;
import konoec.bookhub.domain.userManagement.user.User;
import lombok.*;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(unique = true, length = 13)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "page_count")
    private Integer pageCount;

    @ManyToOne
    @JoinColumn(name = "format_id")
    private Format format;

    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal depth;
    private BigDecimal weight;
    private String edition;
    private Boolean illustrations;
    private String series;
    private String notes;

    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;

    private Integer stock;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookKeyword> bookKeywords;
}
