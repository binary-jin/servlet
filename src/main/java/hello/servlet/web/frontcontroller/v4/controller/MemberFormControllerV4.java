package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
        //model도 프론트 컨트롤러에서 다 만들어서 넘겨줄 거니까 논리 이름만 넘기면 됨
    }
}
