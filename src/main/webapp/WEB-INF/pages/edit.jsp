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

<link rel="stylesheet" href="../../resources/css/edit.css">

<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="../../resources/js/angular.min.js"></script>
<script src="../../resources/js/edit.js"></script>

<head>
    <title>Edit</title>
</head>
    <body ng-app="edit">

        <div class="center-block">
            <h3 class="bg-info">Editing database</h3>
        </div>

        <div class="row">
            <div class="col-md-6 col-ld-6">
                <table class="table">
                    <th class="btn-primary">id</th>
                    <th class="btn-primary">Customers</th>
                    <th class="btn-primary">Shops</th>
                    <th class="btn-primary">Name Product</th>
                    <th class="btn-primary">Count</th>
                    <th class="btn-primary">Price</th>
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
            </div>
        </div>

      <div class="row">
          <div class="col-md-4 col-ld-4">
              <form name="deleteForm" ng-controller="controller" method="post" action="/edit/deleteTransactionProduct/" class="form-inline">
                  <div class="form-group">
                      <input type="hidden" name="_method" value="DELETE">

                      <label for="main_id" class="col-sm-12 control-label">Delete transaction</label>
                      <input type="number" name="main_id" id="main_id" class="form-control" min="1" required="">

                      <div ng-show="deleteForm.$submitted || deleteForm.main_id.$touched">
                          <div ng-show="deleteForm.main_id.$error.required" class="error">Tell us id transaction.</div>
                      </div>

                      <input type="submit" name="delete" id="delete" value="Delete" class="btn btn-btn-primary" ng-disabled="addForm.$invalid">
                  </div>
              </form>
          </div>
      </div>

      <div class="row">
          <div class="col-md-4 col-ld-4">

              <p>Add transaction</p>

              <form name="addForm" ng-controller="controller" method="get" action="add" class="form-inline" novalidate>
                  <div class="form-group">
                      <label for="nameCustomer">Name customer</label>
                      <input type="text" id="nameCustomer" name="nameCustomer" ng-model="deal.customer" class="form-control" required="">
                      <div ng-show="addForm.$submitted || addForm.nameCustomer.$touched">
                          <div ng-show="addForm.nameCustomer.$error.required" class="error">Tell us customer name.</div>
                      </div>
                  </div>

                  <div class="form-group">
                      <label for="nameShop">Name shop</label>
                      <input type="text" id="nameShop" name="nameShop" ng-model="deal.shop" class="form-control" required="">
                      <div ng-show="addForm.$submitted || addForm.nameShop.$touched">
                          <div ng-show="addForm.nameShop.$error.required" class="error">Tell us shop name.</div>
                      </div>
                  </div>
                  </br>
                  <input type="submit" value="to Add" class="btn btn-primary" ng-disabled="addForm.$invalid">

              </form>
          </div>
      </div>

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