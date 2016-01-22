(function () {
	var app = angular.module('AgendaOdontologoAdmin',['AgendaOdontologo','ngCookies']);

	app.controller('listaAgendaOdontologoController',['$scope','AgendaOdontologoService',function($scope, AgendaOdontologoService){
		$scope.agendaOdontologoList = AgendaOdontologoService.query();
		console.log($scope.agendaOdontologoList);
	}]);
})();