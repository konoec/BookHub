package konoec.bookhub.domain.bookManagement.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
