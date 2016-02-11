(function(){
    var app = angular.module('OdontologoEspecialidad',['ngResource']);

    app.factory('OdontologoEspecialidadService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/odontologoEspecialidad/:idOdontologoEspecialidad',
            {
                idOdontologoEspecialidad:'@idOdontologoEspecialidad'
            },
            {
              actualizar:
              {
                method:'PUT',
                params:{
                    idOdontologoEspecialidad:'@idOdontologoEspecialidad'
                }

            }
        });

        return factory;

    }]);
})();