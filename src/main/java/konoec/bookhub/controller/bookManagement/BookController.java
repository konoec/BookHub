package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.author.AuthorService;
import konoec.bookhub.domain.bookManagement.book.Book;
import konoec.bookhub.domain.bookManagement.book.BookRepository;
import konoec.bookhub.domain.bookManagement.book.BookService;
import konoec.bookhub.domain.bookManagement.format.FormatService;
import konoec.bookhub.domain.bookManagement.genre.GenreService;
import konoec.bookhub.domain.bookManagement.keyword.Keyword;
import konoec.bookhub.domain.bookManagement.keyword.KeywordService;
import konoec.bookhub.domain.bookManagement.language.LanguageService;
import konoec.bookhub.domain.bookManagement.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final LanguageService languageService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final FormatService formatService;
    private final GenreService genreService;
    private final KeywordService keywordService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "bookManagement/books/list";
    }

    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "bookManagement/books/detail";
    }

    @GetMapping("/new")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        addFormAttributes(model);
        return "bookManagement/books/form";
    }

    @PostMapping
    public String saveBook(@Valid @ModelAttribute Book book,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            return "bookManagement/books/form";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        addFormAttributes(model);
        return "bookManagement/books/form";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                           @Valid @ModelAttribute Book book,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            return "bookManagement/books/form";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/activate/{id}")
    public String activateBook(@PathVariable Long id) {
        bookService.activate(id);
        return "redirect:/books";
    }

    @GetMapping("/genre/{genreId}")
    public String getBooksByGenre(@PathVariable Long genreId, Model model) {
        model.addAttribute("books", bookService.findByGenreId(genreId));
        return "bookManagement/books/list";
    }

    @PostMapping("/{bookId}/genres/add/{genreId}")
    public String addGenreToBook(@PathVariable Long bookId, @PathVariable Long genreId) {
        bookService.addGenre(bookId, genreId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{bookId}/genres/remove/{genreId}")
    public String removeGenreFromBook(@PathVariable Long bookId, @PathVariable Long genreId) {
        bookService.removeGenre(bookId, genreId);
        return "redirect:/books/" + bookId;
    }

    @GetMapping("/keyword/{keywordId}")
    public String getBooksByKeyword(@PathVariable Long keywordId, Model model) {
        model.addAttribute("books", bookService.findByKeywordId(keywordId));
        return "bookManagement/books/list";
    }

    @PostMapping("/{bookId}/keywords/add/{keywordId}")
    public String addKeywordToBook(@PathVariable Long bookId, @PathVariable Long keywordId) {
        bookService.addKeyword(bookId, keywordId);
        return "redirect:/books/" + bookId;
    }

    @PostMapping("/{bookId}/keywords/remove/{keywordId}")
    public String removeKeywordFromBook(@PathVariable Long bookId, @PathVariable Long keywordId) {
        bookService.removeKeyword(bookId, keywordId);
        return "redirect:/books/" + bookId;
    }

    private void addFormAttributes(Model model) {
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("languages", languageService.findAll());
        model.addAttribute("formats", formatService.findAll());
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("keywords", keywordService.findAll());
    }
}