(function () {
	var app = angular.module('navBar', []);

	app.controller('NavBarController', function(){
		this.usuario = {};

		this.logIn = function(){
			console.log(this.usuario.email);
			console.log(this.usuario.password);
		};
	});
})();