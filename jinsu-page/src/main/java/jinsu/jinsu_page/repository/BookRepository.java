package jinsu.jinsu_page.repository;

import jinsu.jinsu_page.domain.Book;
import jinsu.jinsu_page.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findBookById(Long id);
    Optional<Book> findBookByTitle(String name);
    List<Book> findAll();
}
