(function () {
	var app = angular.module('EspecialidadAdmin',['Especialidad','ngCookies']);

	app.controller('listaEspecialidadesController',['$scope','EspecialidadService',function($scope, EspecialidadService){
		$scope.especialidadList = EspecialidadService.query();
		console.log($scope.especialidadList);
	}]);
})();