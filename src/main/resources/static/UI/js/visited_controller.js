scotchApp.controller('visitedController', function ($scope, $rootScope, $http, UserService) {

    UserService.getUser()

    // get all destinations
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/takenflight/getAllTakenFlights'
    }).then(function successCallback(response) {
        $scope.allTakenFlights = response.data;
        console.log($scope.allTakenFlights)
    }, function errorCallback(response) {
        console.log(response);
        $scope.allTakenFlights = "error";
    });

//    // get recommendations
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/recommender/recommend'
    }).then(function successCallback(response) {
        console.log("recommend","successCallback",response);
        $scope.mostBeneficialAirline = response.data.mostBeneficialAirline;
    }, function errorCallback(response) {
        console.log("recommend","errorCallback",response);
    });

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
            $http({
                url: "http://127.0.0.1:8080/takenflight/addTakenFlight/",
                method: "POST",
                withCredentials: true,
                data: jsonObj
            }).success(function (data, status, headers, config) {
                $scope.showLoader = false;
                $scope.allTakenFlights.push(jsonObj)
            }).error(function (data, status, headers, config) {
               // alert("c");
            });

        };
});