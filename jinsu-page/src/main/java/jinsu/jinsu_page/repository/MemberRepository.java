package jinsu.jinsu_page.repository;

import jinsu.jinsu_page.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    List<Member> searchByName(String name);
}
