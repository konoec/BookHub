package konoec.bookhub.domain.saleManagement.customer;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import konoec.bookhub.domain.saleManagement.util.DocumentType;
import lombok.*;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(nullable = false)
    private String fullName;

    @NotNull(message = "El tipo de documento es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @NotBlank(message = "El número de documento es obligatorio")
    @Pattern(regexp = "^[0-9]{8,11}$", message = "El número de documento debe tener entre 8 y 11 dígitos")
    @Column(nullable = false, unique = true, length = 11)
    private String documentNumber;

    @Email(message = "El correo electrónico debe tener un formato válido")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^(\\+?51|0)?9\\d{8}$", message = "El teléfono debe ser un número válido en Perú")
    private String phone;

    @Size(max = 200, message = "La dirección no puede exceder los 200 caracteres")
    private String address;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}