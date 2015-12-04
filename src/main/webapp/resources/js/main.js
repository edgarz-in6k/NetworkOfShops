angular.module('myApp', [])
    .controller('controller', function ($scope, $http) {

        $http.get('/angular').then(function (response) {
            $scope.arr = response.data;
        });

    });