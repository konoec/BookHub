package konoec.bookhub.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookKeywordId implements Serializable {
    private Integer bookId;
    private String keyword;
}
