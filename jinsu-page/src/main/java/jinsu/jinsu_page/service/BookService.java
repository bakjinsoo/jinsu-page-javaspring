package jinsu.jinsu_page.service;

import jakarta.transaction.Transactional;
import jinsu.jinsu_page.domain.Book;
import jinsu.jinsu_page.domain.Member;
import jinsu.jinsu_page.domain.Role;
import jinsu.jinsu_page.repository.BookRepository;
import jinsu.jinsu_page.repository.SpringJpaBookRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final SpringJpaBookRepository springJpaBookRepository;

    public BookService(@Qualifier("springJpaBookRepository")BookRepository bookRepository, SpringJpaBookRepository springJpaBookRepository) {
        this.bookRepository = bookRepository;
        this.springJpaBookRepository = springJpaBookRepository;
    }
    public Long bookAdd(Book book) {
        validateDuplicateBook(book); //중복 회원 검증
        bookRepository.save(book);
        return book.getId();
    }

    private void validateDuplicateBook(Book book) {
        bookRepository.findBookByTitle(book.getTitle())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 책입니다.");
                });
    }
    public List<Book> findBooks() {
        return bookRepository.findAll();
    }
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findBookById(id);
    }
    public Book findBookByName(String title) {
        List<Book> books = findBooks();
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }
    @Transactional
    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
