<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.core.ProductName" %>
<%@ page import="com.output.AboutTransaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

  <table border="1">
    <th>id</th> <th>Customers</th> <th>Shops</th> <th>Name P</th> <th>Count P</th> <th>Price P</th> <th>Delete</th>
    <%for (AboutTransaction about : (List<AboutTransaction>)request.getAttribute("aboutTransactions")) {%>
        <tr>
          <td><%=about.getId()%></td>
          <td><%=about.getNameCustomer()%></td>
          <td><%=about.getNameProduct()%></td>
          <td><%=about.getNameProduct()%></td>
          <td><%=about.getCount()%></td>
          <td><%=about.getPrice()%></td>
          <%--<td><a href="?carId=<%=car.getId()%>"><img src="images/icon-delete.gif" alt="del"></a> </td>--%>
          <%--<td><a href="?prod_id=<%=objects[3]%>">X</a></td>--%>
          <%--<td><form action="/edit/" method="post">
            <button type="submit" value="<%=objects[3]%>" name="prod_id">X</button>
          </form></td>--%>
          <form method="post" action="<%=about.getProdId()%>">
            <input type="hidden" name="_method" value="DELETE">
            <td><input type="image" src="/resources/images/icon-delete.gif" alt="del"></td>
          </form>
        </tr>
    <%}%>
  </table>

  <p>Add transaction</p>

 <%-- <form method="post" action="edit/addTransaction/">&lt;%&ndash;method="post" action="edit/addTransaction/"&ndash;%&gt;
    <table>
      <th>Customer</th> <th>Shop</th> <th>Product</th> <th>Count</th> <th>Price $</th> <th></th>
      <tr>
        <td><input type="text" name="nameCustomersInput" list="nameCustomers"></td>
        <td><input type="text" name="nameShopsInput" list="nameShops"/></td>
        <td><input type="text" name="productsNameInput" list="productsName"/></td>
        <td><input type="text" name="count"></td>
        <td><input type="text" name="price"></td>
      </tr>
      <%for (int i = 0; i < 0; i++) {%>
        <tr>
          <td></td>
          <td></td>
          <td><input type="text" name=("productsNameInput"+i) list="productsName"/></td>
          <td><input type="text" name=("count"+i)></td>
          <td><input type="text" name=("price"+i)></td>
        </tr>
      <%}%>
    </table>

    &lt;%&ndash;<input type="hidden" name="_method" value="PUT">&ndash;%&gt;
    <input type="submit" value="ADD">

  </form>--%>

  <%--<tr></tr>--%>


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