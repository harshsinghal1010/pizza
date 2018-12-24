

app.controller('orderController', ['$scope','$http','$state', function ($scope, $http,$state) {


    $scope.getAllOrders = function () {
        console.log("contextBasePath: " + contextBasePath);
        $http.get(contextBasePath + "rest/order/getAllOrders")
            .then(function (response) {
                $scope.orderList = response.data;
            });
    }
    $scope.getAllOrders();

    $scope.getPizzaTypeWiseOrders = function () {
        console.log("contextBasePath: " + contextBasePath);
        $http.get(contextBasePath + "rest/order/getOrdersCountPizzaTypeWise")
            .then(function (response) {
                $scope.pizzaWiseCount = response.data[0];

                // $scope.indianPizzaCount = $scope.pizzaWiseCount.INDIAN;
                $scope.indianPizzaCount = isNaN($scope.pizzaWiseCount.INDIAN)?0:$scope.pizzaWiseCount.INDIAN;
                $scope.italianPizzaCount = isNaN($scope.pizzaWiseCount.ITALIAN)?0:$scope.pizzaWiseCount.ITALIAN;
              
                $scope.totalCount = Number.parseInt($scope.indianPizzaCount)+Number.parseInt($scope.italianPizzaCount);

                

            });
    }
    $scope.getPizzaTypeWiseOrders();

    $scope.back = function(){
        $state.go('home');
    }

}]);