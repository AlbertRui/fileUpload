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
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            //1.获取#addFile，并为其添加click响应函数
            var i = 2;
            $("#addFile").click(function () {
                //2.利用jQuery生成节点，注意数字的变化,其中删除按钮可以删除当前的file和desc相关节点
                $(this).parent().parent().before("<tr class=\"file\">\n" +
                    "            <td>File" + i + "</td>\n" +
                    "            <td><input type=\"file\" name=\"file" + i + "\"/></td>\n" +
                    "        </tr>\n" +
                    "        <tr class=\"desc\">\n" +
                    "            <td>Desc" + i + "</td>\n" +
                    "            <td>\n" +
                    "                <input type=\"text\" name=\"desc" + i + "\"/>\n" +
                    "                <button id=\"delete" + i + "\" type=\"button\">删除</button>\n" +
                    "            </td>\n" +
                    "        </tr>");
                i++;
                //获取新添加的删除按钮
                $("#delete" + (i - 1)).click(function () {
                    var $tr = $(this).parent().parent();
                    $tr.prev("tr").remove();
                    $tr.remove();
                    //对 i 重新排序
                    $(".file").each(function (index) {
                        var n = index + 1;
                        $(this).find("td:first").text("File" + n);
                        $(this).find("td:last input").attr("name", "file" + n);
                    });

                    $(".desc").each(function (index) {
                        var n = index + 1;
                        $(this).find("td:first").text("Desc" + n);
                        $(this).find("td:last input").attr("name", "desc" + n);
                    });
                    i = i - 1;
                });
                return false;
            });
        });
    </script>
</head>
<body>
<span style="color: red;">${message}</span>
<form action="${pageContext.request.contextPath}/uploadServlet" method="post" enctype="multipart/form-data">
    <input type="hidden" id="fileNum" name="fileNum" value="1"/><br><br>
    <table>
        <tr class="file">
            <td>File1</td>
            <td><input type="file" name="file1"/></td>
        </tr>
        <tr class="desc">
            <td>Desc1</td>
            <td><input type="text" name="desc1" title="text"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="submit" id="submit"/></td>
            <td>
                <button id="addFile" type="button">新增一个附件</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
