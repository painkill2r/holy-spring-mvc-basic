package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    /**
     * 해당 서블릿이 호출되면 실행되는 메소드
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + res);

        // 요청 파라미터 출력
        String username = req.getParameter("username");
        System.out.println("username = " + username);

        // 응답 출력
        res.setContentType("text/plain");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write("Hello " + username);
    }
}
