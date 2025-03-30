package konoec.bookhub.domain.saleManagement.sale;

import jakarta.persistence.*;
import konoec.bookhub.domain.saleManagement.customer.Customer;
import konoec.bookhub.domain.saleManagement.util.DocumentType;
import konoec.bookhub.domain.userManagement.user.User;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType documentType;

    @Column(nullable = false)
    private String series;

    @Column(nullable = false)
    private Integer correlative;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
