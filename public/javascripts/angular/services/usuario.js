(function(){
    var app = angular.module('Usuario',['ngResource']);

    app.factory('UsuarioService',['$resource',function($resource){

        var factory = $resource(
            '//obscure-atoll-1131.herokuapp.com/usuario/:idUsuario',
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
                url:'//obscure-atoll-1131.herokuapp.com/usuario/buscarPorEmail/:email',
                method: 'GET',
                params:{
                    email: '@email'
                }
            }
        });

        return factory;

    }]);
})();
