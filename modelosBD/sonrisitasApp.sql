/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     6/12/2015 19:01:20                           */
/*==============================================================*/


drop index ADMINISTRADOR_USUARIO_FK;

drop index ADMINISTRADOR_PK;

drop table ADMINISTRADOR;

drop index ODONTOLOGO_AGE_FK;

drop index AGENDA_ODONTOLOGO_PK;

drop table AGENDA_ODONTOLOGO;

drop index HCD_ATM_FK;

drop index ATM_TAM_FK;

drop index ODONTOLOGO_ATM_FK;

drop index PACIENTE_ATM_FK;

drop index ATENCION_MEDICA_PK;

drop table ATENCION_MEDICA;

drop index ESPECIALIDAD_PK;

drop table ESPECIALIDAD;

drop index PACIENTE_HCC_FK;

drop index HISTORIA_CLINICA_CABECERA_PK;

drop table HISTORIA_CLINICA_CABECERA;

drop index HCC_HCD_FK;

drop index HISTORIA_CLINICA_DETALLE_PK;

drop table HISTORIA_CLINICA_DETALLE;

drop index ODONTOLOGO_USUARIO_FK;

drop index ODONTOLOGO_PK;

drop table ODONTOLOGO;

drop index ESPECIALIDAD_ODE_FK;

drop index ODONTOLOGO_ODE_FK;

drop index ODONTOLOGO_ESPECIALIDAD_PK;

drop table ODONTOLOGO_ESPECIALIDAD;

drop index PACIENTE_USUARIO_FK;

drop index PACIENTE_PK;

drop table PACIENTE;

drop index TIPO_ATENCION_MEDICA_PK;

drop table TIPO_ATENCION_MEDICA;

drop index USUARIO_PK;

drop table USUARIO;

/*==============================================================*/
/* Table: ADMINISTRADOR                                         */
/*==============================================================*/
create table ADMINISTRADOR (
   ADM_ID               INT8                 not null,
   USU_ID               INT8                 not null,
   ADM_NOMBRE           VARCHAR(50)          not null,
   ADM_APELLIDO         VARCHAR(50)          not null,
   ADM_DIRECCION        VARCHAR(50)          not null,
   ADM_TELEFONO         VARCHAR(15)          not null,
   ADM_EMAIL            VARCHAR(25)          not null,
   ADM_ACTIVO           BOOL                 null,
   ADM_FECHA_REGISTRO   DATE                 null,
   constraint PK_ADMINISTRADOR primary key (ADM_ID)
);

/*==============================================================*/
/* Index: ADMINISTRADOR_PK                                      */
/*==============================================================*/
create unique index ADMINISTRADOR_PK on ADMINISTRADOR (
ADM_ID
);

/*==============================================================*/
/* Index: ADMINISTRADOR_USUARIO_FK                              */
/*==============================================================*/
create  index ADMINISTRADOR_USUARIO_FK on ADMINISTRADOR (
USU_ID
);

/*==============================================================*/
/* Table: AGENDA_ODONTOLOGO                                     */
/*==============================================================*/
create table AGENDA_ODONTOLOGO (
   AGE_DIA              INT2                 not null,
   AGE_HORA_INICIO      TIME                 not null,
   AGE_HORA_FIN         TIME                 not null,
   AGE_ACTIVO           BOOL                 null,
   AGE_FECHA_REGISTRO   DATE                 null,
   AGE_DIA_NOMBRE       VARCHAR(15)          not null,
   ODO_ID               INT8                 not null,
   AGE_ID               INT8                 not null,
   constraint PK_AGENDA_ODONTOLOGO primary key (AGE_ID)
);

/*==============================================================*/
/* Index: AGENDA_ODONTOLOGO_PK                                  */
/*==============================================================*/
create unique index AGENDA_ODONTOLOGO_PK on AGENDA_ODONTOLOGO (
AGE_ID
);

/*==============================================================*/
/* Index: ODONTOLOGO_AGE_FK                                     */
/*==============================================================*/
create  index ODONTOLOGO_AGE_FK on AGENDA_ODONTOLOGO (
ODO_ID
);

