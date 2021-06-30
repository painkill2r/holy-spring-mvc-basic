package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class HelloData {

    private String username;
    private int age;
    private List<String> fruits;
    private Map<String, String> address;
    private Map<String, Object> teamInfo;
}
