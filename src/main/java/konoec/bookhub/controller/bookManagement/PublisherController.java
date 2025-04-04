package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/publishers")
public class PublisherController {
    private final PublisherService publisherService;
}
