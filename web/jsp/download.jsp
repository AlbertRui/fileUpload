<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/2/23
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!-- 静态下载 -->
<a href="../resources/xyz.txt">download xyz.txt</a><br><br>
<!-- 动态下载 -->
<a href="test.jsp">download test.jsp</a><br><br>
<a href="${pageContext.request.contextPath}/downloadServlet">download hello.pptx</a>
</body>
</html>
