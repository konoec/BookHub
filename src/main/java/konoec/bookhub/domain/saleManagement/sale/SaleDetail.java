package konoec.bookhub.domain.saleManagement.sale;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import konoec.bookhub.domain.bookManagement.book.Book;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La venta es obligatoria")
    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @NotNull(message = "El libro es obligatorio")
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    @Column(nullable = false)
    private Integer quantity;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.0", message = "El precio unitario debe ser mayor a 0")
    @Column(nullable = false)
    private Double unitPrice;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", message = "El subtotal debe ser mayor a 0")
    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}