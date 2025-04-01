package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.language.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LanguageController {
    private final LanguageService languageService;
}
