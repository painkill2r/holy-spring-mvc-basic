package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        Map<String, String> paramMap = createParamMap(request);
        ControllerV4 controller = (ControllerV4) handler;
        HashMap<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);
        ModelView mv = new ModelView(viewName); // 어댑터는 ModelView를 반환해야 한다는 조건을 만족하기 위해 ModelView 객체를 생성
        mv.setModel(model);

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
