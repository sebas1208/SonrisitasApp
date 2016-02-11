(function(){
    var app = angular.module('AgendaOdontologo',['ngResource']);

    app.factory('AgendaOdontologoService',['$resource',function($resource){

        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/agendaOdontologo/:idAgendaOdontologo',
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
                url:'//obscure-atoll-1131.herokuapp.com/agendaOdontologo/:idOdontologo',
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
