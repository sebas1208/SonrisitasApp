(function(){
	var app = angular.module('signUp', ['alertSignUp','Usuario']);
	app.controller('SignUpController', ['$scope','$http','alertService','$location','UsuarioService',function ($scope,$http, alertService, $location,UsuarioService) {
		console.log("Entro en el singin");
		$scope.agregarUsuario = function agregarUsuario () {
			if($scope.password !== $scope.confirmPassword){
				alertService.add('danger', 'Las contraseñas no coinciden.');
			}else{
				UsuarioService.save({
					usuUser: $scope.usuario,
					usuPassword: $scope.password,
					usuPreguntaRecuperacion: $scope.preguntaRecuperacion,
					usuRespuestaRecuperacion: $scope.respuestaRecuperacion,
					usuEmail: $scope.email}).$promise.then(function(result){
						console.log(respuesta);
						window.location.replace('/emailMessage/' + $scope.email);
					},function(error){
						if(error.status === 400) {
							angular.forEach(error.data, function(value, key) {
								alertService.add('danger', key.replace('usu','') + ' : ' + value);
							});
						}
						if(error.status === 500) {
							alertService.add('danger', 'Internal server error!');
						}
					});
				}
			};
		}]);
})();