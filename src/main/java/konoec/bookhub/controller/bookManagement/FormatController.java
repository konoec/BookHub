package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.format.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/formats")
public class FormatController {
    private final FormatService formatService;
}
