package konoec.bookhub.domain.bookManagement.format;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FormatService {
    private final FormatRepository formatRepository;

    public Format save(Format format) {
        return formatRepository.save(format);
    }
}
