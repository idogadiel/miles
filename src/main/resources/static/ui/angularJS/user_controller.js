scotchApp.controller('userController', function ($rootScope, $scope, $http, $location, UserService, serverHttp) {

    // pages on which we want to skip the user check
    if (["/signin","/register","/forgotpassword"].indexOf($location.path()) < 0){
        UserService.getUser();
    }

    $scope.EMAIL_REGEXP = EMAIL_REGEXP
    $scope.STRONG_PASS_REGEXP = STRONG_PASS_REGEXP

    $scope.signup = function () {
        if ($scope.$signupForm.$invalid){
            console.log($scope.$signupForm.$error)
            return
        }

        var jsonObj =
        {
            "username": $scope.username,
            "email": $scope.email,
            "password": $scope.password,
            "birthdate": $scope.bday
//            "birthdate": "1.1.2001"
        };
        serverHttp.POST("user/signup/", jsonObj ).then(function(data){
            console.log(data)
            if (data.result) {
                $rootScope.user = {}
                $rootScope.user.signedin = true;
                $rootScope.user.username = data.username;
                UserService.setUser($rootScope.user)
                $location.path("/goal");
            }
            else{
                $scope.signupErrorMessage = data.reason
            }
        });
    };

    $scope.login_tries = 0
    $scope.signIn = function () {
//        $scope.signinForm.$setUntouched();
//        $scope.signinForm.$setPristine();
        if ($scope.signinForm.$invalid){
            console.log($scope.signinForm.$error)
            return
        }
        var jsonObj = "username=" + $scope.email + "&password=" + $scope.password;
        serverHttp.POST_LOGIN("user/login", jsonObj ).then(function(data){
            var user = {}
            user.signedin = true;
            user.username = $scope.username;
            UserService.setUser(user)
            $location.path("/goal");
        }, function(error){
            $scope.signinErrorMessage = error.reason
            $scope.login_tries += 1
        });
    };

    $scope.signOut = function () {
        UserService.clearUser()
        serverHttp.GET("user/logout",{}).then(function(data){
            $scope.message = "Logged out Successfully";
        });
     }

    $scope.sendForgotPasswordLink = function () {
        if (!isValidEmail($scope.username)) {
            $scope.message = "Please add valid email address";
            return;
        }
        serverHttp.GET("user/forgotPassword",{}).then(function(data){
            $scope.showForgotPassowrdLink = true;
            $scope.message = "Check your email for instructions";
            $rootScope.user.signedin = false;
        });
    }

    $scope.changePassword = function () {
        var jsonObj = {
           "code": window.location.href.split('key=')[1],
           "password": $scope.password1
        };
        serverHttp.POST("user/changePassword/", jsonObj ).then(function(data){
            if (data.result) {
                $scope.message = "Password changed Successfully";
            }
            else {
                $scope.message = "Error changing password.";
            }
       });
    }
});