(function () {
	var app = angular.module('EspecialidadAdmin',['Especialidad','ngCookies']);

	app.controller('listaEspecialidadesController',['$scope','EspecialidadService',function($scope, EspecialidadService){
		$scope.especialidadList = EspecialidadService.query();
		$scope.nuevaEspecialidad = {};

		$scope.cargarInformacionEditar = function(especialidad){
			$scope.especialidadEditar = especialidad;
			console.log($scope.especialidadEditar);
		};

		$scope.cargarInformacionEliminar = function(especialidad,index){
			$scope.especialidadEliminar = especialidad;
			$scope.indiceEliminar = index;
			console.log($scope.especialidadEliminar);
		};

		$scope.crearEspecialidad = function () {
			EspecialidadService.save({
				espNombre: $scope.nuevaEspecialidad.nombre,
				espArea: $scope.nuevaEspecialidad.area
			}).$promise.then(function(result){
				$scope.especialidadList.push(result);
				$scope.nuevaEspecialidad = {};
				console.log(result);
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

		$scope.eliminarEspecialidad = function(){
			EspecialidadService.delete({idEspecialidad:$scope.especialidadEliminar.espId}).$promise.then(
				function(result){
					$scope.especialidadList.splice($scope.indiceEliminar,1)
				});
		};
	}]);
})();