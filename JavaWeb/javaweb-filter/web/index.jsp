<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>当前有 <span><%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%></span> 个用户</h1>
  </body>
</html>
