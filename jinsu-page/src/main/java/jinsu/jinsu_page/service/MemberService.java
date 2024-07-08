package jinsu.jinsu_page.service;

import jinsu.jinsu_page.domain.Member;
import jinsu.jinsu_page.domain.Role;
import jinsu.jinsu_page.repository.MemberRepository;
import jinsu.jinsu_page.repository.SpringDataJpaMemberRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class MemberService implements UserDetailsService {
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
        validateDuplicateMember(member); //중복 회원 검증
        member.setRole(Role.USER);
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
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Member> _siteUser = this.memberRepository.findByName(name);
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        Member member = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(member.getRole().getValue()));
        return new User(member.getName(), member.getPassword(), authorities);
    }
}
