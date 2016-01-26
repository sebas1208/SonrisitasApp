(function () {
	var app = angular.module('OdontologoAdmin',['Odontologo','ngCookies']);

	app.controller('listaOdontologoController',['$scope','OdontologoService',function($scope, OdontologoService){
		$scope.odontologoList = OdontologoService.query();
		$scope.nuevoOdontologo = {};

		$scope.cargarInformacionEditar = function(odontologo){
			$scope.odontologoEditar = odontologo;
			console.log(odontologo);
		};

		$scope.cargarInformacionEliminar = function(odontologo,index){
			$scope.odontologoEliminar = odontologo;
			$scope.indiceEliminar = index;
		};

		$scope.crearOdontologo = function () {
			OdontologoService.save({
				odoNombres: $scope.nuevoOdontologo.nombres,
				odoApellidos: $scope.nuevoOdontologo.apellidos,
				odoDireccion: $scope.nuevoOdontologo.direccion,
				odoTelefono: $scope.nuevoOdontologo.telefono,
				odoEmail: $scope.nuevoOdontologo.email,
				odoCedula: $scope.nuevoOdontologo.cedula
			}).$promise.then(function(result){
				$scope.odontologoList.push(result);
				$scope.nuevoOdontologo = {};
			},function(error){
				console.log(error);
			}
			);
		};

		$scope.editarOdontologo = function(){
			OdontologoService.actualizar({idOdontologo: $scope.odontologoEditar.odoId},
				{	odoNombres: $scope.odontologoEditar.odoNombres,
					odoApellidos: $scope.odontologoEditar.odoApellidos,
					odoDireccion: $scope.odontologoEditar.odoDireccion,
					odoTelefono: $scope.odontologoEditar.odoTelefono,
					odoEmail: $scope.odontologoEditar.odoEmail,
					odoCedula: $scope.odontologoEditar.odoCedula,
					odoActivo: $scope.odontologoEditar.odoActivo}).$promise.then(
					function(result){
						$scope.odontologoEditar = result;
					}, function(error){
						console.log(error);
					});
		};

		$scope.eliminarOdontologo = function(){
			OdontologoService.delete({idOdontologo:$scope.odontologoEliminar.odoId}).$promise.then(
				function(result){
					$scope.odontologoList.splice($scope.indiceEliminar,1)
				},function(error){
					console.log(error);
				});
		};

	}]);
})();