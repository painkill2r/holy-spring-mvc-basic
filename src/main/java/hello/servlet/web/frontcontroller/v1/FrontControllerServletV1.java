package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FrontController - V1(기존 Servlet 코드를 최대한 유지하면서 프론트 컨트롤러를 도입)
 * 1. 프론트 컨트롤러 서블릿 하나로 클라이언트의 요청을 받음,
 * 2. 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출.
 * - 이전에는 클라이언트의 요청에 맞는 서블릿을 직접 호출해서 사용했었음.
 * 3. 프론트 컨트롤러는 '문지기' 역할, 공통적인 처리가 가능함.
 * 4. 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨.
 */
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    // URL 매핑정보 저장 Map
    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI(); // 요청 URL 정보
        ControllerV1 controller = controllerMap.get(requestURI);

        if (requestURI == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        controller.process(request, response);
    }
}
