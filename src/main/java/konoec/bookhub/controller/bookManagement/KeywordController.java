package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.keyword.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class KeywordController {
    private final KeywordService keywordService;
}
