package konoec.bookhub.domain.saleManagement.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleService {
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;

    public Sale save(Sale sale) {
        return saleRepository.save(sale);
    }
}
