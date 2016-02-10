(function(){
    var app = angular.module('OdontologoEspecialidad',[]);

    app.factory('OdontologoEspecialidadService',['$resource',function($resource){

        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/odontologoEspecialidad/:idOdontologoEspecialidad',
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