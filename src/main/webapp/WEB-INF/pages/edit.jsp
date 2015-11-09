<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
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
          <td><%=about.getNameShop()%></td>
          <td><%=about.getNameProduct()%></td>
          <td><%=about.getCount()%></td>
          <td><%=about.getPrice()%></td>
          <form method="post" action="<%=about.getProdId()%>">
            <input type="hidden" name="_method" value="DELETE">
            <td><input type="image" src="/resources/images/icon-delete.gif" alt="del"></td>
          </form>
        </tr>
    <%}%>
  </table>

  <p>Delete transaction</p>

  <form method="post" action="deleteTransactionProduct/"> <%--deleteTransactionProduct--%>
      <input type="hidden" name="_method" value="DELETE">
      <input type="text" name="main_id" id="main_id">
      <td><input type="submit" name="delete"></td>
  </form>

  <p>Add transaction</p>

  <form method="get" action="add">
      <input type="text" name="nameCustomer">
      <input type="text" name="nameShop">
      <input type="submit">
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