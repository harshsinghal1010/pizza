

app.controller('homeController', ['$scope','$state', function ($scope,$state) {

    $scope.hello = "Welcome to the pizza parlour.What we can do for you";

    $scope.welcome = function(name){
        $scope.hello = "Hello "+name+" ,"+$scope.hello;
        if(name=='Orders'){
            $state.go('order',{obj:name});
        }else{
            $state.go('buy',{obj:name});
        }
        
    }
    $scope.homeScreenButtons = ['Indian Pizza','Italian Pizza','Orders'];
    
}]);