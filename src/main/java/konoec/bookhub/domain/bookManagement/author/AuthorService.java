package konoec.bookhub.domain.bookManagement.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    public Author edit(Long id, Author author) {
        return authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(author.getName());
                    return authorRepository.save(existingAuthor);
                })
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
    }

    public void delete(Long id) {
        authorRepository.findById(id)
                .map(author -> {
                    author.setIsDeleted(true);
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
    }
}
