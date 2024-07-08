package jinsu.jinsu_page.repository;

import jakarta.persistence.EntityManager;
import jinsu.jinsu_page.domain.Book;
import jinsu.jinsu_page.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class JpaBookRepository implements BookRepository{
    private EntityManager em;
    public JpaBookRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        Book book = em.find(Book.class, id);
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> findBookByTitle(String name) {
        List<Book> result = em.createQuery("select b from Book b where b.title = :name", Book.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("select b from Book b",Book.class).getResultList();
    }

}
