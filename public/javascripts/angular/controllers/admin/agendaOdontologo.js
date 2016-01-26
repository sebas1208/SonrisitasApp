(function () {
	var app = angular.module('AgendaOdontologoAdmin',['AgendaOdontologo','Odontologo','ngCookies']);

	app.controller('listaAgendaOdontologoController',['$scope','AgendaOdontologoService','OdontologoService',function($scope, AgendaOdontologoService,OdontologoService){
		$scope.agendaOdontologoList = AgendaOdontologoService.query();
		console.log($scope.agendaOdontologoList);
		$scope.nuevaAgendaOdontologo = {};
		$scope.odontologoList = OdontologoService.query();
		$scope.horarioList = [];

		for (var i = 7; i < 11; i++) {
			$scope.horarioList.push("" + i + ":00");
			$scope.horarioList.push("" + i + ":30");
		}

		for (var i = 11; i < 20; i++) {
			$scope.horarioList.push("" + i + ":00");
			$scope.horarioList.push("" + i + ":30");
		}
		$scope.horarioList.pop();

		$scope.escogerHorarioInicio = function(horario,indice){
			$scope.nombreHorarioInicioEscogido = horario;
			$scope.horarioInicioEscogido = indice;
		}

		$scope.escogerHorarioFin = function(horario,indice){
			$scope.nombreHorarioFinEscogido = horario;
			$scope.horarioFinEscogido = indice;
		}

		$scope.escogerOdontologo = function(odontologo){
			$scope.odontologoEscogido = odontologo;
		}

		$scope.escogerDia = function(dia){
			$scope.diaEscogido = dia;
			$scope.nombreDia = obtenerNemotecnicoDia(dia);
		}

		var obtenerNemotecnicoDia = function(dia){
			var nombreDia = dia == 1 ? 'Lunes':
			dia == 2 ? 'Martes':
			dia == 3 ? 'Miercoles':
			dia == 4 ? 'Jueves':
			dia == 5 ? 'Viernes':
			dia == 6 ? 'Sabado':
			dia == 7 ? 'Domingo':
			'';
			return nombreDia;
		}

		$scope.cargarInformacionEditar = function(especialidad){
			$scope.especialidadEditar = especialidad;
			console.log($scope.especialidadEditar);
		};

		$scope.cargarInformacionEliminar = function(especialidad,index){
			$scope.especialidadEliminar = especialidad;
			$scope.indiceEliminar = index;
			console.log($scope.especialidadEliminar);
		};

		$scope.crearAgendaOdontologo = function () {
			AgendaOdontologoService.save({
				ageDia: $scope.diaEscogido,
				ageHoraInicio: $scope.nombreHorarioInicioEscogido,
				ageHoraFin: $scope.nombreHorarioFinEscogido,
				ageDiaNombre: $scope.nombreDia,
				odoId: $scope.odontologoEscogido.odoId
			}).$promise.then(function(result){
				$scope.agendaOdontologoList.push(result);
				$scope.nuevaAgendaOdontologo = {};
				$scope.odontologoEscogido = {};
			},function(error){
				console.log(error);
			}
			);
		};

		$scope.editarEspecialidad = function(){
			EspecialidadService.actualizar({idEspecialidad: $scope.especialidadEditar.espId},
				{espNombre: $scope.especialidadEditar.espNombre,
					espArea: $scope.especialidadEditar.espArea,
					espActivo: $scope.especialidadEditar.espActivo}).$promise.then(
					function(result){
						$scope.especialidadEditar = result;
						console.log(result);
					}, function(error){
						console.log(error);
					});
				};

				$scope.eliminarEspecialidad = function(){
					EspecialidadService.delete({idEspecialidad:$scope.especialidadEliminar.espId}).$promise.then(
						function(result){
							$scope.especialidadList.splice($scope.indiceEliminar,1)
						});
				};

			}]);
})();