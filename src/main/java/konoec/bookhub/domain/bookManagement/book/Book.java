package konoec.bookhub.domain.bookManagement.book;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import konoec.bookhub.domain.bookManagement.format.Format;
import konoec.bookhub.domain.bookManagement.genre.Genre;
import konoec.bookhub.domain.bookManagement.keyword.Keyword;
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
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 1, max = 255, message = "El título debe tener entre 1 y 255 caracteres")
    @Column(nullable = false)
    private String title;

    @Past(message = "La fecha de publicación debe ser en el pasado")
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @NotNull(message = "La editorial es obligatoria")
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$",
            message = "El ISBN debe tener un formato válido")
    @Column(unique = true, length = 13)
    private String isbn;

    @NotNull(message = "El idioma es obligatorio")
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Min(value = 1, message = "El número de páginas debe ser mayor a 0")
    @Column(name = "page_count")
    private Integer pageCount;

    @NotNull(message = "El formato es obligatorio")
    @ManyToOne
    @JoinColumn(name = "format_id")
    private Format format;

    @DecimalMin(value = "0.0", message = "El ancho debe ser mayor a 0")
    private BigDecimal width;

    @DecimalMin(value = "0.0", message = "El alto debe ser mayor a 0")
    private BigDecimal height;

    @DecimalMin(value = "0.0", message = "La profundidad debe ser mayor a 0")
    private BigDecimal depth;

    @DecimalMin(value = "0.0", message = "El peso debe ser mayor a 0")
    private BigDecimal weight;

    @Size(max = 50, message = "La edición no puede exceder los 50 caracteres")
    private String edition;

    private Boolean illustrations;

    @Size(max = 100, message = "La serie no puede exceder los 100 caracteres")
    private String series;

    @Size(max = 1000, message = "Las notas no pueden exceder los 1000 caracteres")
    private String notes;

    @NotNull(message = "El precio de venta es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio debe ser mayor a 0")
    @Column(name = "selling_price", nullable = false)
    private BigDecimal sellingPrice;

    @Min(value = 0, message = "El stock no puede ser negativo")
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

    @NotNull(message = "El autor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Size(min = 1, message = "Debe tener al menos una palabra clave")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_keywords",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<Keyword> keywords;

    @Size(min = 1, message = "Debe tener al menos un género")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @Column(name = "image_path")
    private String imagePath;
}