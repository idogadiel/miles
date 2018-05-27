scotchApp.controller('userController', function ($rootScope, $scope, $http, $location, UserService) {
    console.log($location);
    console.log($location.url());
    console.log($rootScope.user);
    console.log($rootScope.showLoader);
    console.log(["/signin","/signup","/forgotpassword"].indexOf($location.url()),$location.url())

    // pages on which we want to skip the user check
    if (["/signin","/signup","/forgotpassword"].indexOf($location.path()) < 0){
        UserService.getUser();
    }

    $scope.login_tries = 0

    $rootScope.showLoader = false;
    $scope.message = "";

    $scope.signup = function () {
         if (!isValidEmail($scope.username)) {
            $scope.message = "Please add valid email address";
            return;
        }

        $scope.message = "";
        $rootScope.showLoader = true;
        console.log($rootScope.showLoader);
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
            console.log(data)
            if (data.result) {
                $rootScope.user = {}
                $rootScope.user.signedin = true;
                $rootScope.user.username = data.username;
            }
            else {
                $scope.message = data.reason;
            }
        }).error(function (data, status, headers, config) {
            $scope.showLoader = false;
        });

    };

    $scope.signIn = function () {
        $scope.showLoader = true;
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
//            console.log(data)

            $scope.showLoader = false;

            $rootScope.user = {}
            $rootScope.user.signedin = true;
            $rootScope.user.username = $scope.username;

            UserService.setUser($rootScope.user)
        }).error(function (data, status, headers, config) {
            $scope.showLoader = false;
            $scope.login_tries += 1
            $rootScope.user.signedin = false;
        });
    };

    $scope.signOut = function () {
        UserService.clearUser()
        $http({
            method: 'GET',
            withCredentials: true,
            url: 'http://127.0.0.1:8080/user/logout'
        }).then(function successCallback(response) {
//            alert("SuccessFully Logged out")
            $scope.message = "Logged out Successfully";
        }, function errorCallback(response) {
            $scope.message = "Error";
        });
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
            $rootScope.user.signedin = false;
        }, function errorCallback(response) {
            $scope.message = "Error";
            $scope.showLoader = true;

        });
    }

    $scope.changePassword = function () {
        $rootScope.showLoader = true;
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
            $rootScope.showLoader = false;
           if (response.data.result) {
               $scope.message = "Password changed Successfully";
           }
           else {
               $scope.message = "Error changing password.";
           }
       }, function errorCallback(response) {
            $rootScope.showLoader = false;
           $scope.message = "Error changing password";
       });
   }
});