package konoec.bookhub.domain.saleManagement.sale;

import jakarta.persistence.*;
import konoec.bookhub.domain.bookManagement.book.Book;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double unitPrice;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
