package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.author.Author;
import konoec.bookhub.domain.bookManagement.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "bookManagement/authors/list";
    }

    @GetMapping("/{id}")
    public String getAuthor(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "bookManagement/authors/detail";
    }

    @GetMapping("/new")
    public String newAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "bookManagement/authors/form";
    }

    @PostMapping
    public String saveAuthor(@Valid @ModelAttribute Author author,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/authors/form";
        }
        authorService.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "bookManagement/authors/form";
    }

    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id,
                             @Valid @ModelAttribute Author author,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/authors/form";
        }
        authorService.update(id, author);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

    @GetMapping("/activate/{id}")
    public String activateAuthor(@PathVariable Long id) {
        authorService.activate(id);
        return "redirect:/authors";
    }
}