scotchApp.directive('airports', function () {

    var controller = ['$scope','$log','$q', function ($scope,$log,$q) {

        $scope.querySearch = function   (query) {
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
            $scope.ngModel = item.iata;
        }
    }];

    return {
          restrict: 'EA', //Default in 1.3+
          scope: {
                ngModel : '=',
                placeholder: '@'
          },
          controller: controller,
          templateUrl: 'airports_directive_template.html'
    };
});