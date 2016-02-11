(function(){
    var app = angular.module('Usuario',['ngResource']);

    app.factory('UsuarioService',['$resource',function($resource){

        var factory = $resource(
            '//localhost:9000/usuario/:idUsuario',
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
                url:'//localhost:9000/usuario/buscarPorEmail/:email',
                method: 'GET',
                params:{
                    email: '@email'
                }
            }
        });

        return factory;

    }]);
})();
