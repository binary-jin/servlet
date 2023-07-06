package hello.servlet.web.springmvc.v3;


import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //@RequestMapping("/new-form") //이 경우에는 GET으로 호출하든, POST로 호출하든 호출이 됨
    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    // 이렇게 method를 지정하면 지정된 메서드 이외로 호출할 수 없음
    @GetMapping("/new-form") //위의 코드를 하나로 합친 것
    public String newForm() {
        return "new-form";
        //ModelAndView로 반환하는 대신에 String으로 반환해도 됨 그러면 스프링이 알아서 view 이름으로 인식하고 진행함
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age, //요청 파라미터들을 @RequestParam으로 받고
            Model model) {

        Member member = new Member(username, age);
        memberRepository.save(member); //비즈니스 로직

        model.addAttribute("member", member); //모델에 담고
        return "save-result"; //view 이름 반환

    }

    //@RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);

        return "members";
    }
}
