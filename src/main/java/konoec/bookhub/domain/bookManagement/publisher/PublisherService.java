package konoec.bookhub.domain.bookManagement.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }
}
