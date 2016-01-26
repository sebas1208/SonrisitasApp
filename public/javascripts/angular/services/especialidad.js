(function(){
    var app = angular.module('Especialidad',[]);
    app.factory('EspecialidadService',['$resource',function($resource){
        var factory = $resource(
            //'http://localhost:9000/especialidad/:idEspecialidad',
            'https://localhost:9000/especialidad/:idEspecialidad',
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