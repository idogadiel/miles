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
         //   controller: 'userController'
        })

        .when('/goal', {
            templateUrl: 'goal.html',
            controller: 'goalController'
        })

         .when('/about', {
             templateUrl: 'about.html',
             // controller: 'desiredController'
          })

           .when('/contact', {
              templateUrl: 'contact.html',
              // controller: 'desiredController'
         })

         .when('/takenFlights', {
             templateUrl: 'takenFlights.html',
             controller: 'takenFlightsController'
         })

        .when('/signin', {
            templateUrl: 'signin.html',
            controller: 'userController'
        })

        .when('/signout', {
            templateUrl: 'signout.html',
           controller: 'userController'
        })

        .when('/register', {
            templateUrl: 'register.html',
            controller: 'userController'
        });
});

scotchApp.controller('mainController', function ($scope, $rootScope, $http, UserService, serverHttp) {
    $rootScope.showLoader = false;

   serverHttp.GET("user/isloggedin",{}).then(function(data){
                    var answer = angular.fromJson(data);
                            if (answer.result){
                                var user = {}
                                user.signedin = true;
                                user.username = answer.reason;
                                UserService.setUser(user);
                            }
                })
});

function isValidEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
}