/*==============================================================*/
/* Table: ATENCION_MEDICA                                       */
/*==============================================================*/
create table ATENCION_MEDICA (
   TAM_ID               INT8                 not null,
   ODO_ID               INT8                 not null,
   PAC_ID               INT8                 not null,
   ATM_FECHA            DATE                 not null,
   ATM_HORA_INICIO      TIME                 not null,
   ATM_HORA_FIN         TIME                 not null,
   ATM_ACTIVO           BOOL                 null,
   ATM_FECHA_REGISTRO   DATE                 null,
   ATM_ID               INT8                 not null,
   HCD_ID               INT8                 not null,
   constraint PK_ATENCION_MEDICA primary key (ATM_ID)
);

/*==============================================================*/
/* Index: ATENCION_MEDICA_PK                                    */
/*==============================================================*/
create unique index ATENCION_MEDICA_PK on ATENCION_MEDICA (
ATM_ID
);

/*==============================================================*/
/* Index: PACIENTE_ATM_FK                                       */
/*==============================================================*/
create  index PACIENTE_ATM_FK on ATENCION_MEDICA (
PAC_ID
);

/*==============================================================*/
/* Index: ODONTOLOGO_ATM_FK                                     */
/*==============================================================*/
create  index ODONTOLOGO_ATM_FK on ATENCION_MEDICA (
ODO_ID
);

/*==============================================================*/
/* Index: ATM_TAM_FK                                            */
/*==============================================================*/
create  index ATM_TAM_FK on ATENCION_MEDICA (
TAM_ID
);

/*==============================================================*/
/* Index: HCD_ATM_FK                                            */
/*==============================================================*/
create  index HCD_ATM_FK on ATENCION_MEDICA (
HCD_ID
);

/*==============================================================*/
/* Table: ESPECIALIDAD                                          */
/*==============================================================*/
create table ESPECIALIDAD (
   ESP_NOMBRE           VARCHAR(50)          not null,
   ESP_AREA             VARCHAR(50)          not null,
   ESP_ACTIVO           BOOL                 null,
   ESP_FECHA_REGISTRO   DATE                 null,
   ESP_ID               INT8                 not null,
   constraint PK_ESPECIALIDAD primary key (ESP_ID)
);

/*==============================================================*/
/* Index: ESPECIALIDAD_PK                                       */
/*==============================================================*/
create unique index ESPECIALIDAD_PK on ESPECIALIDAD (
ESP_ID
);

/*==============================================================*/
/* Table: HISTORIA_CLINICA_CABECERA                             */
/*==============================================================*/
create table HISTORIA_CLINICA_CABECERA (
   HCC_ANTECEDENTES_FAMILIARES VARCHAR(500)         not null,
   HCC_ANTECEDENTES_PERSONALES VARCHAR(500)         not null,
   HCC_ENFERMEDAD_ACTUAL VARCHAR(100)         not null,
   HCC_ACTIVO           BOOL                 null,
   HCC_FECHA_REGISTRO   DATE                 null,
   PAC_ID               INT8                 not null,
   HCC_ID               INT8                 not null,
   constraint PK_HISTORIA_CLINICA_CABECERA primary key (HCC_ID)
);

/*==============================================================*/
/* Index: HISTORIA_CLINICA_CABECERA_PK                          */
/*==============================================================*/
create unique index HISTORIA_CLINICA_CABECERA_PK on HISTORIA_CLINICA_CABECERA (
HCC_ID
);

/*==============================================================*/
/* Index: PACIENTE_HCC_FK                                       */
/*==============================================================*/
create  index PACIENTE_HCC_FK on HISTORIA_CLINICA_CABECERA (
PAC_ID
);

/*==============================================================*/
/* Table: HISTORIA_CLINICA_DETALLE                              */
/*==============================================================*/
create table HISTORIA_CLINICA_DETALLE (
   HCD_EXPLORACION_FISICA VARCHAR(500)         not null,
   HCD_DIAGNOSTICO      VARCHAR(200)         not null,
   HCD_EVOLUCION        VARCHAR(200)         not null,
   HCD_EXAMENES_COMPLEMETARIOS VARCHAR(500)         not null,
   HCD_ACTIVO           BOOL                 null,
   HCD_FECHA_REGISTRO   DATE                 null,
   HCD_RECETA_MEDICA    VARCHAR(500)         not null,
   HCC_ID               INT8                 not null,
   HCD_ID               INT8                 not null,
   constraint PK_HISTORIA_CLINICA_DETALLE primary key (HCD_ID)
);

