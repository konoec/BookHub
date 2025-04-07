package konoec.bookhub.domain.bookManagement.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> findAll() {
        return keywordRepository.findAll();
    }

    public Keyword findById(Long id) {
        return keywordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada"));
    }

    public Keyword save(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    public Keyword update(Long id, Keyword keyword) {
        return keywordRepository.findById(id)
                .map(existingKeyword -> {
                    existingKeyword.setName(keyword.getName());
                    return keywordRepository.save(existingKeyword);
                })
                .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada"));
    }

    public void delete(Long id) {
        keywordRepository.findById(id)
                .map(keyword -> {
                    keyword.setIsDeleted(true);
                    return keywordRepository.save(keyword);
                })
                .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada"));
    }

    public void activate(Long id) {
        keywordRepository.findById(id)
                .map(keyword -> {
                    keyword.setIsDeleted(false);
                    return keywordRepository.save(keyword);
                })
                .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada"));
    }
}

