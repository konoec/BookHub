package konoec.bookhub.domain.bookManagement.keyword;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "keywords")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La palabra clave es obligatoria")
    @Size(min = 2, max = 30, message = "La palabra clave debe tener entre 2 y 30 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\s-]+$", message = "La palabra clave solo puede contener letras, números, espacios y guiones")
    @Column(name = "keyword", nullable = false, unique = true)
    private String name;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}