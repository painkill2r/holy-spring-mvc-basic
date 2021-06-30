package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import hello.servlet.basic.Team;

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

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    // Spring에서 제공하는 객체를 JSON 문자열로 변환하기 위한 라이브러리 설정
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json;charset=UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("hello");
        helloData.setAge(20);

        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("apple");
        fruits.add("orange");
        helloData.setFruits(fruits);

        Map<String, String> address = new HashMap<>();
        address.put("zip", "12345");
        address.put("basic", "ABCD EFGH");
        address.put("detail", "XYZ QWER");
        helloData.setAddress(address);

        Team team = new Team();
        team.setName("spring");
        team.setPerson(5);

        ArrayList<String> history = new ArrayList<>();
        history.add("2020 generated");
        history.add("2021 active");
        team.setHistory(history);

        Map<String, Object> teamInfo = new HashMap<>();
        teamInfo.put("first", team);
        helloData.setTeamInfo(teamInfo);

        // {"username":"hello", "age":20}
        String result = objectMapper.writeValueAsString(helloData);

        response.getWriter().write(result);
    }
}