/*==============================================================*/
/* Index: HISTORIA_CLINICA_DETALLE_PK                           */
/*==============================================================*/
create unique index HISTORIA_CLINICA_DETALLE_PK on HISTORIA_CLINICA_DETALLE (
HCD_ID
);

/*==============================================================*/
/* Index: HCC_HCD_FK                                            */
/*==============================================================*/
create  index HCC_HCD_FK on HISTORIA_CLINICA_DETALLE (
HCC_ID
);

/*==============================================================*/
/* Table: ODONTOLOGO                                            */
/*==============================================================*/
create table ODONTOLOGO (
   ODO_ID               INT8                 not null,
   USU_ID               INT8                 not null,
   ODO_NOMBRES          VARCHAR(50)          not null,
   ODO_APELLIDOS        VARCHAR(50)          not null,
   ODO_DIRECCION        VARCHAR(50)          not null,
   ODO_TELEFONO         VARCHAR(15)          not null,
   ODO_EMAIL            VARCHAR(50)          not null,
   ODO_CEDULA           VARCHAR(15)          not null,
   ODO_ACTIVO           BOOL                 null,
   ODO_FECHA_REGISTRO   DATE                 null,
   constraint PK_ODONTOLOGO primary key (ODO_ID)
);

/*==============================================================*/
/* Index: ODONTOLOGO_PK                                         */
/*==============================================================*/
create unique index ODONTOLOGO_PK on ODONTOLOGO (
ODO_ID
);

/*==============================================================*/
/* Index: ODONTOLOGO_USUARIO_FK                                 */
/*==============================================================*/
create  index ODONTOLOGO_USUARIO_FK on ODONTOLOGO (
USU_ID
);

/*==============================================================*/
/* Table: ODONTOLOGO_ESPECIALIDAD                               */
/*==============================================================*/
create table ODONTOLOGO_ESPECIALIDAD (
   ODO_ID               INT8                 not null,
   ESP_ID               INT8                 not null,
   ODE_ID               INT8                 not null,
   constraint PK_ODONTOLOGO_ESPECIALIDAD primary key (ODE_ID)
);

/*==============================================================*/
/* Index: ODONTOLOGO_ESPECIALIDAD_PK                            */
/*==============================================================*/
create unique index ODONTOLOGO_ESPECIALIDAD_PK on ODONTOLOGO_ESPECIALIDAD (
ODE_ID
);

/*==============================================================*/
/* Index: ODONTOLOGO_ODE_FK                                     */
/*==============================================================*/
create  index ODONTOLOGO_ODE_FK on ODONTOLOGO_ESPECIALIDAD (
ODO_ID
);

/*==============================================================*/
/* Index: ESPECIALIDAD_ODE_FK                                   */
/*==============================================================*/
create  index ESPECIALIDAD_ODE_FK on ODONTOLOGO_ESPECIALIDAD (
ESP_ID
);

/*==============================================================*/
/* Table: PACIENTE                                              */
/*==============================================================*/
create table PACIENTE (
   PAC_CEDULA           VARCHAR(20)          not null,
   PAC_NOMBRES          VARCHAR(50)          not null,
   PAC_APELLIDOS        VARCHAR(50)          not null,
   PAC_DIRECCION        VARCHAR(50)          not null,
   PAC_FECHA_NACIMIENTO DATE                 not null,
   PAC_TELEFONO         VARCHAR(15)          not null,
   PAC_EMAIL            VARCHAR(50)          null,
   PAC_SEGURO_MEDICO    VARCHAR(25)          null,
   PAC_ACTIVO           BOOL                 null,
   PAC_FECHA_REGISTRO   DATE                 null,
   PAC_SEXO             VARCHAR(20)          not null,
   PAC_ESTADO_CIVIL     VARCHAR(20)          null,
   PAC_LUGAR_NACIMIENTO VARCHAR(20)          null,
   PAC_OCUPACION        VARCHAR(20)          null,
   PAC_ID               INT8                 not null,
   USU_ID               INT8                 not null,
   constraint PK_PACIENTE primary key (PAC_ID)
);

/*==============================================================*/
/* Index: PACIENTE_PK                                           */
/*==============================================================*/
create unique index PACIENTE_PK on PACIENTE (
PAC_ID
);

/*==============================================================*/
/* Index: PACIENTE_USUARIO_FK                                   */
/*==============================================================*/
create  index PACIENTE_USUARIO_FK on PACIENTE (
USU_ID
);

