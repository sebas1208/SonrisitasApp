(function(){
    var app = angular.module('OdontologoEspecialidad',[]);

    app.factory('OdontologoEspecialidadService',['$resource',function($resource){

        var factory = $resource(
            'http://localhost:9000/odontologoEspecialidad/:idOdontologoEspecialidad',
            //'https://obscure-atoll-1131.herokuapp.com/:idOdontologoEspecialidad',
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