package konoec.bookhub.domain.saleManagement.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setFullName(customer.getFullName());
                    existingCustomer.setDocumentType(customer.getDocumentType());
                    existingCustomer.setDocumentNumber(customer.getDocumentNumber());
                    existingCustomer.setEmail(customer.getEmail());
                    existingCustomer.setPhone(customer.getPhone());
                    existingCustomer.setAddress(customer.getAddress());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void delete(Long id) {
        customerRepository.findById(id)
                .map(customer -> {
                    customer.setIsDeleted(true);
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void activate(Long id) {
        customerRepository.findById(id)
                .map(customer -> {
                    customer.setIsDeleted(false);
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}
