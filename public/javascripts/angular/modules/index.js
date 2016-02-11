(function(){
	var app = angular.module('SonrisitasIndex', ['ui.bootstrap','ngCookies','ui.router','signUp','emailMessage','logIn']);

	app.config(function ($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/content');

		$stateProvider
		.state('login', {
			url: '/login',
			templateUrl: 'partials/partial-login.html',
			data: {
				nivelAcceso: 0,
				loginRequerido: false
			}
		})
		.state('content', {
			url: '/content',
			templateUrl: 'partials/partial-content.html',
			data: {
				nivelAcceso: 0,
				loginRequerido: false
			}
		})
		.state('signUp', {
			url: '/signUp',
			templateUrl: 'partials/partial-signup.html',
			data: {
				nivelAcceso: 0,
				loginRequerido: false
			}
		});
	});

	app.run(function ($rootScope, $cookies, $state) {
		console.log('entro run');

		$rootScope.$on('$stateChangeStart', function (event, toState, toParams) {

			var requiereLogin = toState.data.loginRequerido;
			var nivelAcceso = toState.data.nivelAcceso;

			if (requiereLogin) {
				console.log('Requiere login');
				if ($cookies.get('userEmail')) {
					if (nivelAcceso == 1) {
						console.log('puede entrar')
					} else {
						alert("No tienes acceso a esa seccion");
						return $state.go('index.home');
					}
				} else {
					console.log('No ha hecho Login');
					// event.preventDefault();
					toastr.info("No tienes acceso a esta seccion. Logueate Primero")
					$window.location.href = "/";
				}
			} else {
				console.log('No requiere login');
			}

		});
	});
})();