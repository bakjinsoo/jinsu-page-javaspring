package jinsu.jinsu_page;

import jinsu.jinsu_page.repository.BookRepository;
import jinsu.jinsu_page.repository.MemberRepository;
import jinsu.jinsu_page.repository.SpringDataJpaMemberRepository;
import jinsu.jinsu_page.repository.SpringJpaBookRepository;
import jinsu.jinsu_page.service.BookService;
import jinsu.jinsu_page.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    private final SpringDataJpaMemberRepository springRepository;
    private final SpringJpaBookRepository springBookRepository;
    private final BookRepository bookRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository, SpringDataJpaMemberRepository springRepository, @Qualifier("springJpaBookRepository") SpringJpaBookRepository springBookRepository,
                        @Qualifier("jpaBookRepository") BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.springRepository = springRepository;
        this.springBookRepository = springBookRepository;
        this.bookRepository = bookRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository,springRepository);
    }
    @Bean
    public BookService bookService() {
        return new BookService(bookRepository,springBookRepository);
    }
//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JpaMemberRepository(em);
//    }
}
