(function(){
    var app = angular.module('AtencionMedica',['ngResource']);

    app.factory('AtencionMedicaService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/atencionMedica/:idAtencionMedica',
            {
                idAtencionMedica:'@idAtencionMedica'
            },
            {
            actualizar:
            {
                method:'PUT',
                params:{
                    idAtencionMedica:'@idAtencionMedica'
                }
            },
            buscarPorUsuario:
            {
                url: '//localhost:9000/atencionMedica/buscarPorUsuario/:idUsuario',
                metod: 'GET',
                isArray: true,
                param: {
                    idUsuario: '@idUsuario'
                }
            }
        });

        return factory;

    }]);
})();
