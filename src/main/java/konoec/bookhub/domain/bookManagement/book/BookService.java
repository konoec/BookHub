package konoec.bookhub.domain.bookManagement.book;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
