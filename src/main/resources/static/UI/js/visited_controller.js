scotchApp.controller('visitedController', function ($scope, $rootScope, $http, UserService, serverHttp) {

    UserService.getUser()

    serverHttp.GET("takenflight/getAllTakenFlights",{}).then(function(data){
        $scope.allTakenFlights = data;
        console.log("getAllTakenFlights",data);
    })

//    // get recommendations
    serverHttp.GET("recommender/recommend",{}).then(function(data){
        console.log("recommend","successCallback",data);
        $scope.mostBeneficialAirline = data.mostBeneficialAirline;
    })

    $scope.from = "TLV"
    $scope.to = "LAX"
    $scope.flightNumber = "EZY643"
    $scope.ticketNumber = "ABC123"
    $scope.nameOnTicket = "Daniel"
    $scope.dateOfFlight = "01012010"
    $scope.seatType = "A"
    var jsonObj =
        {
            "from": $scope.from,
            "to": $scope.to,
            "flightNumber": $scope.flightNumber,
            "ticketNumber": $scope.ticketNumber,
            "nameOnTicket": $scope.nameOnTicket,
            "dateOfFlight": $scope.dateOfFlight,
            "seatType": $scope.seatType
        };
    console.log("stringified JSON: " ,JSON.stringify(jsonObj))

    // add destination func :
    $scope.addDestination = function () {
        $scope.message = "";
        var jsonObj =
        {
            "from": $scope.from,
            "to": $scope.to,
            "flightNumber": $scope.flightNumber,
            "ticketNumber": $scope.ticketNumber,
            "nameOnTicket": $scope.nameOnTicket,
            "dateOfFlight": $scope.dateOfFlight,
            "seatType": $scope.seatType
        };

        serverHttp.POST("takenflight/addTakenFlight/", jsonObj ).then(function(data){
            $scope.showLoader = false;
            $scope.allTakenFlights.push(jsonObj)
            console.log("addTakenFlight",data);
        });
    };

});