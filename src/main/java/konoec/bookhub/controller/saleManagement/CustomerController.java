package konoec.bookhub.controller.saleManagement;

import konoec.bookhub.domain.saleManagement.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
}
