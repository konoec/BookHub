package konoec.bookhub.domain.bookManagement.format;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formats")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
