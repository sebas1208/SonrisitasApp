(function () {
	var app = angular.module('TipoAtencionMedicaAdmin',['TipoAtencionMedica','ngCookies']);

	app.controller('listaTipoAtencionMedicaController',['$scope','TipoAtencionMedicaService',function($scope, TipoAtencionMedicaService){
		$scope.tipoAtencionMedicaList = TipoAtencionMedicaService.query();
		console.log($scope.tipoAtencionMedicaList);
	}]);
})();