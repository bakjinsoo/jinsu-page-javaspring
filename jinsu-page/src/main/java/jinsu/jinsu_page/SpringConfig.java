package jinsu.jinsu_page;

import jinsu.jinsu_page.repository.MemberRepository;
import jinsu.jinsu_page.repository.SpringDataJpaMemberRepository;
import jinsu.jinsu_page.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final SpringDataJpaMemberRepository springRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository, SpringDataJpaMemberRepository springRepository) {
        this.memberRepository = memberRepository;
        this.springRepository=springRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository,springRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }
}
