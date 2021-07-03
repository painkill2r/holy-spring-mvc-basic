package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

/**
 * 기본적인 구조는 V3와 같지만 컨트롤러가 ModelView를 반환하지 않고, ViewName만 반환한다.
 */
public interface ControllerV4 {

    /**
     * @param paramMap
     * @param model
     * @return
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}
