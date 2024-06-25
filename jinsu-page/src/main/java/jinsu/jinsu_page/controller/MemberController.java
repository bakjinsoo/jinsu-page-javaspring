package jinsu.jinsu_page.controller;

import jinsu.jinsu_page.domain.Member;
import jinsu.jinsu_page.repository.JpaMemberRepository;
import jinsu.jinsu_page.repository.MemberRepository;
import jinsu.jinsu_page.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;


    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
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
}
