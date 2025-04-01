package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.format.FormatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FormatController {
    private final FormatService formatService;
}
