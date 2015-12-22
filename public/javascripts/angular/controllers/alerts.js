app.controller('AlertsCtrl', ['$scope', 'alertService',function ($scope, alertService) {
	$scope.alerts = alertService.get();
	console.log(alertService);
}]);