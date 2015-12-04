<%@ page import="com.output.AboutProducts" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<link rel='stylesheet' href='../../resources/bootstrap-3.3.5-dist/css/bootstrap.css' type='text/css' media='all'>
<link rel='stylesheet' href='../../resources/bootstrap-3.3.5-dist/css/bootstrap.css.map' type='text/css' media='all'>
<link rel='stylesheet' href='../../resources/bootstrap-3.3.5-dist/css/bootstrap.min.css' type='text/css' media='all'>
<link rel='stylesheet' href='../../resources/bootstrap-3.3.5-dist/css/bootstrap-theme.css' type='text/css' media='all'>
<link rel='stylesheet' href='../../resources/bootstrap-3.3.5-dist/css/bootstrap-theme.css.map' type='text/css' media='all'>
<link rel='stylesheet' href='../../resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css' type='text/css' media='all'>

<meta name="viewport" content="width=device-width, initial-scale=1">

<head>
    <title></title>
</head>
<body>

  <div class="center-block"><h3 class="bg-info">Adding products</h3></div>
  <div class="center-block"><h3 class="bg-info"><%=request.getAttribute("nameCustomer")%> and <%=request.getAttribute("nameShop")%></h3></div>

  <form method="POST" action="/edit/addProduct"> <%--id="add"--%>
    <table class="table">
      <th class="btn-primary">Name Product</th>
      <th class="btn-primary">Count</th>
      <th class="btn-primary">Price</th>
      <%for (AboutProducts about : (List<AboutProducts>)request.getAttribute("products")){%>
        <tr class="bg-info">
          <td class="active"><%=about.getNameProduct()%></td>
          <td class="active"><%=about.getCount()%></td>
          <td class="active"><%=about.getPrice()%></td>
        </tr>
      <%}%>
      <tr class="bg-info">
        <td class="active"><input type="text" name="nameProduct"></td>
        <td class="active"><input type="text" name="count"></td>
        <td class="active"><input type="text" name="price"></td>
      </tr>
    </table>
    <input type="submit" value="add">
  </form>

  <form method="post" action="/edit/endAddProduct">
    <input type="submit" value="end add" class="btn btn-btn-primary">
  </form>

  <a href="/">back to main page</a>
  <%--http://10.1.1.98:8080/--%>
</body>
</html>
