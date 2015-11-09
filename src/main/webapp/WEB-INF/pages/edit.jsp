<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.core.ProductName" %>
<%@ page import="com.output.AboutTransaction" %>
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
    <title>Edit</title>
</head>
<body>

<div class="center-block"><h3 class="bg-info">Editing database</h3></div>

<table class="table">
    <th class="btn-primary">id</th>
    <th class="btn-primary">Customers</th>
    <th class="btn-primary">Shops</th>
    <th class="btn-primary">Name P</th>
    <th class="btn-primary">Count P</th>
    <th class="btn-primary">Price P</th>
    <th class="btn-primary">Delete</th>
    <%for (AboutTransaction about : (List<AboutTransaction>)request.getAttribute("aboutTransactions")) {%>
    <tr class="bg-info">
        <td class="active"><%=about.getId()%></td>
        <td class="active"><%=about.getNameCustomer()%></td>
        <td class="active"><%=about.getNameShop()%></td>
        <td class="active"><%=about.getNameProduct()%></td>
        <td class="active"><%=about.getCount()%></td>
        <td class="active"><%=about.getPrice()%></td>
        <form method="post" action="<%=about.getProdId()%>">
            <input type="hidden" name="_method" value="DELETE">
            <td><input type="image" src="/resources/images/icon-delete.gif" alt="del"></td>
        </form>
    </tr>
    <%}%>
</table>

  <form method="post" action="deleteTransactionProduct/"  class="form-inline"> <%--deleteTransactionProduct--%>
      <div class="form-group">
          <input type="hidden" name="_method" value="DELETE">
          <label for="main_id" class="col-sm-12 control-label">Delete transaction</label>
          <input type="text" name="main_id" id="main_id" class="form-control">
          <input type="submit" name="delete" id="delete" value="Delete" class="btn btn-btn-primary">
      </div>
  </form>

  <p>Add transaction</p>

  <form method="get" action="add" class="form-inline">
      <div class="form-group">
          <label for="nameCustomer">Name customer</label>
          <input type="text" id="nameCustomer" name="nameCustomer" class="form-control">
      </div>
      <div class="form-group">
          <label for="nameShop">Name shop</label>
        <input type="text" id="nameShop" name="nameShop" class="form-control">
      </div>
      <input type="submit" value="to Add" class="btn btn-primary">
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

  <a href="/">back to main page</a>

</body>
</html>