package konoec.bookhub.controller.saleManagement;

import konoec.bookhub.domain.saleManagement.sale.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
}
