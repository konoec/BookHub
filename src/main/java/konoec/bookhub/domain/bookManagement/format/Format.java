package konoec.bookhub.domain.bookManagement.format;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Format {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String description;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
