package jinsu.jinsu_page.service;

import jinsu.jinsu_page.domain.Member;
import jinsu.jinsu_page.repository.MemberRepository;
import jinsu.jinsu_page.repository.SpringDataJpaMemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
public class MemberService {
    private final MemberRepository memberRepository;
    private final SpringDataJpaMemberRepository springRepository;
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public MemberService(MemberRepository memberRepository, SpringDataJpaMemberRepository springRepository) {
        this.memberRepository = memberRepository;
        this.springRepository = springRepository;
    }

    /**
     *회원가입
     */
    public Long join(Member member) {
        //중복회원 X
        validateDuplicateMember(member);//중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
    @Transactional
    public List<Member> searchName(String keyword)  {
        List<Member>members= springRepository.searchByNameContaining(keyword);
        return members;
    }
}
