scotchApp.factory('serverHttp', function ($http, $q, $log, $rootScope) {

    var session = undefined;
    var API_URL = 'http://127.0.0.1:8080/';

    var req = function (url, jsonObj, method, urlencoded=false) {
        $rootScope.showLoader = true;
        var deferred = $q.defer();
        headers_data = {
           method: method,
           withCredentials: true,
           url: API_URL + url,
           data: JSON.stringify(jsonObj)
        }
        if(urlencoded){
            headers_data.headers = {"Content-Type": "application/x-www-form-urlencoded"};
            headers_data.data = jsonObj
        }
        $http(headers_data).then(function (respond) {
            $rootScope.showLoader = false;
            deferred.resolve(respond.data);
        }, function (error, code) {
            $rootScope.showLoader = false;
            console.log("Error: { URL:",url,"}, {Code: ",code,"}, {Error: ",error,"}, {Data: ",error.data,"}");
//            $log.error(error, code);
            deferred.reject(error.data);
        });

        return deferred.promise;
    }

    return {
        GET: function (url, jsonObj = {}) {
            return req(url,jsonObj,"GET");
        },
        POST: function (url, jsonObj = {}) {
            return req(url,jsonObj,"POST");
        },
        POST_LOGIN: function (url, jsonObj = {}) {
            return req(url,jsonObj,"POST",true);
        }

    };
})