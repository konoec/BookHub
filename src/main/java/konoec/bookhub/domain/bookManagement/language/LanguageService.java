package konoec.bookhub.domain.bookManagement.language;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public List<Language> findAll() {
        return languageRepository.findAll();
    }

    public Language findById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado"));
    }

    public Language save(Language language) {
        return languageRepository.save(language);
    }

    public Language update(Long id, Language language) {
        return languageRepository.findById(id)
                .map(existingLanguage -> {
                    existingLanguage.setName(language.getName());
                    return languageRepository.save(existingLanguage);
                })
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado"));
    }

    public void delete(Long id) {
        languageRepository.findById(id)
                .map(language -> {
                    language.setIsDeleted(true);
                    return languageRepository.save(language);
                })
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado"));
    }

    public void activate(Long id) {
        languageRepository.findById(id)
                .map(language -> {
                    language.setIsDeleted(false);
                    return languageRepository.save(language);
                })
                .orElseThrow(() -> new RuntimeException("Idioma no encontrado"));
    }
}
