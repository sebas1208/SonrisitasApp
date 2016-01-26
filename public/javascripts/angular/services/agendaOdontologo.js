(function(){
    var app = angular.module('AgendaOdontologo',[]);

    app.factory('AgendaOdontologoService',['$resource',function($resource){

        var factory = $resource(
            //'http://localhost:9000/agendaOdontologo/:idAgendaOdontologo',
            'https://localhost:9000/agendaOdontologo/:idAgendaOdontologo',
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
                url:'https://localhost:9000/agendaOdontologo/:idOdontologo',
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