(function(){
    var app = angular.module('AtencionMedica',[]);

    app.factory('AtencionMedicaService',['$resource',function($resource){

        var factory = $resource(
            //'http://localhost:9000/atencionMedica/:idAtencionMedica',
            'https://obscure-atoll-1131.herokuapp.com/atencionMedica/:idAtencionMedica',
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
                //url: 'http://localhost:9000/atencionMedica/buscarPorUsuario/:idUsuario',
                url: 'https://obscure-atoll-1131.herokuapp.com/atencionMedica/buscarPorUsuario/:idUsuario',
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
