(function(){
    var app = angular.module('AuthenticateUser',['ngResource']);

    app.factory('AuthenticateUserService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/authenticateUser',
            {},
            {
                authenticateUser:
                {
                    method:'POST',
                }
            });

        return factory;
    }]);
})();