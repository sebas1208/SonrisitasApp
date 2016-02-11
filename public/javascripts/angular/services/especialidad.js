(function(){
    var app = angular.module('Especialidad',['ngResource']);
    app.factory('EspecialidadService',['$resource',function($resource){
        var factory = $resource(
            '//localhost:9000/especialidad/:idEspecialidad',
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