package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.genre.Genre;
import konoec.bookhub.domain.bookManagement.genre.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    @GetMapping
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "bookManagement/genres/list";
    }

    @GetMapping("/{id}")
    public String getGenre(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "bookManagement/genres/detail";
    }

    @GetMapping("/new")
    public String newGenreForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "bookManagement/genres/form";
    }

    @PostMapping
    public String saveGenre(@Valid @ModelAttribute Genre genre,
                          BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/genres/form";
        }
        genreService.save(genre);
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String editGenreForm(@PathVariable Long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "bookManagement/genres/form";
    }

    @PostMapping("/update/{id}")
    public String updateGenre(@PathVariable Long id,
                            @Valid @ModelAttribute Genre genre,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/genres/form";
        }
        genreService.update(id, genre);
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.delete(id);
        return "redirect:/genres";
    }

    @GetMapping("/activate/{id}")
    public String activateGenre(@PathVariable Long id) {
        genreService.activate(id);
        return "redirect:/genres";
    }
}