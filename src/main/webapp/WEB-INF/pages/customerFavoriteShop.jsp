<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Customers info</title>
  </head>
  <body>

  <h1>${aboutPurchasesCustomers}</h1>

    <table border="1" align="center">
      <th>Customers</th> <th>Shops</th> <th>Count visits</th> <th>Sum $</th>
      <%--<%for (AboutPurchasesCustomer about : (List<AboutPurchasesCustomer>)request.getAttribute("aboutPurchasesCustomers")) {%>
        <tr>
          <%for (String component : about) {%>
                <td><%=component%></td>
            <%}%>
        </tr>
      <%}%>--%>
    </table>

  </body>
</html>