(function(){
    var app = angular.module('TipoAtencionMedica',[]);

    app.factory('TipoAtencionMedicaService',['$resource',function($resource){

        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/tipoAtencion/:idTipoAtencion',
            //'https://obscure-atoll-1131.herokuapp.com/:idTipoAtencion',
            {
                idTipoAtencion:'@idTipoAtencion'
            },
            {
              actualizar: 
              {
                method:'PUT', 
                params:{
                    idTipoAtencion:'@idTipoAtencion'
                }

            }
        });

        return factory;

    }]);
})();