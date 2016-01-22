(function () {
	var app = angular.module('OdontologoAdmin',['Odontologo','ngCookies']);

	app.controller('listaOdontologoController',['$scope','OdontologoService',function($scope, OdontologoService){
		$scope.odontologoList = OdontologoService.query();
		console.log($scope.odontologoList);
	}]);
})();