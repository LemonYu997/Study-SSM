<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/quick22" method="post">
    名称：<input type="text" name="username"> <br/>
    文件：<input type="file" name="upload"> <br/>
</form>
</body>
</html>
