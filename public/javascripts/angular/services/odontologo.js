(function(){
    var app = angular.module('Odontologo',[]);

    app.factory('OdontologoService',['$resource',function($resource){

        var factory = $resource(
            //'http://localhost:9000/odontologo/:idOdontologo',
            'https://obscure-atoll-1131.herokuapp.com/odontologo/:idOdontologo',
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
