package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 과거 버전 스프링의 Controller
 * HandlerMapping : BeanNameUrlHandlerMapping(스프링 빈 이름으로 핸들러를 찾음)
 * HandlerAdapter : SimpleControllerHandlerAdapter(Controller 인터페이스(애노테이션 x, 과거에 사용) 처리)
 */
@Component("/springmvc/old-controller") // 스프링 Bean 이름 설정
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
