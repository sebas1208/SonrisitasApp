(function(){
	var app = angular.module('SonrisitasHome', ['ui.bootstrap','ui.router','ngResource','Citas','Modal','ngCookies']);

	app.controller('SessionNavBarController', ['$scope','$cookies','$window', function($scope,$cookies, $window){
		$scope.userEmail = $cookies.get('userEmail');

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
	        templateUrl: 'partials/home/partial-home-content.html'
	    })
	    .state('reservar-citas', {
	        url: '/reservar-citas',
	        templateUrl: 'partials/home/partial-home-reservar-citas.html'
	    })
	    .state('reservar-citas-odontologos', {
	        url: '/reservar-citas-odontologos/:idEspecialidad/:idTipoAtencionMedica',
	        templateUrl: 'partials/home/partial-home-reservar-citas-odontologos.html'
	    })
	    .state('reservar-citas-horarios', {
	        url: '/reservar-citas-horarios/:idEspecialidad/:idTipoAtencionMedica/:idOdontologo',
	        templateUrl: 'partials/home/partial-home-reservar-citas-horarios.html'
	    }).
	    state('lista-citas', {
	        url: '/lista-citas/:idUsuario',
	        templateUrl: 'partials/home/partial-home-lista-citas.html'
	    });
	});
})();