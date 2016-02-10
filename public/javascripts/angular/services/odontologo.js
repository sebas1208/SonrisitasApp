(function(){
    var app = angular.module('Odontologo',[]);

    app.factory('OdontologoService',['$resource',function($resource){

        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/odontologo/:idOdontologo',
            //'https://obscure-atoll-1131.herokuapp.com/odontologo/:idOdontologo',
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
