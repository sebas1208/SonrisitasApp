(function(){
	var app = angular.module('emailMessage', []);
	
	app.controller('emailMessage', ['$location',function ($location) {
		setTimeout(function(){
			window.location.replace('/');
		} , 5000)
	}]);
})();