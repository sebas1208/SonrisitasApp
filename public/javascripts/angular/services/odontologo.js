(function(){
    var app = angular.module('Odontologo',['ngResource']);

    app.factory('OdontologoService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/odontologo/:idOdontologo',
            {
                idOdontologo:'@idOdontologo'
            },
            {
              actualizar:
              {
                method:'PUT',
                params:{
                    idOdontologo:'@idOdontologo'
                }

            }
        });

        return factory;

    }]);
})();
