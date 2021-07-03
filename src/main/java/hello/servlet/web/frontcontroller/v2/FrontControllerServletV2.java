package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FrontController - V2(VIEW 분리)
 */
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    // URL 매핑정보 저장 Map
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberListControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberListControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // 요청 URL 정보
        ControllerV2 controller = controllerMap.get(requestURI);

        if (requestURI == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        controller.process(request, response);
    }
}