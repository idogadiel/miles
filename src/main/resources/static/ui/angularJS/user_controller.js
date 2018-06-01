scotchApp.controller('userController', function ($rootScope, $scope, $http, $location, UserService, serverHttp) {
    console.log($location);
    console.log($location.url());
    console.log($rootScope.user);
    console.log($rootScope.showLoader);

    // pages on which we want to skip the user check
    if (["/signin","/register","/forgotpassword"].indexOf($location.path()) < 0){
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
         serverHttp.POST("user/signup/", jsonObj ).then(function(data){
                    $scope.showLoader = false;
                                console.log(data)
                                if (data) {
                                    $rootScope.user = {}
                                    $rootScope.user.signedin = true;
                                    $rootScope.user.username = data.username;
                                    UserService.setUser($rootScope.user)
                                }
                });
    };

    $scope.signIn = function () {
        $scope.showLoader = true;
        var jsonObj = "username=" + $scope.username + "&password=" + $scope.password;

             serverHttp.POST_LOGIN("user/login", jsonObj ).then(function(data){
                                          $scope.showLoader = false;
                                           $rootScope.user = {}
                                           $rootScope.user.signedin = true;
                                           $rootScope.user.username = $scope.username;
                                            UserService.setUser($rootScope.user)
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
                     $scope.showLoader = false;
                     $rootScope.user.signedin = false;
                    });
    }

    $scope.changePassword = function () {
        $rootScope.showLoader = true;
       var jsonObj = {
           "code": window.location.href.split('key=')[1],
           "password": $scope.password1
       };
          serverHttp.POST("user/changePassword/", jsonObj ).then(function(data){
                           $scope.showLoader = false;
                            $rootScope.showLoader = false;
                             if (data.result) {
                              $scope.message = "Password changed Successfully";
                            }
                             else {
                           $scope.message = "Error changing password.";
                          }
                       });
   }
});