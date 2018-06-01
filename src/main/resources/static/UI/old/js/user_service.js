scotchApp.factory("UserService", function($rootScope, $location) {

    var clearUser = function(){
        $rootScope.user = {};
        //  Checking for localStorage Support
        if (window.localStorage) {
            localStorage.removeItem("user");
            console.log("clearUser",$rootScope.user);
//                localStorage.clear();
        }
        $location.path("");
    }

    return {
        getUser: function() {
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
        setUser: function(user) {
            //  Checking for localStorage Support
            if (window.localStorage) {
                localStorage.setItem("user", JSON.stringify(user));
                console.log("setUser",user)
                $rootScope.user = user;
                return 1;
            }
            return 0
        },
        clearUser: clearUser
    };
});