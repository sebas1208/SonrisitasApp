(function(){
    var app = angular.module('TipoAtencionMedica',['ngResource']);

    app.factory('TipoAtencionMedicaService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/tipoAtencion/:idTipoAtencion',
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