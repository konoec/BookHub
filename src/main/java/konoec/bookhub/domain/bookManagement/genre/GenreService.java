package konoec.bookhub.domain.bookManagement.genre;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepository genreRepository;

    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }
}
