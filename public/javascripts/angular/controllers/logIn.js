(function () {
	var app = angular.module('logIn',['alertSignUp','ngCookies']);

	app.controller('logInController',['$scope','$http','$window','alertService','$cookies', function($scope,$http,$window,alertService,$cookies){				
		$scope.logIn = function(){
			$http({
					method: 'POST',
					url: 'http://localhost:9000/authenticateUser',
					data: {
						password: $scope.password,
						email: $scope.email
					}
				}).then(
				function success(respuesta) {				
					$cookies.put('userEmail',$scope.email);
					$window.location.href = "/home";
				},
				function error(error) {
					if(error.status === 400) {
						angular.forEach(error.data, function(value, key) {							
							alertService.add('danger', key.replace('usu','') + ' : ' + value);
						});
					}
					if(error.status === 500) {
						alertService.add('danger', 'Internal server error!');
					}
					console.log(error);
				});
		};
	}]);
})();