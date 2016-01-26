(function(){
    var app = angular.module('Usuario',[]);

    app.factory('UsuarioService',['$resource',function($resource){

        var factory = $resource(
            'https://obscure-atoll-1131.herokuapp.com/usuario/:idUsuario',
            //'http://localhost:9000/usuario/:idUsuario',
            {
                idUsuario:'@idUsuario'
            },
            {
            actualizar: 
            {
                method:'PUT', 
                params:{
                    idUsuario:'@idUsuario'
                }            
            },
            buscarPorEmail:
            {
                //url:'http://localhost:9000/usuario/buscarPorEmail/:email',
                url:'https://obscure-atoll-1131.herokuapp.com/usuario/buscarPorEmail/:email',
                method: 'GET',
                params:{
                    email: '@email'
                }
            }
        });

        return factory;

    }]);
})();
