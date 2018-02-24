<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/24
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    //1. 通知客户端浏览器: 这是一个需要下载的文件, 不能再按普通的 html 的方式打开.
    //即设置一个响应的类型: application/x-msdownload
    response.setContentType("application/x-msdownload");

    //2. 通知客户端浏览器: 不再有浏览器来处理该文件, 而是交由用户自行处理
    //设置用户处理的方式: 响应头: Content-Disposition
    response.setHeader("Content-Disposition", "attachment;filename=test.txt");
%>
<h1>Test Page</h1>
Time:<%=new Date()%>
</body>
</html>
