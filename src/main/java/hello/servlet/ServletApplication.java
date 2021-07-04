package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 서블릿 자동 등록을 위한 설정
@SpringBootApplication
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

    // 스프링 부트는 뷰 리졸버를 자동으로 등록해주기 때문에 주석 처리
//    @Bean
//    ViewResolver internalResourceViewResolver() {
//        return new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
//    }
}
