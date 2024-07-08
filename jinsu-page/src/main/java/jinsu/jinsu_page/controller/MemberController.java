package jinsu.jinsu_page.controller;

import jinsu.jinsu_page.domain.Book;
import jinsu.jinsu_page.domain.Member;
import jinsu.jinsu_page.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Service
@Controller
public class MemberController {
    private final MemberService memberService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberController(MemberService memberService,PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @GetMapping("/members/login")
    public String loginForm() {
        return "members/loginForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form, Model model) {
        try {
            Member member = new Member();
            member.setName(form.getName());
            member.setPassword(passwordEncoder.encode(form.getPassword()));
            memberService.join(member);
            return "redirect:/";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "home";
        }
    }
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members=memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
//    @PostMapping("/members/search")
//    public String search() {
//        return "members/memberSearch.html";
//    }
    @GetMapping("members/search")
    public String afterSearch(Model model, @RequestParam(value = "keyword")String keyword) {
        List<Member> members=memberService.searchName(keyword);
        model.addAttribute("members", members);
        return "members/memberSearch.html";
    }
    @GetMapping("books/libraryHome")
    public String library() {
        return "books/libraryHome";
    }
}
