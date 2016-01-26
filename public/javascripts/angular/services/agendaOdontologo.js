(function(){
    var app = angular.module('AgendaOdontologo',[]);

    app.factory('AgendaOdontologoService',['$resource',function($resource){

        var factory = $resource(
            'https://obscure-atoll-1131.herokuapp.com/agendaOdontologo/:idAgendaOdontologo',
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
                //url:'http://localhost:9000/agendaOdontologo/:idOdontologo',
                url:'https://obscure-atoll-1131.herokuapp.com/agendaOdontologo/:idOdontologo',
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
