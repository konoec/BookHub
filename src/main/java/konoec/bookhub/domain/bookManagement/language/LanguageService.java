package konoec.bookhub.domain.bookManagement.language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public Language save(Language language) {
        return languageRepository.save(language);
    }
}
