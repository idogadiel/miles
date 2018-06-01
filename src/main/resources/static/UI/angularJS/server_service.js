scotchApp.factory('serverHttp', function ($http, $q, $log) {

    var session = undefined;
    var API_URL = 'http://127.0.0.1:8080/';

    var req = function (url, jsonObj, method) {
        var deferred = $q.defer();
        $http({
          method: method,
//          headers: {"Content-Type": "application/x-www-form-urlencoded"},
          withCredentials: true,
          url: API_URL + url,
          data: JSON.stringify(jsonObj)
        }).then(function (respond) {
          deferred.resolve(respond.data);
        }, function (error, code) {
          deferred.reject(error);
          $log.error(error, code);
        });

        return deferred.promise;
    }

    return {
        GET: function (url, jsonObj = {}) {
            return req(url,jsonObj,"GET");
        },
        POST: function (url, jsonObj = {}) {
            return req(url,jsonObj,"POST");
        }
    };
})