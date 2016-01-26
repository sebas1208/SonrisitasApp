(function(){
    var app = angular.module('OdontologoEspecialidad',[]);

    app.factory('OdontologoEspecialidadService',['$resource',function($resource){

        var factory = $resource(
            //'http://localhost:9000/odontologoEspecialidad/:idOdontologoEspecialidad',
            'https://localhost:9000/odontologoEspecialidad/:idOdontologoEspecialidad',
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