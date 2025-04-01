package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;
}
