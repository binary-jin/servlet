package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name="helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("helloservlet.service");

        System.out.println("request=" +request);
        System.out.println("response = " + response);

        String username = request.getParameter("username");
        System.out.println("username =" + username);

        response.setContentType("text/plain"); //단순 문자로 보냄 -> 헤더
        response.setCharacterEncoding("utf-8"); //인코딩 정보 알려주기 -> 헤더
        response.getWriter().write("hello" + username); //바디 부분
    } //서블릿이 호출되면 이 서비스가 호출 됨

}
