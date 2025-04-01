package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.author.AuthorService;
import konoec.bookhub.domain.bookManagement.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
}
