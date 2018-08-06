scotchApp.factory("UserService", function($rootScope, $location, serverHttp) {

    var clearUser = function(){
        $rootScope.user = {};
        //  Checking for localStorage Support
        if (window.localStorage) {
            localStorage.removeItem("user");
            console.log("clearUser",$rootScope.user);
//                localStorage.clear();
        };
        $location.path("");
    };

    var setLocalUser = function(user) {
        //  Checking for localStorage Support
        if (window.localStorage) {
            localStorage.setItem("user", JSON.stringify(user));
            console.log("setUser",user)
            $rootScope.user = user;
            return 1;
        };
        return 0
    };

    var isLoggedIn = function() {
        serverHttp.GET("user/isloggedin",{}).then(function(data){
            var answer = angular.fromJson(data);
            if (answer.result){
                var user = {}
                user.signedin = true;
                user.username = answer.reason;
                setLocalUser(user);
            };
        });
    };

    var checked_is_loggedIn = false;
    return {
        getUser: function() {
            if (!checked_is_loggedIn){
                checked_is_loggedIn = true;
                isLoggedIn();
                if ($rootScope.user){
                    console.log("checked_is_loggedIn",$rootScope.user)
                }
            };
            //  Checking for localStorage Support
            if (window.localStorage) {
                let user = JSON.parse(localStorage.getItem("user"));
                console.log("getUser",user)
                if (user){
                    $rootScope.user = user;
                }
                else {
                    clearUser();
                }
                return user;
            }
            return 0;
        },
        setUser: setLocalUser,
        clearUser: clearUser
    };
});