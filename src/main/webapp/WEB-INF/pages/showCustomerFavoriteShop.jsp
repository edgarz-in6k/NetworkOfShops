<%@ page import="com.output.AboutPurchasesCustomer" %>
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
      <title>Customers info</title>
  </head>
  <body>

  <div class="center-block"><h3 class="bg-info">Favorites of shops and shoppers</h3></div>

    <table class="table">
        <th class="btn-primary">Customers</th>
        <th class="btn-primary">Shops</th>
        <th class="btn-primary">Count visits</th>
        <th class="btn-primary">Sum $</th>
      <%for (AboutPurchasesCustomer about : (List<AboutPurchasesCustomer>)request.getAttribute("aboutPurchasesCustomers")) {%>
        <tr class="bg-info">
          <td class="active"><%=about.getNameCustomer()%></td>
          <td class="active"><%=about.getNameShop()%></td>
          <td class="active"><%=about.getVisit()%></td>
          <td class="active"><%=about.getSum()%></td>
        </tr>
      <%}%>
    </table>

    <a href="/">back to main page</a>

  </body>
</html>