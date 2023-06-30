package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); //=(200)으로 써도 됨

        //[response-header]
        response.setHeader("Content-type", "text/plain; charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, ,must-revalidate"); //캐시를 완전 무효화
        response.setHeader("Pragma","no-cache"); //과거 버전까지 캐시 무효화
        response.setHeader("my-header", "hello");

        //[Header 편의 메서드]
        //content(response);
        cookie(response);

        PrintWriter writer = response.getWriter();
        writer.println("ok");

    }

    private void content(HttpServletResponse response) {
        //Content-Type : text/plain; charset=utf-8
        //Content-Length:2
        //response.setHeader("Content-Type", "text/plain; charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //생략시 자동 생성
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie : myCookie=good; Max-Age=600;
        //response.serHeader("Set-Cookie", "myCookie=Good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
}