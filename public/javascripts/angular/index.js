(function(){
	var app = angular.module('SonrisitasIndex', ['ui.bootstrap','ui.router','signUp','emailMessage','logIn']);

	app.config(function ($stateProvider, $urlRouterProvider) {	
		$urlRouterProvider.otherwise('/content');
		
		$stateProvider        
	    // HOME STATES AND NESTED VIEWS ========================================
	    // .state('content', {
	    //     url: '/content',
	    //     templateUrl: 'partials/partial-home.html'
	    // })
	    .state('login', {
	        url: '/login',
	        templateUrl: 'partials/partial-login.html'
	    })
	    .state('content', {
	        url: '/content',
	        templateUrl: 'partials/partial-content.html'
		})
		.state('signUp', {
	        url: '/signUp',
	        templateUrl: 'partials/partial-signup.html'
		}); 
	});
})();