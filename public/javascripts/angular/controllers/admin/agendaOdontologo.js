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

		var obtenerNemotecnicoHora = function(horaLong){
			var hora = new Date(horaLong);
			return hora.getHours() + ':' + hora.getMinutes();
		}

		var removeByAttr = function(arr, attr, value){
			var i = arr.length;
			while(i--){
				if( arr[i] && arr[i].hasOwnProperty(attr) && (arguments.length > 2 && arr[i][attr] === value ) ){
					arr.splice(i,1);
				}
			}
			return arr;
		}

		$scope.cargarInformacionEditar = function(agendaOdontologo){
			$scope.agendaOdontologoEditar = agendaOdontologo;
			$scope.nombreDia = agendaOdontologo.ageDiaNombre;
			$scope.nombreHorarioInicioEscogido = obtenerNemotecnicoHora(agendaOdontologo.ageHoraInicio);
			$scope.nombreHorarioFinEscogido = obtenerNemotecnicoHora(agendaOdontologo.ageHoraFin);
			$scope.diaEscogido = agendaOdontologo.ageDia;
		};

		$scope.cargarInformacionEliminar = function(agendaOdontologo,index){
			$scope.agendaOdontologoEliminar = agendaOdontologo;
			$scope.indiceEliminar = index;
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

		$scope.editarAgendaOdontologo = function(){
			AgendaOdontologoService.actualizar({idAgendaOdontologo: $scope.agendaOdontologoEditar.ageId},
			{
				ageDia: $scope.diaEscogido,
				ageHoraInicio: $scope.nombreHorarioInicioEscogido,
				ageHoraFin: $scope.nombreHorarioFinEscogido,
				ageDiaNombre: $scope.nombreDia,
				ageActivo: $scope.agendaOdontologoEditar.ageActivo
			}).$promise.then(
			function(result){
				$scope.agendaOdontologoEditar = result;
				console.log(result);
			}, function(error){
				console.log(error);
			});
		};

		$scope.eliminarAgendaOdontologo = function(){
			AgendaOdontologoService.delete({idAgendaOdontologo:$scope.agendaOdontologoEliminar.ageId}).$promise.then(
				function(result){
					removeByAttr($scope.agendaOdontologoList,'ageId',$scope.agendaOdontologoEliminar.ageId);
				});
		};

	}]);
})();