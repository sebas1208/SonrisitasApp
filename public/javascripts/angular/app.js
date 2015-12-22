var app = angular.module('sonrisitasApp', ['ui.bootstrap', 'ui.router']);

app.config(function ($stateProvider, $urlRouterProvider) {
	$stateProvider
	.state('signUp', {
		url: "/signUp",
		templateUrl: "localhost:9000/signUp",
		controller: 'controllers/signUp.js'
	}).state('home', {
		url: "/",
		templateUrl: "localhost:9000",
		controller: 'controllers/home.js'
	}).state('emailMessage', {
		url: "/emailMessage",
		templateUrl: "localhost:9000/emailMessage",
		controller: 'controllers/emailMessage.js'
	}).state('signUpOdontologo', {
		url: "/signUpOdontologo",
		templateUrl: "localhost:9000/signUpOdontologo",
		controller: 'controllers/signUpOdontologo.js'
	});
});