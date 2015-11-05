<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<a href="${pageContext.request.contextPath}/MyServlet">Customer favorite shop</a>

<br/><br/>

<form action="MyServlet" method="post">
    <input type="submit" value="Edit database"/>
</form>

<%--<form action="MyServlet" method="post">
    Width: <input type="text" size="5" name="width" title="Width"/>
    Height: <input type="text" size="5" name="height" title="Height"/>
    <input type="submit" value="Calculate"/>
</form>--%>

</body>
</html>
