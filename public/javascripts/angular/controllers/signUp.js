(function(){
	var app = angular.module('signUp', ['alertSignUp']);
	app.controller('SignUpController', ['$scope','$http','alertService','$location',function ($scope,$http, alertService, $location) {
		console.log("Entro en el singin");
		$scope.agregarUsuario = function agregarUsuario () {
			if($scope.password !== $scope.confirmPassword){
				alertService.add('danger', 'Las contraseñas no coinciden.');
			}else{
				$http({
					method: 'POST',
					url: 'http://localhost:9000/usuario',
					data: {
						usuUser: $scope.usuario,                
						usuPassword: $scope.password,
						usuPreguntaRecuperacion: $scope.preguntaRecuperacion,
						usuRespuestaRecuperacion: $scope.respuestaRecuperacion,
						usuEmail: $scope.email
					}
				}).then(
				function exitoEnElGuardado(respuesta) {
					console.log(respuesta);
					window.location.replace('/emailMessage/' + $scope.email);
				},
				function falloEnElGuardado(error) {
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