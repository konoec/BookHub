package konoec.bookhub.controller.saleManagement;

import konoec.bookhub.domain.saleManagement.customer.Customer;
import konoec.bookhub.domain.saleManagement.customer.CustomerService;
import konoec.bookhub.domain.saleManagement.util.DocumentType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "saleManagement/customers/list";
    }

    @GetMapping("/{id}")
    public String getCustomer(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "saleManagement/customers/detail";
    }

    @GetMapping("/new")
    public String newCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("documentTypes", DocumentType.values());
        return "saleManagement/customers/form";
    }

    @PostMapping
    public String saveCustomer(@Valid @ModelAttribute Customer customer, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("documentTypes", DocumentType.values());
            return "saleManagement/customers/form";
        }
        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("documentTypes", DocumentType.values());
        return "saleManagement/customers/form";
    }

    @PostMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @Valid @ModelAttribute Customer customer,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("documentTypes", DocumentType.values());
            return "saleManagement/customers/form";
        }
        customerService.update(id, customer);
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return "redirect:/customers";
    }

    @GetMapping("/activate/{id}")
    public String activateCustomer(@PathVariable Long id) {
        customerService.activate(id);
        return "redirect:/customers";
    }
}