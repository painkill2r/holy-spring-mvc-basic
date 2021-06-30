package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Team {

    private String name;
    private int person;
    private List<String> history;
}