/*==============================================================*/
/* Table: TIPO_ATENCION_MEDICA                                  */
/*==============================================================*/
create table TIPO_ATENCION_MEDICA (
   TAM_ID               INT8                 not null,
   TAM_NOMBRE           VARCHAR(50)          not null,
   TAM_DURACION_HORAS   INT4                 not null,
   TAM_DURACION_MINUTOS INT4                 not null,
   TAM_ACTIVO           BOOL                 null,
   TAM_FECHA_REGISTRO   DATE                 null,
   constraint PK_TIPO_ATENCION_MEDICA primary key (TAM_ID)
);

/*==============================================================*/
/* Index: TIPO_ATENCION_MEDICA_PK                               */
/*==============================================================*/
create unique index TIPO_ATENCION_MEDICA_PK on TIPO_ATENCION_MEDICA (
TAM_ID
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO (
   USU_USER             VARCHAR(50)          not null,
   USU_PASSWORD         VARCHAR(50)          not null,
   USU_PREGUNTA_RECUPERACION VARCHAR(100)         not null,
   USU_RESPUESTA_RECUPERACION VARCHAR(100)         not null,
   USU_EMAIL            VARCHAR(50)          not null,
   USU_ACTIVO           BOOL                 null,
   USU_FECHA_REGISTRO   DATE                 null,
   USU_ID               INT8                 not null,
   constraint PK_USUARIO primary key (USU_ID)
);

/*==============================================================*/
/* Index: USUARIO_PK                                            */
/*==============================================================*/
create unique index USUARIO_PK on USUARIO (
USU_ID
);

alter table ADMINISTRADOR
   add constraint FK_ADMINIST_ADMINISTR_USUARIO foreign key (USU_ID)
      references USUARIO (USU_ID)
      on delete restrict on update restrict;

alter table AGENDA_ODONTOLOGO
   add constraint FK_AGENDA_O_ODONTOLOG_ODONTOLO foreign key (ODO_ID)
      references ODONTOLOGO (ODO_ID)
      on delete restrict on update restrict;

alter table ATENCION_MEDICA
   add constraint FK_ATENCION_ATM_TAM_TIPO_ATE foreign key (TAM_ID)
      references TIPO_ATENCION_MEDICA (TAM_ID)
      on delete restrict on update restrict;

alter table ATENCION_MEDICA
   add constraint FK_ATENCION_HCD_ATM_HISTORIA foreign key (HCD_ID)
      references HISTORIA_CLINICA_DETALLE (HCD_ID)
      on delete restrict on update restrict;

alter table ATENCION_MEDICA
   add constraint FK_ATENCION_ODONTOLOG_ODONTOLO foreign key (ODO_ID)
      references ODONTOLOGO (ODO_ID)
      on delete restrict on update restrict;

alter table ATENCION_MEDICA
   add constraint FK_ATENCION_PACIENTE__PACIENTE foreign key (PAC_ID)
      references PACIENTE (PAC_ID)
      on delete restrict on update restrict;

alter table HISTORIA_CLINICA_CABECERA
   add constraint FK_HISTORIA_PACIENTE__PACIENTE foreign key (PAC_ID)
      references PACIENTE (PAC_ID)
      on delete restrict on update restrict;

alter table HISTORIA_CLINICA_DETALLE
   add constraint FK_HISTORIA_HCC_HCD_HISTORIA foreign key (HCC_ID)
      references HISTORIA_CLINICA_CABECERA (HCC_ID)
      on delete restrict on update restrict;

alter table ODONTOLOGO
   add constraint FK_ODONTOLO_ODONTOLOG_USUARIO foreign key (USU_ID)
      references USUARIO (USU_ID)
      on delete restrict on update restrict;

alter table ODONTOLOGO_ESPECIALIDAD
   add constraint FK_ODONTOLO_ESPECIALI_ESPECIAL foreign key (ESP_ID)
      references ESPECIALIDAD (ESP_ID)
      on delete restrict on update restrict;

alter table ODONTOLOGO_ESPECIALIDAD
   add constraint FK_ODONTOLO_ODONTOLOG_ODONTOLO foreign key (ODO_ID)
      references ODONTOLOGO (ODO_ID)
      on delete restrict on update restrict;

alter table PACIENTE
   add constraint FK_PACIENTE_PACIENTE__USUARIO foreign key (USU_ID)
      references USUARIO (USU_ID)
      on delete restrict on update restrict;

