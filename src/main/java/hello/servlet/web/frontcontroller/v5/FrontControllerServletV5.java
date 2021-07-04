package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>(); // 모든 컨트롤러를 지원하기 위해 Object로 설정
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 여러 핸들러 어탭터를 담을 수 있는 List 선언

    public FrontControllerServletV5() {
        initHandlerMappings();
        initHandlerAdapters();
    }

    private void initHandlerMappings() {
        // V3 추가
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        // V4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV5.service");

        Object handler = getHandler(request); // 핸들러 조회

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 핸들러 어댑터 조회
        ModelView mv = adapter.handle(request, response, handler); // 핸들러 실행
        String viewName = mv.getViewName(); // 논리 이름
        MyView view = viewResolver(viewName); // 물리 이름

        view.render(mv.getModel(), request, response);
    }

    /**
     * 요청 URL을 처리할 핸들러를 찾아 반환하는 메소드
     *
     * @param request
     * @return
     */
    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        Object handler = handlerMappingMap.get(requestURI);

        return handler;
    }

    /**
     * 핸들러 어댑터 목록에서 현재 핸들러를 지원하는 어댑터를 찾아 반환하는 메소드
     *
     * @param handler
     * @return
     */
    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }

        throw new IllegalArgumentException("Handler Adapter를 찾을 수 없습니다. handler = " + handler);
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
