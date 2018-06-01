scotchApp.controller('goalController', function ($scope, $rootScope, $http, UserService, serverHttp) {

    UserService.getUser()

    // get all destinations
    serverHttp.GET("goal/getAllGoals",{}).then(function(data){
                    $scope.allDesiredDestinations = data;
                })


        $scope.getRecommendation = function() {
        serverHttp.GET("recommender/recommend",{}).then(function(data){
                $scope.mostBeneficialAirline = data.mostBeneficialAirline;
                alert("We recommend you to redeem it with: "+$scope.mostBeneficialAirline );
            })
        };

    // add destination func :
    $scope.addDestination = function () {
      var jsonObj =
                {
                    "to": $scope.to,
                    "from": $scope.from,
                    "seatType": $scope.seatType
                };

    serverHttp.POST("goal/addGoal",jsonObj).then(function(data){
                        })
        };
});