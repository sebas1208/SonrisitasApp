(function(){
	var app = angular.module('AdminHome', ['ui.bootstrap','ui.router','ngResource','ngCookies','angular.filter','Modal','EspecialidadAdmin','AgendaOdontologoAdmin','OdontologoAdmin','OdontologoEspecialidadAdmin','TipoAtencionMedicaAdmin']);

	app.controller('SessionNavBarController', ['$scope','$cookies','$window', function($scope,$cookies, $window){
		$scope.userEmail = $cookies.get('userEmail');

		$scope.logout = function(){
			$cookies.remove('userEmail');
			$window.location.href = "/";
		}
	}]);

	app.config(function ($stateProvider, $urlRouterProvider) {	
		$urlRouterProvider.otherwise('/content');

		$stateProvider
	    .state('content', {
	        url: '/content',
	        templateUrl: 'partials/admin/content.html'
	    })
	    .state('especialidad', {
	        url: '/especialidad',
	        templateUrl: 'partials/admin/especialidad.html',
	        controller: 'listaEspecialidadesController'
	    })
	    .state('odontologo', {
	        url: '/odontologo',
	        templateUrl: 'partials/admin/odontologo.html',
	        controller: 'listaOdontologoController'
	    })
	    .state('tipoAtencionMedica', {
	        url: '/tipoAtencionMedica',
	        templateUrl: 'partials/admin/tipoAtencionMedica.html',
	        controller: 'listaTipoAtencionMedicaController'
	    })
	    .state('agendaOdontologo', {
	        url: '/agendaOdontologo',
	        templateUrl: 'partials/admin/agendaOdontologo.html',
	        controller: 'listaAgendaOdontologoController'
	    })
	    .state('odontologoEspecialidad',{
	        url: '/odontologoEspecialidad',
	        templateUrl: 'partials/admin/odontologoEspecialidad.html',
	        controller: 'listaOdontologoEspecialidadController'
	    });
	});
})();