(function () {
	var app = angular.module('TipoAtencionMedicaAdmin',['TipoAtencionMedica','Especialidad','ngCookies']);

	app.controller('listaTipoAtencionMedicaController',['$scope','TipoAtencionMedicaService','EspecialidadService',function($scope, TipoAtencionMedicaService,EspecialidadService){
		$scope.tipoAtencionMedicaList = TipoAtencionMedicaService.query();
		$scope.nuevoTipoAtencionMedica = {};
		$scope.especialidadList = EspecialidadService.query();
		$scope.especialidadEscojida = {};

		$scope.cargarInformacionEditar = function(tipoAtencionMedica){
			$scope.tipoAtencionMedicaEditar = tipoAtencionMedica;
			EspecialidadService.get({idEspecialidad:tipoAtencionMedica.idEspecialidad}).$promise.then(function(result){
				$scope.especialidadEscojida = result;
			})
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

		$scope.editarTipoAtencionMedica = function(){
			TipoAtencionMedicaService.actualizar({idTipoAtencion: $scope.tipoAtencionMedicaEditar.tamId},
			{
				tamNombre: $scope.tipoAtencionMedicaEditar.tamNombre,
				tamDuracionHoras: $scope.tipoAtencionMedicaEditar.tamDuracionHoras,
				tamDuracionMinutos: $scope.tipoAtencionMedicaEditar.tamDuracionMinutos,
				tamActivo: $scope.tipoAtencionMedicaEditar.tamActivo,
				espId: $scope.especialidadEscojida.espId
			}).$promise.then(
			function(result){
				$scope.tipoAtencionMedicaEditar = result;
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