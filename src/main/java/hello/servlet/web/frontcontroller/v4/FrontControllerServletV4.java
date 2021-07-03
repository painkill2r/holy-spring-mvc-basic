package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * FrontController - V4(실용적인 컨트롤러 구현)
 * ModelView를 사용하지 않는게 특징임.
 */
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    // URL 매핑정보 저장 Map
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI(); // 요청 URL 정보
        ControllerV4 controller = controllerMap.get(requestURI);

        if (requestURI == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        // paramMap
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); // 프론트 컨트롤러에서 URL에 매핑된 컨트롤러를 실행시켜 Model을 만들기 위해 추가
        String viewName = controller.process(paramMap, model);
        MyView view = viewResolver(viewName);

        view.render(model, request, response);
    }

    /**
     * 요청 데이터들을 Map으로 반환
     *
     * @param request
     * @return
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

        return paramMap;
    }

    /**
     * View 이름 중복 제거
     * 컨트롤러는 View의 논리 이름을 반환하고, 실제 물리 위치의 이름은 프론트 컨트롤러에서 처리
     * 향후 View의 폴더 위치가 변경되어도 프론트 컨트롤러만 수정하면 된다.
     *
     * @param viewName
     * @return
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
