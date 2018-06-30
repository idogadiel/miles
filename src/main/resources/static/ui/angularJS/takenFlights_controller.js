scotchApp.controller('takenFlightsController', function ($scope, $rootScope, $http, UserService, serverHttp) {

    UserService.getUser()

    serverHttp.GET("takenflight/getAllTakenFlights",{}).then(function(data){
        $rootScope.allTakenFlights = data;
        console.log("getAllTakenFlights",data);
    })


    // add destination func :
    $scope.addDestination = function () {
        $scope.message = "";
        var jsonObj =
        {
            "from": $scope.from,
            "to": $scope.to,
            "flightNumber": $scope.flightNumber,
            "cost": $scope.cost,
            "dateOfFlight": $scope.dateOfFlight,
            "seatType": $scope.seatType
        };

        serverHttp.POST("takenflight/addTakenFlight/", jsonObj ).then(function(data){
            $scope.showLoader = false;
            $rootScope.allTakenFlights.push(jsonObj)

            $scope.from = "";
            $scope.to = "";
            $scope.flightNumber = "";
            $scope.cost = "";
            $scope.dateOfFlight = "";
            $scope.seatType = "";

            console.log("addTakenFlight",data);
        });
    };

});