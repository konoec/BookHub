package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;
}
