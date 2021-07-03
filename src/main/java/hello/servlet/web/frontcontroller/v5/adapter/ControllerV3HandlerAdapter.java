package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ControllerV3를 지원하는 핸들러 어댑터
 */
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    /**
     * 핸들러가 ControllerV3의 인스턴스인지 확인
     *
     * @param handler 컨트롤러를 말한다.
     * @return
     */
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Map<String, String> paramMap = createParamMap(request);
        ControllerV3 controller = (ControllerV3) handler;
        ModelView mv = controller.process(paramMap);

        return mv;
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
}
