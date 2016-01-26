(function () {
	var app = angular.module('Citas',['Especialidad', 'Odontologo','AgendaOdontologo','TipoAtencionMedica','Usuario','AtencionMedica','ngCookies']);

	app.controller('citasController',['$scope','$http','EspecialidadService',function($scope, $http, EspecialidadService){
		EspecialidadService.query().$promise.then(        
			function succes(respuesta){
				console.log(respuesta);
				$scope.especialidadList = respuesta;
				//console.log($scope.especialidades[0].odontologoEspecialidadList[0].odoId.odoNombres);
			},
			function error(error){
				console.log(error);
			});	
	}]);

	app.controller('listaCitasController', ['$scope','$cookies','AtencionMedicaService','UsuarioService', function($scope,$cookies,AtencionMedicaService,UsuarioService){
		UsuarioService.buscarPorEmail({email:$cookies.get('userEmail')}).$promise.then(
			function(result){
				$scope.usuario = result;
				AtencionMedicaService.buscarPorUsuario({idUsuario:$scope.usuario.usuId}).$promise.then(
					function(result){
						$scope.listaCitas = result;
					},
					function(error){
						$scope.error = error;		
					});
			},
			function(error){
				$scope.error = error;
			});

		$scope.cargarInformacionEliminar = function(cita,indice){
			$scope.citaEliminar = cita;
			$scope.indiceEliminar = indice;
		}

		$scope.eliminarCita = function(){
			AtencionMedicaService.delete({idAtencionMedica: $scope.citaEliminar.atmId}).$promise.then(
				function(result){
					$scope.listaCitas.splice($scope.indiceEliminar,1)
				});
		}
	}]);

	app.controller('tipoAtencionMedica', ['$scope','TipoAtencionMedicaService', function($scope,TipoAtencionMedicaService){
		$scope.tipoAtencionMedicaList = TipoAtencionMedicaService.query();
		console.log($scope.tipoAtencionMedicaList);
	}]);

	app.controller('odontologosController', ['$scope','EspecialidadService','TipoAtencionMedicaService','$stateParams', function($scope,EspecialidadService,TipoAtencionMedicaService,$stateParams){
		$scope.especialidad = EspecialidadService.get({idEspecialidad: $stateParams.idEspecialidad});
		$scope.tipoAtencionMedica = TipoAtencionMedicaService.get({idTipoAtencion: $stateParams.idTipoAtencionMedica})
	}]);

	app.controller('horariosController', ['$scope','$http','$stateParams','$cookies','$window','OdontologoService','EspecialidadService','AgendaOdontologoService','TipoAtencionMedicaService','UsuarioService','AtencionMedicaService', function($scope,$http,$stateParams,$cookies,$window,OdontologoService,EspecialidadService,AgendaOdontologoService,TipoAtencionMedicaService,UsuarioService,AtencionMedicaService){

		$scope.idEspecialidad = $stateParams.idEspecialidad;
		$scope.idOdontologo = $stateParams.idOdontologo;
		$scope.odontologo = OdontologoService.get({idOdontologo: $scope.idOdontologo});
		$scope.especialidad = EspecialidadService.get({idEspecialidad: $scope.idEspecialidad});
		$scope.tipoAtencionMedica = TipoAtencionMedicaService.get({idTipoAtencion: $stateParams.idTipoAtencionMedica})
		$scope.agendaOdontologoList = AgendaOdontologoService.buscarPorOdontologo({idOdontologo: $scope.idOdontologo});
		$scope.usuario = UsuarioService.buscarPorEmail({email:$cookies.get('userEmail')});
		$scope.horariosPermitidosDia = [];

		$scope.escojerHorario = function(agendaOdontologo){			
			$scope.agendaOdontologoSeleccionada = agendaOdontologo;			
		}

		$scope.seleccionarDia = function(){
			$scope.fechaSeleccionada = this.fechaSeleccionada;		
			var agendaOdontologoDiaSeleccionadoList = [];
			for (var i = 0; i < $scope.agendaOdontologoList.length; i++) {
				if($scope.agendaOdontologoList[i].ageDia == ($scope.fechaSeleccionada.getDay())){
					agendaOdontologoDiaSeleccionadoList.push($scope.agendaOdontologoList[i]);
				}				
			};			
			var horariosPermitidosDia = [];
			for (var i = 0; i < agendaOdontologoDiaSeleccionadoList.length; i++) {
				var inicio = agendaOdontologoDiaSeleccionadoList[i].ageHoraInicio;
				var fin = agendaOdontologoDiaSeleccionadoList[i].ageHoraFin;
				for (var j = inicio; j < fin; j=j+1800000) {
					horariosPermitidosDia.push(j);
				};
			};
			$scope.horariosPermitidosDia = horariosPermitidosDia;
		}

		$scope.seleccionarHora = function(hora){
			$scope.horaSeleccionada = hora;
		}

		$scope.confirmarCita = function(){
			var fecha = new Date(this.fechaSeleccionada);
			fecha = fecha.getDate() + '/' + (fecha.getMonth()+1) + '/' + fecha.getFullYear();
			var horaInicio = new Date($scope.horaSeleccionada);
			horaInicio = horaInicio.getHours() + ':' + horaInicio.getMinutes();
			var horaFin = $scope.horaSeleccionada + ($scope.tipoAtencionMedica.tamDuracionHoras * 3600000) + ($scope.tipoAtencionMedica.tamDuracionMinutos * 60000);
			horaFin = new Date(horaFin);
			horaFin = horaFin.getHours() + ':' + horaFin.getMinutes();		
			AtencionMedicaService.save({
				atmFecha: fecha,
				atmHoraInicio: horaInicio,
				atmHoraFin: horaFin,
				odoId: $scope.odontologo.odoId,
				tamId: $scope.tipoAtencionMedica.tamId,
				usuId: $scope.usuario.usuId
			}).$promise.then(function(result){
				$window.location.href = "/home#/lista-citas/";
			}, function(error){
				console.log(error);
				$scope.error = error.data;
			});
		}
	}]);
})();