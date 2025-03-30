package konoec.bookhub.domain.bookManagement.format;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatRepository extends JpaRepository<Format, Integer> {
}
