package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.publisher.Publisher;
import konoec.bookhub.domain.bookManagement.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    public String listPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAll());
        return "bookManagement/publishers/list";
    }

    @GetMapping("/{id}")
    public String getPublisher(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.findById(id));
        return "bookManagement/publishers/detail";
    }

    @GetMapping("/new")
    public String newPublisherForm(Model model) {
        model.addAttribute("publisher", new Publisher());
        return "bookManagement/publishers/form";
    }

    @PostMapping
    public String savePublisher(@Valid @ModelAttribute Publisher publisher,
                              BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/publishers/form";
        }
        publisherService.save(publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/edit/{id}")
    public String editPublisherForm(@PathVariable Long id, Model model) {
        model.addAttribute("publisher", publisherService.findById(id));
        return "bookManagement/publishers/form";
    }

    @PostMapping("/update/{id}")
    public String updatePublisher(@PathVariable Long id,
                                @Valid @ModelAttribute Publisher publisher,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "bookManagement/publishers/form";
        }
        publisherService.update(id, publisher);
        return "redirect:/publishers";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable Long id) {
        publisherService.delete(id);
        return "redirect:/publishers";
    }

    @GetMapping("/activate/{id}")
    public String activatePublisher(@PathVariable Long id) {
        publisherService.activate(id);
        return "redirect:/publishers";
    }
}