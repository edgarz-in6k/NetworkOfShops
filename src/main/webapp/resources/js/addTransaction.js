angular.module("myApp", [])
    .controller('controller', function ($scope, $http) {

        $scope.addProduct = function(){
            $http.post().then(function(response){

            });
        };

    });