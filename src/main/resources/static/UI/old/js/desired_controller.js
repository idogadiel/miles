scotchApp.controller('desiredController', function ($scope, $rootScope, $http, UserService) {

    UserService.getUser()

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