package konoec.bookhub.domain.bookManagement.book;

import konoec.bookhub.domain.bookManagement.genre.Genre;
import konoec.bookhub.domain.bookManagement.genre.GenreRepository;
import konoec.bookhub.domain.bookManagement.keyword.Keyword;
import konoec.bookhub.domain.bookManagement.keyword.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final KeywordRepository keywordRepository;
    private final GenreRepository genreRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public Book findById(Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book update(Long id, Book book) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setPublicationDate(book.getPublicationDate());
                    existingBook.setPublisher(book.getPublisher());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setLanguage(book.getLanguage());
                    existingBook.setPageCount(book.getPageCount());
                    existingBook.setFormat(book.getFormat());
                    existingBook.setWidth(book.getWidth());
                    existingBook.setHeight(book.getHeight());
                    existingBook.setDepth(book.getDepth());
                    existingBook.setWeight(book.getWeight());
                    existingBook.setEdition(book.getEdition());
                    existingBook.setIllustrations(book.getIllustrations());
                    existingBook.setSeries(book.getSeries());
                    existingBook.setNotes(book.getNotes());
                    existingBook.setSellingPrice(book.getSellingPrice());
                    existingBook.setStock(book.getStock());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setKeywords(book.getKeywords());
                    existingBook.setGenres(book.getGenres());
                    existingBook.setImagePath(book.getImagePath());
                    return bookRepository.save(existingBook);
                })
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public void delete(Long id) {
        bookRepository.findById(id)
                .map(book -> {
                    book.setIsDeleted(true);
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public List<Book> findByGenreId(Long genreId) {
        return bookRepository.findByGenreId(genreId);
    }

    public List<Book> findByKeywordId(Long keywordId) {
        return bookRepository.findByKeywordId(keywordId);
    }

    public Book addKeyword(Long bookId, Long keywordId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Keyword keyword = keywordRepository.findById(keywordId)
                .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada"));
        book.getKeywords().add(keyword);
        return bookRepository.save(book);
    }

    public Book removeKeyword(Long bookId, Long keywordId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Keyword keyword = keywordRepository.findById(keywordId)
                .orElseThrow(() -> new RuntimeException("Palabra clave no encontrada"));
        book.getKeywords().remove(keyword);
        return bookRepository.save(book);
    }

    public Book addGenre(Long bookId, Long genreId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
        book.getGenres().add(genre);
        return bookRepository.save(book);
    }

    public Book removeGenre(Long bookId, Long genreId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(() -> new RuntimeException("Género no encontrado"));
        book.getGenres().remove(genre);
        return bookRepository.save(book);
    }
}
