<%@ page import="com.output.AboutProducts" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <p>Add Products for</p>
  <p><%=request.getAttribute("nameCustomer")%> and <%=request.getAttribute("nameShop")%></p>
  <%--<p><%=request.getAttribute("price")%></p>--%>

  <form method="POST" action="/edit/addProduct"> <%--id="add"--%>
    <table border="1">
      <th>Name Product</th> <th>Count</th> <th>Price</th>
      <%for (AboutProducts about : (List<AboutProducts>)request.getAttribute("products")){%>
      <tr>
        <td><%=about.getNameProduct()%></td>
        <td><%=about.getCount()%></td>
        <td><%=about.getPrice()%></td>
      </tr>
      <%}%>
      <tr>
        <td><input type="text" name="nameProduct"></td>
        <td><input type="text" name="count"></td>
        <td><input type="text" name="price"></td>
      </tr>
    </table>
    <input type="submit" value="add">
  </form>

  <form method="post" action="/edit/endAddProduct">
    <input type="submit" value="end add">
  </form>


  <%--<form action="MyServlet" method="post">
    <button type="submit" value="value" name="add">ADD</button>
  </form>--%>
  <%--<form method="post" action="bestmanager">
      <label for="start">Start date(yyyy-mm-dd)</label>
      <input type="date" name="startDate" id="start">

      <label for="end">End date(yyyy-mm-dd)</label>
      <input type="date" name="endDate" id="end">

      <input type="submit" name="show" value="Show">
    </form>--%>

</body>
</html>
