(function(){
    var app = angular.module('AgendaOdontologo',['ngResource']);

    app.factory('AgendaOdontologoService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/agendaOdontologo/:idAgendaOdontologo',
            {
                idAgendaOdontologo:'@idAgendaOdontologo'
            },
            {
              actualizar:
              {
                method:'PUT',
                params:{
                    idAgendaOdontologo:'@idAgendaOdontologo'
                }

            },
            buscarPorOdontologo:
            {
                url:'//localhost:9000/agendaOdontologo/:idOdontologo',
                method:'GET',
                isArray: true,
                params:{
                    idOdontologo:'@idOdontologo'
                }
            }
        });

        return factory;

    }]);
})();
