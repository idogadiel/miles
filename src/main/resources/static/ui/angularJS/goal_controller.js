scotchApp.controller('goalController', function ($log, $q, $timeout, $scope, $rootScope, $http, UserService, serverHttp) {

    UserService.getUser()

    // get all destinations
    if(!$rootScope.allDesiredDestinations){
        serverHttp.GET("goal/getAllGoals",{}).then(function(data){
            $rootScope.allDesiredDestinations = data;
            console.log("allDesiredDestinations",data);
        });
    }


    $scope.getRecommendation = function() {
        serverHttp.GET("recommender/recommend",{}).then(function(data){
            $scope.mostBeneficialAirline = data.mostBeneficialAirline;
            alert("We recommend you to redeem it with: "+$scope.mostBeneficialAirline );
        })
    };

    // add destination func :
    $scope.addDestination = function () {
        var jsonObj ={
            "to": $scope.to,
            "from": $scope.from,
            "seatType": $scope.seatType
        };

        console.log(jsonObj)

        serverHttp.POST("goal/addGoal",jsonObj).then(function(data){
            $log.info(data)

            $rootScope.allDesiredDestinations.push(jsonObj)

            $scope.from = "";
            $scope.to = "";
            $scope.seatType = "";
        })
    };
});