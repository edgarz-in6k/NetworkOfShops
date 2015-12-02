<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap.css' type='text/css' media='all'>
    <link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap.css.map' type='text/css' media='all'>
    <link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap.min.css' type='text/css' media='all'>
    <link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap-theme.css' type='text/css' media='all'>
    <link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap-theme.css.map' type='text/css' media='all'>
    <link rel='stylesheet' href='resources/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css' type='text/css' media='all'>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="resources/js/angular.min.js"></script>
    <script src="resources/js/main.js"></script>
</head>
<body>

<nav class="navbar navbar-brand">
    <div class="navbar-inverse">
        <div class="container">
            <p><a class="btn btn-primary btn-lg" role="button" href="customerFavoriteShop">Customers favorites of Shops</a></p>
            <p><a class="btn btn-primary btn-lg" role="button" href="edit/show">Edit database</a></p>
        </div>
    </div>
</nav>

<div ng-app="myApp">
    <div ng-controller="controller">
        <p>{{1+1}}</p>
        <p ng-repeat="i in arr track by $index">{{i}}</p>
    </div>
</div>

    <%--<div class="jumbotron">
        <div class="col-xs-6 col-sm-offset-6 col-md-6 col-lg-offset-12">
            <h1>Information of shops and shopper</h1>
            <p>bla-bla-bla</p>
            <p><a class="btn btn-primary btn-lg" role="button" href="customerFavoriteShop">Customers favorites of Shops</a></p>
            <p><a class="btn btn-primary btn-lg" role="button" href="edit/show">Edit database</a></p>
        </div>
    </div>--%>

    <%--<table class="table-responsive">
        <a href="http://10.1.1.98:8080/">Andrey</a>
        <a href="http://10.1.1.95:8080/">Alena</a>
    </table>--%>

</body>
</html>