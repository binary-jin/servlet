package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Controller가 있으면 스프링이 자동으로 스프링 빈으로 등록(내부에 @Component 어노테이션이 있어서 컴포넌트 스캔의 대상이 됨
//스프링 MVC에서 어노테이션 기반 컨트롤러로 인식 (RequestMappingHandlerMapping에서 핸들러 정보구나 하고 꺼낼 수 있는 대상이 됨)
public class SpringMemberFormControllerV1 {

    @RequestMapping("springmvc/v1/members/new-form")
    //요청 정보를 매핑함. 해당 URL이 호출되면 이 메서드가 호출 됨
    public ModelAndView process() {
        return new ModelAndView("new-form");
        //ModelAndView : 모델과 뷰의 정보를 담아 반환
    }
}
