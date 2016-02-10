(function () {
	var app = angular.module('OdontologoEspecialidadAdmin',['OdontologoEspecialidad','Especialidad','Odontologo','ngCookies']);

	app.controller('listaOdontologoEspecialidadController',['$scope','OdontologoEspecialidadService','EspecialidadService','OdontologoService',function($scope, OdontologoEspecialidadService,EspecialidadService,OdontologoService){

		$scope.odontologoEscogido = {};
		$scope.especialidadEscogida = {};
		OdontologoEspecialidadService.query().$promise.then(
			function(result){
				$scope.odontologoEspecialidadList = result;
				console.log($scope.odontologoEspecialidadList);
				console.log(result.length);
				for (var i = result.length - 1; i >= 0; i--) {
					$scope.odontologoEspecialidadList[i].espId = EspecialidadService.get({idEspecialidad:$scope.odontologoEspecialidadList[i].idEspecialidad});
				};
			});
		$scope.odontologoList = OdontologoService.query();
		$scope.especialidadList = EspecialidadService.query();

		$scope.crearOdontologoEspecialidad = function(){
			OdontologoEspecialidadService.save({
				odoId:$scope.odontologoEscogido.odoId,
				espId:$scope.especialidadEscogida.espId
			}).$promise.then(function(result){
				OdontologoEspecialidadService.query().$promise.then(
					function(result){
						$scope.odontologoEspecialidadList = result;
						console.log($scope.odontologoEspecialidadList);
						console.log(result.length);
						for (var i = result.length - 1; i >= 0; i--) {
							$scope.odontologoEspecialidadList[i].espId = EspecialidadService.get({idEspecialidad:$scope.odontologoEspecialidadList[i].idEspecialidad});
						};
					});
			});
		};

		$scope.editarOdontologoEspecialidad = function(){
			console.log($scope.especialidadEscogida);
			OdontologoEspecialidadService.actualizar({idOdontologoEspecialidad: $scope.odontologoEspecialidadEditar.odeId},
			{
				espId:$scope.especialidadEscogida.espId
			}).$promise.then(function(result){
				OdontologoEspecialidadService.query().$promise.then(
					function(result){
						$scope.odontologoEspecialidadList = result;
						console.log($scope.odontologoEspecialidadList);
						for (var i = result.length - 1; i >= 0; i--) {
							$scope.odontologoEspecialidadList[i].espId = EspecialidadService.get({idEspecialidad:$scope.odontologoEspecialidadList[i].idEspecialidad});
						};
					});
			});
		}

		$scope.eliminarOdontologoEspecialidad = function(){
			OdontologoEspecialidadService.delete({idOdontologoEspecialidad: $scope.odontologoEspecialidadEliminar.odeId}).$promise.then(function(){
				OdontologoEspecialidadService.query().$promise.then(
					function(result){
						$scope.odontologoEspecialidadList = result;
						console.log($scope.odontologoEspecialidadList);
						console.log(result.length);
						for (var i = result.length - 1; i >= 0; i--) {
							$scope.odontologoEspecialidadList[i].espId = EspecialidadService.get({idEspecialidad:$scope.odontologoEspecialidadList[i].idEspecialidad});
						};
					});
			});
		}

		$scope.cargarInformacionEditar = function(odontologoEspecialidad){
			$scope.odontologoEspecialidadEditar = odontologoEspecialidad;
			EspecialidadService.get({idEspecialidad: odontologoEspecialidad.idEspecialidad}).$promise.then(function(result){
				$scope.especialidadEscogida = result;
				console.log($scope.especialidadEscogida);
			});
		}

		$scope.cargarInformacionEliminar = function(odontologoEspecialidad,indice){
			$scope.odontologoEspecialidadEliminar = odontologoEspecialidad;
			$scope.indiceEliminar = indice;
		};

		$scope.escogerOdontologo = function(odontologo){
			console.log(odontologo);
			$scope.odontologoEscogido = odontologo;
		}

		$scope.escogerEspecialidad = function(especialidad){
			$scope.especialidadEscogida = especialidad;
		}
	}]);
})();