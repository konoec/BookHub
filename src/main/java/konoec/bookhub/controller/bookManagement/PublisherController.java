package konoec.bookhub.controller.bookManagement;

import konoec.bookhub.domain.bookManagement.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;
}
