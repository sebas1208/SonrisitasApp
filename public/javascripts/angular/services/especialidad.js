(function(){
    var app = angular.module('Especialidad',['ngResource']);
    app.factory('EspecialidadService',['$resource',function($resource){
        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/especialidad/:idEspecialidad',
            {
                idEspecialidad:'@idEspecialidad'
            },
            {
                actualizar:
                {
                    method:'PUT',
                    params:{
                        idEspecialidad:'@idEspecialidad'
                    }
                }
            });
        return factory;
    }]);
})();