package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 핸들러 어댑터 : 다양한 종류의 컨트롤러를 호출할 수 있음
 * 핸들러 : 컨트롤러의 이름을 더 넓은 범위인 핸들러로 변경.
 * 꼭 컨트롤러의 개념뿐만 아니라 어떠한 것이든 해당하는 종류의 어댑터만 있으면 다 처리할 수 있기 때문이다.
 */
public interface MyHandlerAdapter {

    /**
     * 어댑터가 해당 컨트롤러를 처리할 수 있는지 판단하는 메소드
     *
     * @param handler 컨트롤러를 말한다.
     * @return
     */
    boolean supports(Object handler);

    /**
     * 실제 컨트롤러를 호출하고, 그 결과로 ModelView를 반환하는 메소드
     * 실제 컨트롤러가 ModelView를 반환하지 못하면, 어댑터가 ModelView를 직접 생성해서라도 반환해야 한다.
     * 이전에는 프론트 컨트롤러가 실제 컨트롤러를 호출했지만 이제는 어댑터를 통해서 실제 컨트롤러가 호출된다.
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
