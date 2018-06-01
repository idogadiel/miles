scotchApp.controller('goalController', function ($scope, $rootScope, $http, UserService, serverHttp) {

    UserService.getUser()

    // get all destinations
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://18.188.243.239:8080/goal/getAllGoals'
    }).then(function successCallback(response) {
            $scope.allDesiredDestinations = response.data;
    }, function errorCallback(response) {
            $scope.allDesiredDestinations = "error";
    });

        $scope.getRecommendation = function() {
        serverHttp.GET("recommender/recommend",{}).then(function(data){
                $scope.mostBeneficialAirline = data.mostBeneficialAirline;
                alert("We recommend you to redeem it with: "+$scope.mostBeneficialAirline );
            })
        };

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
                url: "http://18.188.243.239:8080/goal/addGoal/",
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