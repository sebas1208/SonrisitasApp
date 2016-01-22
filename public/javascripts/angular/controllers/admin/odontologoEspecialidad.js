(function () {
	var app = angular.module('OdontologoEspecialidadAdmin',['OdontologoEspecialidad','ngCookies']);

	app.controller('listaOdontologoEspecialidadController',['$scope','OdontologoEspecialidadService',function($scope, OdontologoEspecialidadService){
		$scope.odontologoEspecialidadList = OdontologoEspecialidadService.query();
		console.log($scope.odontologoEspecialidadList);
	}]);
})();