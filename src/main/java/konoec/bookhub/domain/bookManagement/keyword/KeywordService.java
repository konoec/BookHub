package konoec.bookhub.domain.bookManagement.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public Keyword save(Keyword keyword) {
        return keywordRepository.save(keyword);
    }
}

