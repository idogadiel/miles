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
         if (!isValidEmail($scope.email)) {
            $scope.signupForm.email.$setValidity("isValidEmail", false);
            return;
        }
        $scope.signupForm.email.$setValidity("isValidEmail", true);

        if (!checkStrongPassword($scope.password)){
            $scope.signupForm.password.$setValidity("checkStrongPassword", false);
            return
        }
        $scope.signupForm.password.$setValidity("checkStrongPassword", true);

        if ($scope.signupForm.password.$modelValue != $scope.signupForm.confPassword.$modelValue){
            $scope.signupForm.confPassword.$setValidity("mismatch", false);
            return
        }
        $scope.signupForm.confPassword.$setValidity("mismatch", true);

        console.log($scope.signupForm.$error)

        $rootScope.showLoader = true;
        console.log($rootScope.showLoader);
        var jsonObj =
        {
            "username": $scope.username,
            "email": $scope.email,
            "password": $scope.password,
            "birthdate": $scope.bday
//            "birthdate": "1.1.2001"
        };
         serverHttp.POST("user/signup/", jsonObj ).then(function(data){
            $rootScope.showLoader = false;
                console.log(data)
                if (data.username) {
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
                                           var user = {}
                                           user.signedin = true;
                                           user.username = $scope.username;
                                           UserService.setUser(user)
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