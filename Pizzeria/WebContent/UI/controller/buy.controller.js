

app.controller('buyController', ['$scope', '$state', '$http', function ($scope, $state, $http) {

    // alert("$state.params.obj", $state.params.obj);

    var pizzaType = $state.params.obj;
    $scope.isPriceCalculated=true;
    
    $scope.quantity = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20];

    $scope.getAllPizzas = function () {
        console.log("contextBasePath: " + contextBasePath);
        $http.get(contextBasePath + "rest/pizza/getAllPizza")
            .then(function (response) {
                $scope.pizzaList = response.data;
            });
    }
    $scope.getAllPizzas();

    $scope.getCrustByPizzaType = function (pizzaType) {
        if(pizzaType=='Indian Pizza'){
            pizzaType = 'INDIAN';
        }else if(pizzaType=='Italian Pizza'){
            pizzaType = 'ITALIAN';
        }else if(pizzaType=='French Pizza'){
            pizzaType = 'FRENCH';
        }else if(pizzaType=='Russian Pizza'){
            pizzaType = 'RUSSIAN';
        }else if(pizzaType=='Brazilian Pizza'){
            pizzaType = 'BRAZILIAN';
        }
        $http.get(contextBasePath + "rest/crust/getCrustByPizzaType/" + pizzaType)
            .then(function (response) {
                $scope.crustList = response.data;
            });
    }

    
    $scope.getToppingsByPizzaType = function (pizzaType) {
        $scope.pizza_Type = pizzaType;
        if(pizzaType=='Indian Pizza'){
            pizzaType = 'INDIAN';
        }else if(pizzaType=='Italian Pizza'){
            pizzaType = 'ITALIAN';
        }else if(pizzaType=='French Pizza'){
            pizzaType = 'FRENCH';
        }else if(pizzaType=='Russian Pizza'){
            pizzaType = 'RUSSIAN';
        }else if(pizzaType=='Brazilian Pizza'){
            pizzaType = 'BRAZILIAN';
        }
        $http.get(contextBasePath + "rest/topping/getToppingsByPizzaType/" + pizzaType)
            .then(function (response) {
                $scope.toppings = response.data;
            });
    }

    if (pizzaType != 'undefined' || pizzaType != null) {
        console.log("if pizzaType", pizzaType);
        $scope.getToppingsByPizzaType(pizzaType);
        $scope.getCrustByPizzaType(pizzaType);
    } else {
        console.log("else pizzaType", pizzaType);
    }

    $scope.calculatePrice = function(){
        var pCrust = $scope.pcrust;
        $scope.topping =  JSON.parse($scope.pizza_topping);
        $scope.pizzaQuant = $scope.pizzaQuantity;
        $scope.pizzaPrice = $scope.topping.price * $scope.pizzaQuant;
        $scope.isPriceCalculated=false;

    }

    $scope.buyPizza = function(){

        if($scope.pizza_Type=='Indian Pizza'){
            $scope.pizza_Type = 'INDIAN';
        }else if($scope.pizza_Type=='Italian Pizza'){
            $scope.pizza_Type = 'ITALIAN';
        }else if($scope.pizza_Type=='French Pizza'){
            $scope.pizza_Type = 'FRENCH';
        }else if($scope.pizza_Type=='Russian Pizza'){
            $scope.pizza_Type = 'RUSSIAN';
        }else if($scope.pizza_Type=='Brazilian Pizza'){
            $scope.pizza_Type = 'BRAZILIAN';
        }

        $http.get(contextBasePath + "rest/pizza/getOrderedPizza/" + $scope.pizza_Type+"/"+$scope.pcrust+"/"+$scope.topping.name)
            .then(function (response) {
                $scope.pizza = response.data[0];

                $scope.order = {};
                $scope.order.name="ORDER-"+$scope.topping.name;
                $scope.order.pizza={"id":$scope.pizza.id};
                
                $http.post(contextBasePath + "rest/order/create",$scope.order)
                .then(function (response) {
                    $scope.createdOrder = response.data;
                    $state.go('home');
                });
            });
        

    }

   
    

}]);
