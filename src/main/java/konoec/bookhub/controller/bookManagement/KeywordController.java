package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.keyword.Keyword;
import konoec.bookhub.domain.bookManagement.keyword.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/keywords")
public class KeywordController {
    private final KeywordService keywordService;

    @GetMapping
    public String listKeywords(Model model) {
        model.addAttribute("keywords", keywordService.findAll());
        return "bookManagement/keywords/list";
    }

    @GetMapping("/{id}")
    public String getKeyword(@PathVariable Long id, Model model) {
        model.addAttribute("keyword", keywordService.findById(id));
        return "bookManagement/keywords/detail";
    }

    @GetMapping("/new")
    public String newKeywordForm(Model model) {
        model.addAttribute("keyword", new Keyword());
        return "bookManagement/keywords/form";
    }

    @PostMapping
    public String saveKeyword(@Valid @ModelAttribute Keyword keyword,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/keywords/form";
        }
        keywordService.save(keyword);
        return "redirect:/keywords";
    }

    @GetMapping("/edit/{id}")
    public String editKeywordForm(@PathVariable Long id, Model model) {
        model.addAttribute("keyword", keywordService.findById(id));
        return "bookManagement/keywords/form";
    }

    @PostMapping("/update/{id}")
    public String updateKeyword(@PathVariable Long id,
                              @Valid @ModelAttribute Keyword keyword,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/keywords/form";
        }
        keywordService.update(id, keyword);
        return "redirect:/keywords";
    }

    @GetMapping("/delete/{id}")
    public String deleteKeyword(@PathVariable Long id) {
        keywordService.delete(id);
        return "redirect:/keywords";
    }

    @GetMapping("/activate/{id}")
    public String activateKeyword(@PathVariable Long id) {
        keywordService.activate(id);
        return "redirect:/keywords";
    }
}