package konoec.bookhub.domain.saleManagement.sale;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import konoec.bookhub.domain.saleManagement.customer.Customer;
import konoec.bookhub.domain.saleManagement.util.PaymentType;
import konoec.bookhub.domain.userManagement.user.User;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El cliente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", message = "El subtotal debe ser mayor a 0")
    @Column(nullable = false)
    private Double subtotal;

    @NotNull(message = "El impuesto es obligatorio")
    @DecimalMin(value = "0.0", message = "El impuesto debe ser mayor o igual a 0")
    @Column(nullable = false)
    private Double taxAmount;

    @NotNull(message = "El monto total es obligatorio")
    @DecimalMin(value = "0.0", message = "El monto total debe ser mayor a 0")
    @Column(nullable = false)
    private Double totalAmount;

    @NotNull(message = "El tipo de pago es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    @NotBlank(message = "El número de factura es obligatorio")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "El número de factura debe tener un formato válido")
    @Column(nullable = false)
    private String invoiceNumber;

    @NotBlank(message = "La serie es obligatoria")
    @Pattern(regexp = "^[A-Z0-9]{4}$", message = "La serie debe tener 4 caracteres alfanuméricos")
    @Column(nullable = false)
    private String series;

    @NotNull(message = "El correlativo es obligatorio")
    @Min(value = 1, message = "El correlativo debe ser mayor a 0")
    @Column(nullable = false)
    private Integer correlative;

    @NotNull(message = "La fecha de emisión es obligatoria")
    @Column(nullable = false)
    private LocalDateTime issueDate;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}