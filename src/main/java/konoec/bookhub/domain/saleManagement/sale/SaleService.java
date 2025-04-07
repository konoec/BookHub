package konoec.bookhub.domain.saleManagement.sale;

import konoec.bookhub.domain.bookManagement.book.Book;
import konoec.bookhub.domain.bookManagement.book.BookRepository;
import konoec.bookhub.domain.saleManagement.customer.CustomerRepository;
import konoec.bookhub.domain.saleManagement.util.PaymentType;
import konoec.bookhub.domain.userManagement.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private static final AtomicInteger counter = new AtomicInteger(1);

    public Sale createSale(Sale sale) {
        customerRepository.findById(sale.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        userRepository.findById(sale.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return saleRepository.save(sale);
    }

    public SaleDetail addSaleDetail(Long saleId, SaleDetail saleDetail) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        Book book = bookRepository.findById(saleDetail.getBook().getId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        saleDetail.setSale(sale);
        saleDetail.setBook(book);
        return saleDetailRepository.save(saleDetail);
    }

    public void removeSaleDetail(Long saleDetailId) {
        SaleDetail saleDetail = saleDetailRepository.findById(saleDetailId)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
        saleDetailRepository.delete(saleDetail);
    }

    public List<Sale> findAll() {
        return saleRepository.findAll();
    }

    public Sale findById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public void delete(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        sale.setIsDeleted(true);
        saleRepository.save(sale);
    }

    public void activate(Long id) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        sale.setIsDeleted(false);
        saleRepository.save(sale);
    }

    public Sale updateSale(Long id, Sale sale) {
        return saleRepository.findById(id)
                .map(existingSale -> {
                    existingSale.setCustomer(sale.getCustomer());
                    existingSale.setUser(sale.getUser());
                    existingSale.setSubtotal(sale.getSubtotal());
                    existingSale.setTaxAmount(sale.getTaxAmount());
                    existingSale.setTotalAmount(sale.getTotalAmount());
                    existingSale.setPaymentType(sale.getPaymentType());
                    existingSale.setInvoiceNumber(sale.getInvoiceNumber());
                    existingSale.setSeries(sale.getSeries());
                    existingSale.setCorrelative(sale.getCorrelative());
                    existingSale.setIssueDate(sale.getIssueDate());
                    return saleRepository.save(existingSale);
                })
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    public SaleDetail updateSaleDetail(Long saleDetailId, SaleDetail saleDetail) {
        return saleDetailRepository.findById(saleDetailId)
                .map(existingSaleDetail -> {
                    existingSaleDetail.setQuantity(saleDetail.getQuantity());
                    existingSaleDetail.setUnitPrice(saleDetail.getUnitPrice());
                    existingSaleDetail.setSubtotal(saleDetail.getSubtotal());
                    return saleDetailRepository.save(existingSaleDetail);
                })
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado"));
    }

    public Sale generateInvoiceOrReceipt(Long saleId, PaymentType paymentType) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        sale.setPaymentType(paymentType);

        sale.setInvoiceNumber(generateInvoiceNumber());
        sale.setSeries(generateSeries());
        sale.setCorrelative(generateCorrelative());
        sale.setIssueDate(LocalDateTime.now());
        return saleRepository.save(sale);
    }

    private String generateInvoiceNumber() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        return "INV-" + timestamp + "-" + counter.getAndIncrement();
    }

    private String generateSeries() {
        return "SER-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
    }

    private Integer generateCorrelative() {
        return counter.getAndIncrement();
    }
}
