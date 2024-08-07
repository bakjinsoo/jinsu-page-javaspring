package jinsu.jinsu_page.repository;

import jinsu.jinsu_page.domain.Book;
import jinsu.jinsu_page.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>,MemberRepository {
    @Override
    Optional<Member> findByName(String name);

    @Override
    List<Member> searchByName(String name);

    List<Member> searchByNameContaining(String keyword);
}
