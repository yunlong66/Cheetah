<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: yanyunlong
  Date: 2019-05-10
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%="hello1 jsp"%>
<br>
<%out.println("hello2 jsp"); %>

<%
    List<String> words = new ArrayList<>();
    words.add("today");
    words.add("is");
    words.add("a");
    words.add("great");
    words.add("day");
%>

<table width="200px" align="center" border="1" cellspacing="0">
<%
    for(String word : words){
%>
    <tr>
        <td><%=word%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
