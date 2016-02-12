(function(){
	var app = angular.module('SonrisitasHome', ['ui.bootstrap','ui.router','ngResource','Citas','Modal','ngCookies','toastr']);

	app.controller('SessionNavBarController', ['$scope','$cookies','$window','toastr', function($scope,$cookies, $window,toastr){
		$scope.userEmail = $cookies.get('userEmail');
		$scope.userName = $cookies.get('userName');
		toastr.info("Bienvenido Usuario!");

		$scope.logout = function(){
			$cookies.remove('userEmail');
			$window.location.href = "/";
		}
	}])

	app.config(function ($stateProvider, $urlRouterProvider) {	
		$urlRouterProvider.otherwise('/content');

		$stateProvider
		.state('content', {
			url: '/content',
			templateUrl: 'partials/home/partial-home-content.html',
			data: {
				nivelAcceso: 1,
				loginRequerido: true
			}
		})
		.state('reservar-citas', {
			url: '/reservar-citas',
			templateUrl: 'partials/home/partial-home-reservar-citas.html',
			data: {
				nivelAcceso: 1,
				loginRequerido: true
			}
		})
		.state('reservar-citas-odontologos', {
			url: '/reservar-citas-odontologos/:idEspecialidad/:idTipoAtencionMedica',
			templateUrl: 'partials/home/partial-home-reservar-citas-odontologos.html',
			data: {
				nivelAcceso: 1,
				loginRequerido: true
			}
		})
		.state('reservar-citas-horarios', {
			url: '/reservar-citas-horarios/:idEspecialidad/:idTipoAtencionMedica/:idOdontologo',
			templateUrl: 'partials/home/partial-home-reservar-citas-horarios.html',
			data: {
				nivelAcceso: 1,
				loginRequerido: true
			}
		}).
		state('lista-citas', {
			url: '/lista-citas/:idUsuario',
			templateUrl: 'partials/home/partial-home-lista-citas.html',
			data: {
				nivelAcceso: 1,
				loginRequerido: true
			}
		})
		.state('login', {
			url: '/login',
			templateUrl: 'partials/partial-login.html',
			data: {
				nivelAcceso: 0,
				loginRequerido: false
			}
		});
	});

	app.run(function ($rootScope, $cookies, $state, $window,toastr) {
		console.log('entro run');

		$rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
			var requiereLogin = toState.data.loginRequerido;
			var nivelAcceso = toState.data.nivelAcceso;

			if (requiereLogin) {
				console.log('Requiere login');
				if ($cookies.get('userEmail')) {
					if (nivelAcceso != 1) {
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
					//toastr.info("No tienes acceso a esta seccion. Logueate Primero")
					return $state.go('login');
				}
			} else {
				console.log('No requiere login');
			}
		});
	});
})();