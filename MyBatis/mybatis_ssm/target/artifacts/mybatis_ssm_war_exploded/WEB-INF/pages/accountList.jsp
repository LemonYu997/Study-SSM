<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>帐户信息</title>
</head>
<body>
    <h1>展示帐户数据列表</h1>
    <table border="1">
        <tr>
            <th>帐户id</th>
            <th>帐户名称</th>
            <th>帐户金额</th>
        </tr>
        <c:forEach items="${accountList}" var="account">
            <tr>
                <td>${account.id}</td>
                <td>${account.name}</td>
                <td>${account.money}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
