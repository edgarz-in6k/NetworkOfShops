<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.core.ProductName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

  <table border="1">
    <th>id</th> <th>Customers</th> <th>Shops</th> <th>Name P</th> <th>Count P</th> <th>Price P</th> <th>Delete</th>
    <%for (Object object : (List<Object>)request.getAttribute("list")) {%>
      <%Object[] objects = (Object[])object;%>
        <tr>
          <td><%=objects[0]%></td>
          <td><%=objects[1]%></td>
          <td><%=objects[2]%></td>
          <td><%=objects[5]%></td>
          <td><%=objects[6]%></td>
          <td><%=objects[7]%></td>
          <%--<td><a href="?carId=<%=car.getId()%>"><img src="images/icon-delete.gif" alt="del"></a> </td>--%>
          <%--<td><a href="?prod_id=<%=objects[3]%>">X</a></td>--%>
          <%--<td><form action="/edit/" method="post">
            <button type="submit" value="<%=objects[3]%>" name="prod_id">X</button>
          </form></td>--%>
          <td><form method="post" action="/edit/deleteProduct/<%=objects[3]%>">
            <input type="hidden" name="_method" value="DELETE">
            <input type="button" value="X">
          </form></td>
        </tr>
    <%}%>
  </table>

  <p>Add transaction</p>

  <form action="edit/addTransaction/" method="post">
    <table>
      <th>Customer</th> <th>Shop</th> <th>Product</th> <th>Count</th> <th>Price $</th> <th></th>
      <tr>
        <td><input type="text" name="nameCustomersInput" list="nameCustomers"></td>
        <td><input type="text" name="nameShopsInput" list="nameShops"/></td>
        <td><input type="text" name="productsNameInput" list="productsName"/></td>
        <td><input type="text" name="count"></td>
        <td><input type="text" name="price"></td>
        <td>
          <button type="submit" value="add" onclick="add()">ADD</button>
        </td>
      </tr>
      <%for (int i = 0; i < 0; i++) {%>
        <tr>
          <td></td>
          <td></td>
          <td><input type="text" name=("productsNameInput"+i) list="productsName"/></td>
          <td><input type="text" name=("count"+i)></td>
          <td><input type="text" name=("price"+i)></td>
          <td></td>
        </tr>
      <%}%>
    </table>
  </form>

  <tr></tr>

  <form action="MyServlet" method="post">
    <button type="submit" value="value" name="add">ADD</button>
  </form>



  <datalist id="nameCustomers">
    <%for (String s : (ArrayList<String>)request.getAttribute("nameCustomers")){%>
      <option value="<%=s%>"></option>
    <%}%>
  </datalist>

  <datalist id="nameShops">
    <%for (String s : (ArrayList<String>)request.getAttribute("nameShops")){%>
    <option value="<%=s%>"></option>
    <%}%>
  </datalist>

  <datalist id="productsName">
    <%for (ProductName pName : ProductName.values()){%>
    <option value="<%=pName.name()%>"></option>
    <%}%>
  </datalist>

</body>
</html>