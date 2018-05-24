scotchApp.controller('visitedController', function ($scope, $rootScope, $http) {
    // get all destinations
    $http({
        method: 'GET',
        withCredentials: true,
        url: 'http://127.0.0.1:8080/takenflight/getAllTakenFlights'
    }).then(function successCallback(response) {
        $scope.allTakenFlights = response.data;
    }, function errorCallback(response) {
        console.log(response);
        $scope.allTakenFlights = "error";
    });

    // add destination func :
    $scope.addDestination = function () {
            $scope.message = "";
            var jsonObj =
            {
                "flightNumber": $scope.flightNumber,
                "ticketNumber": $scope.ticketNumber,
                "nameOnTicket": $scope.nameOnTicket,
                "dateOfFlight": $scope.dateOfFlight

            };
            $http({
                url: "http://127.0.0.1:8080/takenflight/addTakenFlight/",
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