<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--内置对象--%>
<%
    pageContext.setAttribute("name1","K1"); //保存的数据只在一个页面中有效
    request.setAttribute("name2","K2"); //保存的数据只在一次请求中有效，请求转发会携带这个数据
    session.setAttribute("name3","K3"); //保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
    application.setAttribute("name4","K4"); //保存的数据在服务器中有效，从打开服务器到关闭服务器
%>

<%--脚本片段中的代码会被原封不动的生成到JSP.java中
要求：这里面的代码，必须保证java语法的正确性
--%>
<%
    //从pageContext取出，通过寻找的方式
    //从底层到高层（作用域）
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    String name5 = (String) pageContext.findAttribute("name5"); //不存在


%>

<%--使用EL表达式输出 ${} --%>
<h1>取出的值为：</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3>${name5}</h3>


</body>
</html>
