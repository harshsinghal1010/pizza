var app = angular.module('pizzeria', ['ui.router','angular.filter','ngMaterial']).
    config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/home');
        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: 'UI/view/home.screen.view.html',
                params: {
                    obj: null
                },
                controller: 'homeController'

            }).state('buy', {
                url: '/buy',
                templateUrl: 'UI/view/buy.screen.view.html',
                params: {
                    obj: null
                },
                controller: 'buyController'

            }).state('order', {
                url: '/order',
                templateUrl: 'UI/view/order.screen.view.html',
                params: {
                    obj: null
                },
                controller: 'orderController'

            });
    }]);
