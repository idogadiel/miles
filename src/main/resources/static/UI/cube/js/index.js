var scotchApp = angular.module('scotchApp', ['ngRoute']);

scotchApp.run(function ($rootScope) {});

scotchApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'home.html',
            controller: 'mainController'
        })

        .when('/forgotpassword', {
            templateUrl: 'forgotpassword.html',
            controller: 'userController'
        })

        .when('/desired', {
            templateUrl: 'desired.html',
            controller: 'desiredController'
        })

         .when('/about', {
                    templateUrl: 'about.html',
                   // controller: 'desiredController'
                })

         .when('/visited', {
             templateUrl: 'visited.html',
             controller: 'visitedController'
         })

        .when('/signin', {
            templateUrl: 'signin.html',
            controller: 'userController'
        })

        .when('/signout', {
            templateUrl: 'signout.html',
            controller: 'userController'
        })

        .when('/signup', {
            templateUrl: 'signup.html',
            controller: 'userController'
        });
});

scotchApp.controller('mainController', function ($scope, $rootScope, $http, UserService) {
    $rootScope.showLoader = false;

    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/user/isloggedin'
    }).then(function successCallback(response) {
        console.log("issignedin",response.data);
        var answer = angular.fromJson(response.data);
        if (answer.result){
            var user = {}
            user.signedin = true;
            user.username = answer.reason;

            UserService.setUser(user);
        }
    }, function errorCallback(response) {
        UserService.clearUser();
    });
});

function isValidEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
}
