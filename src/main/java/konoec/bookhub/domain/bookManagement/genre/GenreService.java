package konoec.bookhub.domain.bookManagement.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Genre findById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
    }

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre update(Long id, Genre genre) {
        return genreRepository.findById(id)
                .map(existingGenre -> {
                    existingGenre.setName(genre.getName());
                    return genreRepository.save(existingGenre);
                })
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
    }

    public void delete(Long id) {
        genreRepository.findById(id)
                .map(genre -> {
                    genre.setIsDeleted(true);
                    return genreRepository.save(genre);
                })
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
    }
}
