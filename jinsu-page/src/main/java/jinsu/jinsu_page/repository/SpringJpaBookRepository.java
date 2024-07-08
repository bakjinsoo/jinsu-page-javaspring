package jinsu.jinsu_page.repository;

import jinsu.jinsu_page.domain.Book;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
@Primary
public interface SpringJpaBookRepository extends JpaRepository<Book, Long>,BookRepository {
    @Override
    Optional<Book> findBookByTitle(String name);
    List<Book> findAll();
}
