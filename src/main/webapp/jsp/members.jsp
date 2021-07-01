<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    List<Member> members = memberRepository.findAll();
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>username</th>
                    <th>age</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Member member : members) {
                        out.write("<tr>\n");
                        out.write("<td>" + member.getId() + "</td>\n");
                        out.write("<td>" + member.getUsername() + "</td>\n");
                        out.write("<td>" + member.getAge() + "</td>\n");
                        out.write("</tr>\n");
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
