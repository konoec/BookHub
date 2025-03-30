package konoec.bookhub.domain.saleManagement.customer;

import jakarta.persistence.*;
import konoec.bookhub.domain.saleManagement.util.DocumentType;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false, unique = true, length = 11)
    private String documentNumber;

    @Column(unique = true)
    private String email;

    private String phone;
    private String address;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
