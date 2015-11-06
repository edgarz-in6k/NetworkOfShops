<%@ page import="com.output.AboutPurchasesCustomer" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Customers info</title>
  </head>
  <body>

  <h1>Favorites of shops and shoppers</h1>

    <table border="1" align="center">
      <th>Customers</th> <th>Shops</th> <th>Count visits</th> <th>Sum $</th>
      <%for (AboutPurchasesCustomer about : (List<AboutPurchasesCustomer>)request.getAttribute("aboutPurchasesCustomers")) {%>
        <tr>
          <%for (String component : about) {%>
                <td><%=component%></td>
            <%}%>
        </tr>
      <%}%>
    </table>

    <a href="/">back to main page</a>

  </body>
</html>