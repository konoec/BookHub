package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.format.Format;
import konoec.bookhub.domain.bookManagement.format.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/formats")
public class FormatController {
    private final FormatService formatService;

    @GetMapping
    public String listFormats(Model model) {
        model.addAttribute("formats", formatService.findAll());
        return "bookManagement/formats/list";
    }

    @GetMapping("/{id}")
    public String getFormat(@PathVariable Long id, Model model) {
        model.addAttribute("format", formatService.findById(id));
        return "bookManagement/formats/detail";
    }

    @GetMapping("/new")
    public String newFormatForm(Model model) {
        model.addAttribute("format", new Format());
        return "bookManagement/formats/form";
    }

    @PostMapping
    public String saveFormat(@Valid @ModelAttribute Format format,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/formats/form";
        }
        formatService.save(format);
        return "redirect:/formats";
    }

    @GetMapping("/edit/{id}")
    public String editFormatForm(@PathVariable Long id, Model model) {
        model.addAttribute("format", formatService.findById(id));
        return "bookManagement/formats/form";
    }

    @PostMapping("/update/{id}")
    public String updateFormat(@PathVariable Long id,
                             @Valid @ModelAttribute Format format,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/formats/form";
        }
        formatService.update(id, format);
        return "redirect:/formats";
    }

    @GetMapping("/delete/{id}")
    public String deleteFormat(@PathVariable Long id) {
        formatService.delete(id);
        return "redirect:/formats";
    }

    @GetMapping("/activate/{id}")
    public String activateFormat(@PathVariable Long id) {
        formatService.activate(id);
        return "redirect:/formats";
    }
}