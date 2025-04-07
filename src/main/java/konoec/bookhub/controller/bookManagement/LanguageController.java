package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.language.Language;
import konoec.bookhub.domain.bookManagement.language.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    @GetMapping
    public String listLanguages(Model model) {
        model.addAttribute("languages", languageService.findAll());
        return "bookManagement/languages/list";
    }

    @GetMapping("/{id}")
    public String getLanguage(@PathVariable Long id, Model model) {
        model.addAttribute("language", languageService.findById(id));
        return "bookManagement/languages/detail";
    }

    @GetMapping("/new")
    public String newLanguageForm(Model model) {
        model.addAttribute("language", new Language());
        return "bookManagement/languages/form";
    }

    @PostMapping
    public String saveLanguage(@Valid @ModelAttribute Language language,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/languages/form";
        }
        languageService.save(language);
        return "redirect:/languages";
    }

    @GetMapping("/edit/{id}")
    public String editLanguageForm(@PathVariable Long id, Model model) {
        model.addAttribute("language", languageService.findById(id));
        return "bookManagement/languages/form";
    }

    @PostMapping("/update/{id}")
    public String updateLanguage(@PathVariable Long id,
                               @Valid @ModelAttribute Language language,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/languages/form";
        }
        languageService.update(id, language);
        return "redirect:/languages";
    }

    @GetMapping("/delete/{id}")
    public String deleteLanguage(@PathVariable Long id) {
        languageService.delete(id);
        return "redirect:/languages";
    }

    @GetMapping("/activate/{id}")
    public String activateLanguage(@PathVariable Long id) {
        languageService.activate(id);
        return "redirect:/languages";
    }
}