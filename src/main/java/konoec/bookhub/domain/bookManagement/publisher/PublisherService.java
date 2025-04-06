package konoec.bookhub.domain.bookManagement.publisher;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher findById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Editor no encontrado"));
    }

    public Publisher save(Publisher publisher) {
        return publisherRepository.save(publisher);
    }

    public Publisher update(Long id, Publisher publisher) {
        return publisherRepository.findById(id)
                .map(existingPublisher -> {
                    existingPublisher.setName(publisher.getName());
                    return publisherRepository.save(existingPublisher);
                })
                .orElseThrow(() -> new RuntimeException("Editor no encontrado"));
    }

    public void delete(Long id) {
        publisherRepository.findById(id)
                .map(publisher -> {
                    publisher.setIsDeleted(true);
                    return publisherRepository.save(publisher);
                })
                .orElseThrow(() -> new RuntimeException("Editor no encontrado"));
    }
}
