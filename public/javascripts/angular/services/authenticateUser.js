(function(){
    var app = angular.module('AuthenticateUser',['ngResource']);

    app.factory('AuthenticateUserService',['$resource',function($resource){

        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/authenticateUser',
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