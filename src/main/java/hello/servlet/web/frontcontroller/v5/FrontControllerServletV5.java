package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

        private final Map<String, Object> handlerMappingMap = new HashMap<>();
        //기존에는 Object 자리에 ControllerV4, ControllerV3가 들어갔는데 유연하게 받기 위해 Object를 넣어줌
        private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
        //list가 여러개 담겨있고 그 중에 하나를 꺼내서 써야하니


        public FrontControllerServletV5() {
                initHandlerMappingMap();

                initHandlerAdapters();
        }



        private void initHandlerMappingMap() {
                handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
                handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
                handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

                //v4 추가
                handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
                handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
                handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
        }
        private void initHandlerAdapters() {
                handlerAdapters.add(new ControllerV3HandlerAdapter()); //v3 처리 할 수 있는 handleradapter
                handlerAdapters.add(new ControllerV4HandlerAdapter()); //v4 처리 할 수 있는 handleradapter
        }

        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                Object handler = getHandler(request); //핸들러 찾아옴

                if(handler == null) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        return;
                }

                MyHandlerAdapter adapter = getHandlerAdapter(handler); //핸들러 어뎁터 찾아옴

                ModelView mv = adapter.handle(request, response, handler); //핸들을 호출

                String viewName = mv.getViewName();
                MyView view = viewResolver(viewName); //viewResolver 가져와서 viewName 꺼내옴

                view.render(mv.getModel(), request, response); //view의 render 호출하면서 model 넘겨줌
        }


        private Object getHandler(HttpServletRequest request) {
                String requestURI = request.getRequestURI();
                return handlerMappingMap.get(requestURI);
                //먼저 요청이 오면 URI에서 handlermappingMap에서 handler를 찾음
        }

        private MyHandlerAdapter getHandlerAdapter(Object handler) {
                for (MyHandlerAdapter adapter : handlerAdapters) {
                        if (adapter.supports(handler)) {
                                return adapter;
                                //컨트롤러가 들어오면 for문을 돌면서 handler를 다 찾음
                                //v3가 있으면 if문이 참을 반환 -> adapter가 v3를 처리 ->adapter 리턴
                        }
                }
                throw new IllegalArgumentException("Handler adapter를 찾을 수 없습니다 handler = " + handler);
        }

        private static MyView viewResolver(String viewName) {
                return new MyView("/WEB-INF/views/" + viewName + ".jsp");
                //구체적 코드를 따로 빼냄
        }
}
