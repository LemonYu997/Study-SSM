<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加帐户</title>
</head>
<body>
    <h1>添加账户信息表单</h1>
    <form name="accountForm" action="${pageContext.request.contextPath}/account/save" method="post">
        帐户名称：<input type="text" name="name"> </br>
        帐户金额：<input type="text" name="money"> </br>
        <input type="submit" name="保存"> </br>
    </form>
</body>
</html>
