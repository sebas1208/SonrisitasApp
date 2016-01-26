(function () {
	var app = angular.module('TipoAtencionMedicaAdmin',['TipoAtencionMedica','Especialidad','ngCookies']);

	app.controller('listaTipoAtencionMedicaController',['$scope','TipoAtencionMedicaService','EspecialidadService',function($scope, TipoAtencionMedicaService,EspecialidadService){
		$scope.tipoAtencionMedicaList = TipoAtencionMedicaService.query();
		$scope.nuevoTipoAtencionMedica = {};
		$scope.especialidadList = EspecialidadService.query();
		$scope.especialidadEscojida = {};

		$scope.cargarInformacionEditar = function(tipoAtencionMedica){
			$scope.tipoAtencionMedicaEditar = tipoAtencionMedica;
			$scope.especialidadEscojida = tipoAtencionMedica.espId;
			console.log(tipoAtencionMedica);
			console.log($scope.especialidadEscojida);
		};

		$scope.cargarInformacionEliminar = function(tipoAtencionMedica,index){
			$scope.tipoAtencionMedicaEliminar = tipoAtencionMedica;
			$scope.indiceEliminar = index;
		};

		$scope.crearTipoAtencionMedica = function () {
			TipoAtencionMedicaService.save({
				tamNombre: $scope.nuevoTipoAtencionMedica.nombre,
				tamDuracionHoras: $scope.nuevoTipoAtencionMedica.duracionHoras,
				tamDuracionMinutos: $scope.nuevoTipoAtencionMedica.duracionMinutos,
				espId: $scope.especialidadEscojida.espId
			}).$promise.then(function(result){
				$scope.tipoAtencionMedicaList.push(result);
				$scope.nuevoTipoAtencionMedica = {};
			},function(error){
				console.log(error);
			}
			);
		};

		$scope.editarEspecialidad = function(){
			EspecialidadService.actualizar({idEspecialidad: $scope.especialidadEditar.espId},
				{espNombre: $scope.especialidadEditar.espNombre,
					espArea: $scope.especialidadEditar.espArea,
					espActivo: $scope.especialidadEditar.espActivo}).$promise.then(
					function(result){
						$scope.especialidadEditar = result;
						console.log(result);
					}, function(error){
						console.log(error);
					});
		};

		$scope.escojerEspecialidad = function(especialidad){
			$scope.especialidadEscojida = especialidad;
		}

		$scope.eliminarTipoAtencionMedica = function(){
			TipoAtencionMedicaService.delete({idTipoAtencion:$scope.tipoAtencionMedicaEliminar.tamId}).$promise.then(
				function(result){
					$scope.tipoAtencionMedicaList.splice($scope.indiceEliminar,1)
				});
		};
	}]);
})();