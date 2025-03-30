package konoec.bookhub.domain.bookManagement.keyword;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "keywords")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String keyword;

    @Column(nullable = false)
    private Boolean isDeleted = false;
}
