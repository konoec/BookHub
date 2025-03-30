package konoec.bookhub.domain.saleManagement.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleDetailRepository extends JpaRepository<SaleDetail, Integer> {
}
