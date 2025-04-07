package konoec.bookhub.controller.saleManagement;

import konoec.bookhub.domain.bookManagement.book.BookService;
import konoec.bookhub.domain.saleManagement.customer.CustomerService;
import konoec.bookhub.domain.saleManagement.sale.Sale;
import konoec.bookhub.domain.saleManagement.sale.SaleDetail;
import konoec.bookhub.domain.saleManagement.sale.SaleService;
import konoec.bookhub.domain.saleManagement.util.PaymentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sales")
public class SaleController {
    private final SaleService saleService;
    private final CustomerService customerService;
    private final BookService bookService;

    @GetMapping
    public String listSales(Model model) {
        model.addAttribute("sales", saleService.findAll());
        return "saleManagement/sales/list";
    }

    @GetMapping("/{id}")
    public String getSale(@PathVariable Long id, Model model) {
        model.addAttribute("sale", saleService.findById(id));
        model.addAttribute("paymentTypes", PaymentType.values());
        return "saleManagement/sales/detail";
    }

    @GetMapping("/new")
    public String newSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("paymentTypes", PaymentType.values());
        return "saleManagement/sales/form";
    }

    @PostMapping
    public String createSale(@Valid @ModelAttribute Sale sale, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("paymentTypes", PaymentType.values());
            return "saleManagement/sales/form";
        }
        saleService.createSale(sale);
        return "redirect:/sales/" + sale.getId();
    }

    @GetMapping("/{id}/details/add")
    public String newSaleDetailForm(@PathVariable Long id, Model model) {
        model.addAttribute("saleId", id);
        model.addAttribute("saleDetail", new SaleDetail());
        return "saleManagement/sales/detail-form";
    }

    @PostMapping("/{id}/details")
    public String addSaleDetail(@PathVariable Long id,
                              @Valid @ModelAttribute SaleDetail saleDetail,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "saleManagement/sales/detail-form";
        }
        saleService.addSaleDetail(id, saleDetail);
        return "redirect:/sales/" + id;
    }

    @GetMapping("/details/{detailId}/delete")
    public String removeSaleDetail(@PathVariable Long detailId,
                                 @RequestParam Long saleId) {
        saleService.removeSaleDetail(detailId);
        return "redirect:/sales/" + saleId;
    }

    @GetMapping("/{id}/generate-invoice")
    public String generateInvoice(@PathVariable Long id,
                                @RequestParam PaymentType paymentType) {
        saleService.generateInvoiceOrReceipt(id, paymentType);
        return "redirect:/sales/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        saleService.delete(id);
        return "redirect:/sales";
    }

    @GetMapping("/activate/{id}")
    public String activateSale(@PathVariable Long id) {
        saleService.activate(id);
        return "redirect:/sales";
    }

    @GetMapping("/edit/{id}")
    public String editSaleForm(@PathVariable Long id, Model model) {
        model.addAttribute("sale", saleService.findById(id));
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("paymentTypes", PaymentType.values());
        return "saleManagement/sales/form";
    }

    @PostMapping("/update/{id}")
    public String updateSale(@PathVariable Long id,
                           @Valid @ModelAttribute Sale sale,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("paymentTypes", PaymentType.values());
            return "saleManagement/sales/form";
        }
        saleService.updateSale(id, sale);
        return "redirect:/sales/" + id;
    }
}