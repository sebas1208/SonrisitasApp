(function () {
	var app = angular.module('logIn',['alertSignUp','ngCookies','AuthenticateUser','Usuario']);

	app.controller('logInController',['$state','$scope','$http','$window','alertService','$cookies','AuthenticateUserService','UsuarioService', function($state,$scope,$http,$window,alertService,$cookies,AuthenticateUserService,UsuarioService){

		$scope.logIn = function(){
			AuthenticateUserService.authenticateUser(
			{
				password: $scope.password,
				email: $scope.email
			}).$promise.then(
			function(result){
				UsuarioService.buscarPorEmail({email:$scope.email}).$promise.then(function(result){
					$cookies.put('userName',result.usuUser);
				});
				$cookies.put('userEmail',$scope.email);
				if(result.redirectTo === "admin"){
					$window.location.href = "/admin";
				}else{
					$window.location.href = "/home";
				}
			},
			function(error){
				console.log(error);
				if(error.status === 400) {
					angular.forEach(error.data, function(value, key) {
						alertService.add('danger', key.replace('usu','') + ' : ' + value);
					});
				}
				if(error.status === 500) {
					alertService.add('danger', 'Internal server error!');
				}
			});
		};
	}]);
})();