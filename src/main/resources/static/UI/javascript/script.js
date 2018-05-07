var scotchApp = angular.module('scotchApp', ['ngRoute']);

scotchApp.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'home.html',
            controller: 'mainController'
        })

        .when('/forgotpassword', {
            templateUrl: 'forgotpassword.html',
            controller: 'forgotpasswordController'
        })

        .when('/desired', {
            templateUrl: 'desired.html',
            controller: 'desiredController'
        })

         .when('/visited', {
             templateUrl: 'visited.html',
             controller: 'visitedController'
         })

        .when('/signin', {
            templateUrl: 'signin.html',
            controller: 'signinController'
        })

        .when('/signout', {
            templateUrl: 'signout.html',
            controller: 'signoutController'
        })

        .when('/signup', {
            templateUrl: 'signup.html',
            controller: 'signupController'
        });
});

scotchApp.run(function ($rootScope) {});

scotchApp.controller('mainController', function ($scope, $rootScope, $http) {
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/user/isloggedin'
    }).then(function successCallback(response) {
        var answer = angular.fromJson(response.data);
        $scope.$parent.loggedin = answer.result;
    }, function errorCallback(response) {
        $scope.loggedin = false;
    });
});

scotchApp.controller('signoutController', function ($scope, $rootScope, $http) {
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/user/logout'
    }).then(function successCallback(response) {
        $scope.message = "Logged out Successfully";
        $scope.$parent.loggedin = false;
    }, function errorCallback(response) {
        $scope.message = "Error";
    });
});

scotchApp.controller('forgotpasswordController', function ($scope, $rootScope, $http) {
    $scope.changePassword = function () {
        var body = {
            "code": window.location.href.split('key=')[1],
            "password": $scope.password1
        };
        $http({
            method: 'POST',
            withCredentials: true,
            data: body,
            url: 'http://127.0.0.1:8080/user/changePassword'
        }).then(function successCallback(response) {
            if (response.data.result) {
                $scope.message = "Password changed Successfully";
            }
            else {
                $scope.message = "Error changing password.";
            }
        }, function errorCallback(response) {
            $scope.message = "Error changing password";
        });
    }
});

scotchApp.controller('signinController', function ($scope, $http) {
    $scope.showForgotPassowrdLink = false;
    $scope.login = function () {
        $scope.showLoader = true;
        $scope.message = "";
        var jsonObj = "username=" + $scope.username + "&password=" + $scope.password;
        $http({
            url: "http://127.0.0.1:8080/user/login",
            withCredentials: true,
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            data: jsonObj
        }).success(function (data, status, headers, config) {
            finishedVerify(true);

        }).error(function (data, status, headers, config) {
            finishedVerify(false);
        });
    };
    function finishedVerify(connected) {
        $scope.showLoader = false;
        if (connected) {
            $scope.message = "You are now signed in.";
            $scope.$parent.loggedin = true;
            $scope.showForgotPassowrdLink = false;
        }
        else {
            $scope.message = "Wrong username and/or password";
            $scope.showForgotPassowrdLink = true;
        }
    }

    $scope.sendForgotPasswordLink = function () {
        if (!isValidEmail($scope.username)) {
            $scope.message = "Please add valid email address";
            return;
        }
        $scope.showLoader = true;
        $http({
            method: 'GET',
            withCredentials: true,
            url: 'http://127.0.0.1:8080/user/forgotPassword/' + $scope.username
        }).then(function successCallback(response) {
            $scope.showForgotPassowrdLink = true;
            $scope.message = "Check your email for instructions";
            $scope.showLoader = false;
            $scope.$parent.loggedin = false;
        }, function errorCallback(response) {
            $scope.message = "Error";
            $scope.showLoader = true;

        });
    }

});

scotchApp.controller('desiredController', function ($scope, $rootScope, $http) {
    // get all destinations
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/goal/getAllGoals'
    }).then(function successCallback(response) {
            $scope.allDesiredDestinations = response.data;
    }, function errorCallback(response) {
            $scope.allDesiredDestinations = "error";
    });

    // add destination func :
    $scope.addDestination = function () {
            $scope.message = "";
            var jsonObj =
            {
                "to": $scope.to,
                "from": $scope.from,
                "seatType": $scope.seatType
            };
            $http({
                url: "http://127.0.0.1:8080/goal/addGoal/",
                method: "POST",
                withCredentials: true,
                data: jsonObj
            }).success(function (data, status, headers, config) {
                $scope.showLoader = false;
            }).error(function (data, status, headers, config) {
               // alert("c");
            });
        };
});

scotchApp.controller('visitedController', function ($scope, $rootScope, $http) {
    // get all destinations
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/takenflight/getAllTakenFlights'
    }).then(function successCallback(response) {
            $scope.allTakenFlights = response.data;
    }, function errorCallback(response) {
            $scope.allTakenFlights = "error";
    });

    // add destination func :
    $scope.addDestination = function () {
            $scope.message = "";
            var jsonObj =
            {
                "flightNumber": $scope.flightNumber,
                "ticketNumber": $scope.ticketNumber,
                "nameOnTicket": $scope.nameOnTicket,
                "dateOfFlight": $scope.dateOfFlight

            };
            $http({
                url: "http://127.0.0.1:8080/takenflight/addTakenFlight/",
                method: "POST",
                withCredentials: true,
                data: jsonObj
            }).success(function (data, status, headers, config) {
                $scope.showLoader = false;
            }).error(function (data, status, headers, config) {
               // alert("c");
            });

        };
});




scotchApp.controller('signupController', function ($scope, $http) {

    $scope.showLoader = false;
    $scope.login = function () {
         if (!isValidEmail($scope.username)) {
                    $scope.message = "Please add valid email address";
                    return;
          }
        $scope.message = "";
        $scope.showLoader = true;
        var jsonObj =
        {
            "email": $scope.username,
            "password": $scope.password,
            "birthdate": "1.1.2001"
        };
        $http({
            url: "http://127.0.0.1:8080/user/signup/",
            method: "POST",
            data: jsonObj
        }).success(function (data, status, headers, config) {
            $scope.showLoader = false;
            if (data.result) {
                $scope.message = "Signed up successfully";
                $scope.$parent.headerUsername = data.username;
            }
            else {
                $scope.message = data.result;
            }
        }).error(function (data, status, headers, config) {
            $scope.showLoader = false;
            $scope.message = "Error. something went wrong.";
        });

    };
});

function setHeaderUsername(response) {
    var answer = angular.fromJson(response.data);
    return answer.username;
}

function isValidEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
}
