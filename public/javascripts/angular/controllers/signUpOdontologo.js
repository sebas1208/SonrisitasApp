app.controller('SignUpOdontologoController', ['$scope','$http','alertService','$location',function ($scope,$http, alertService, $location) {
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
				agregarOdontologo(respuesta);	
			},
			function falloEnElGuardado(error) {
				if(error.status === 400) {
					angular.forEach(error.data, function(value, key) {
						console.log(alertService);
						alertService.add('danger', key.replace('usu','') + ' : ' + value);
					});
				}
				if(error.status === 500) {
					alertService.add('danger', 'Internal server error!');
				}
				console.log(error);
			});
		}
	};

	$scope.agregarOdontologo = agregarOdontologo;
	function agregarOdontologo (respuesta) {
		console.log(respuesta);
		$http({
			method: 'POST',
			url: 'http://localhost:9000/odontologo',
			data: {
				odoNombres: $scope.nombres,                
				odoApellidos: $scope.apellidos,
				odoDireccion: $scope.direccion,
				odoTelefono: $scope.telefono,
				odoEmail: $scope.email,
				odoCedula: $scope.cedula,
				odoCedula: $scope.cedula,
				usuId: respuesta.data
			}
		}).then(
		function exitoEnElGuardado(respuesta) {
			console.log(respuesta);				
		},
		function falloEnElGuardado(error) {
			if(error.status === 400) {
				angular.forEach(error.data, function(value, key) {
					console.log(alertService);
					alertService.add('danger', key.replace('usu','') + ' : ' + value);
				});
			}
			if(error.status === 500) {
				alertService.add('danger', 'Internal server error!');
			}
			console.log(error);
		});
	};
}]);