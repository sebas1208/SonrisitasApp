(function(){
	var app = angular.module('AdminHome', ['ui.bootstrap','ui.router','ngResource','ngCookies','angular.filter','Modal','EspecialidadAdmin','AgendaOdontologoAdmin','OdontologoAdmin','OdontologoEspecialidadAdmin','TipoAtencionMedicaAdmin']);

	app.controller('SessionNavBarController', ['$scope','$cookies','$window', function($scope,$cookies, $window){
		$scope.userEmail = $cookies.get('userEmail');
		$scope.userName = $cookies.get('userName');
		console.log($scope.userName);

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
			templateUrl: 'partials/admin/content.html',
			data: {
				nivelAcceso: 2,
				loginRequerido: true
			}
		})
		.state('especialidad', {
			url: '/especialidad',
			templateUrl: 'partials/admin/especialidad.html',
			controller: 'listaEspecialidadesController',
			data: {
				nivelAcceso: 2,
				loginRequerido: true
			}
		})
		.state('odontologo', {
			url: '/odontologo',
			templateUrl: 'partials/admin/odontologo.html',
			controller: 'listaOdontologoController',
			data: {
				nivelAcceso: 2,
				loginRequerido: true
			}
		})
		.state('tipoAtencionMedica', {
			url: '/tipoAtencionMedica',
			templateUrl: 'partials/admin/tipoAtencionMedica.html',
			controller: 'listaTipoAtencionMedicaController',
			data: {
				nivelAcceso: 2,
				loginRequerido: true
			}
		})
		.state('agendaOdontologo', {
			url: '/agendaOdontologo',
			templateUrl: 'partials/admin/agendaOdontologo.html',
			controller: 'listaAgendaOdontologoController',
			data: {
				nivelAcceso: 2,
				loginRequerido: true
			}
		})
		.state('odontologoEspecialidad',{
			url: '/odontologoEspecialidad',
			templateUrl: 'partials/admin/odontologoEspecialidad.html',
			controller: 'listaOdontologoEspecialidadController',
			data: {
				nivelAcceso: 2,
				loginRequerido: true
			}
		});
	});

app.run(function ($rootScope, $cookies, $state, $window) {
	console.log('entro run');

	$rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
		var requiereLogin = toState.data.loginRequerido;
		var nivelAcceso = toState.data.nivelAcceso;

		if (requiereLogin) {
			console.log('Requiere login');
			if ($cookies.get('userEmail')) {
				if (nivelAcceso != 2) {
					console.log('No ha hecho Login');
					event.preventDefault();
					$window.location.href = "/#/login";
					return $state.go('login');
				} else {

				}
			} else {
				console.log('No ha hecho Login');
				event.preventDefault();
				$window.location.href = "/#/login";
				return $state.go('login');
			}
		} else {
			console.log('No requiere login');
		}
	});
});
})();