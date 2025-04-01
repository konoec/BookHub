package konoec.bookhub.domain.bookManagement.genre;

import jakarta.persistence.*;
import konoec.bookhub.domain.bookManagement.book.Book;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;
}
