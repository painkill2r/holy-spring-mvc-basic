package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * ModelView Class
 * 이전에 컨트롤러에서 서블릿에 종속적인 HttpServletRequest를 사용했다면,
 * 서블릿의 종속성을 제거하기 위해 Model을 직접 만들고, 추가로 View 이름까지 전달하는 객체 생성
 * <p>
 * (다른 버전의 프론트 컨트롤러에서도 사용)
 */
@Getter
@Setter
public class ModelView {

    private String viewName;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
