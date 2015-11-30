var myApp = angular.module('myApp', []);

myApp.controller('firstCtrl', function ($scope, $http) {
    $scope.arr = [1,2];
    $http.get('/').then(function (response) {
        $scope.arr = response.data;
    });
});