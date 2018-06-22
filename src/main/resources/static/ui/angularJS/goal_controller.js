scotchApp.controller('goalController', function ($log, $q, $timeout, $scope, $rootScope, $http, UserService, serverHttp) {

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
        var jsonObj ={
            "to": $scope.to.iata,
            "from": $scope.from.iata,
            "seatType": $scope.seatType
        };

        console.log(jsonObj)

        serverHttp.POST("goal/addGoal",jsonObj).then(function(data){
            $log.info(data)
        })
    };

//    var states = function() {
//      var allStates = 'Alabama, Alaska, Arizona, Arkansas, California, Colorado, Connecticut, Delaware,\
//              Florida, Georgia, Hawaii, Idaho, Illinois, Indiana, Iowa, Kansas, Kentucky, Louisiana,\
//              Maine, Maryland, Massachusetts, Michigan, Minnesota, Mississippi, Missouri, Montana,\
//              Nebraska, Nevada, New Hampshire, New Jersey, New Mexico, New York, North Carolina,\
//              North Dakota, Ohio, Oklahoma, Oregon, Pennsylvania, Rhode Island, South Carolina,\
//              South Dakota, Tennessee, Texas, Utah, Vermont, Virginia, Washington, West Virginia,\
//              Wisconsin, Wyoming';
//
//      return allStates.split(/, +/g).map( function (state) {
//        return {
//          value: state.toLowerCase(),
//          display: state
//        };
//      });
//    }

    $scope.querySearch = function (query) {
        var results = query ? airports_formated.filter( createFilterFor(query) ) : airports_formated,deferred;
        results = results.slice(0, 5);

        deferred = $q.defer();
        deferred.resolve(results);

        return deferred.promise;
    }

    function createFilterFor(query) {
      var lowercaseQuery = angular.lowercase(query);
      return function filterFn(airport) {
        return ((airport.name.indexOf(lowercaseQuery) === 0)
            || (airport.city.indexOf(lowercaseQuery) === 0)
            || (airport.country.indexOf(lowercaseQuery) === 0)) ;
      };
    }

    var airports_formated = airports.map(function(airport){
        return {
            iata    :airport.iata,
            name    :airport.name.toLowerCase(),
            city    :airport.city.toLowerCase(),
            country :airport.country.toLowerCase()
        };
    })

    $log.info(airports)
    $log.info(airports_formated)

    $scope.searchTextChange = function(text) {
      $log.info('Text changed to ' + text);
    }

    $scope.selectedItemChange = function(item) {
      $log.info('Item changed to ' + JSON.stringify(item));
    }
});