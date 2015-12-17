# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table public.administrador (
  adm_id                    bigserial not null,
  adm_nombre                varchar(50) not null,
  adm_apellido              varchar(50) not null,
  adm_direccion             varchar(50) not null,
  adm_telefono              varchar(15) not null,
  adm_email                 varchar(25) not null,
  adm_activo                boolean,
  adm_fecha_registro        timestamp,
  usu_id                    bigint not null,
  constraint pk_administrador primary key (adm_id))
;

create table public.agenda_odontologo (
  age_id                    bigserial not null,
  age_dia                   smallint not null,
  age_hora_inicio           timestamp not null,
  age_hora_fin              timestamp not null,
  age_activo                boolean,
  age_fecha_registro        timestamp,
  age_dia_nombre            varchar(15) not null,
  odo_id                    bigint not null,
  constraint pk_agenda_odontologo primary key (age_id))
;

create table public.atencion_medica (
  atm_id                    bigserial not null,
  atm_fecha                 timestamp not null,
  atm_hora_inicio           timestamp not null,
  atm_hora_fin              timestamp not null,
  atm_activo                boolean,
  atm_fecha_registro        timestamp,
  hcd_id                    bigint not null,
  odo_id                    bigint not null,
  pac_id                    bigint not null,
  tam_id                    bigint not null,
  constraint pk_atencion_medica primary key (atm_id))
;

create table public.especialidad (
  esp_id                    bigserial not null,
  esp_nombre                varchar(50) not null,
  esp_area                  varchar(50) not null,
  esp_activo                boolean,
  esp_fecha_registro        timestamp,
  constraint pk_especialidad primary key (esp_id))
;

create table public.historia_clinica_cabecera (
  hcc_id                    bigserial not null,
  hcc_antecedentes_familiares varchar(500) not null,
  hcc_antecedentes_personales varchar(500) not null,
  hcc_enfermedad_actual     varchar(100) not null,
  hcc_activo                boolean,
  hcc_fecha_registro        timestamp,
  pac_id                    bigint not null,
  constraint pk_historia_clinica_cabecera primary key (hcc_id))
;

create table public.historia_clinica_detalle (
  hcd_id                    bigserial not null,
  hcd_exploracion_fisica    varchar(500) not null,
  hcd_diagnostico           varchar(200) not null,
  hcd_evolucion             varchar(200) not null,
  hcd_examenes_complementarios varchar(500) not null,
  hcd_activo                boolean,
  hcd_fecha_registro        timestamp,
  hcd_receta_medica         varchar(500) not null,
  hcc_id                    bigint not null,
  constraint pk_historia_clinica_detalle primary key (hcd_id))
;

create table public.odontologo (
  odo_id                    bigserial not null,
  odo_nombres               varchar(50) not null,
  odo_apellidos             varchar(50) not null,
  odo_direccion             varchar(50) not null,
  odo_telefono              varchar(15) not null,
  odo_email                 varchar(50) not null,
  odo_cedula                varchar(15) not null,
  odo_activo                boolean,
  odo_fecha_registro        timestamp,
  usu_id                    bigint not null,
  constraint pk_odontologo primary key (odo_id))
;

create table public.odontologo_especialidad (
  ode_id                    bigserial not null,
  esp_id                    bigint not null,
  odo_id                    bigint not null,
  constraint pk_odontologo_especialidad primary key (ode_id))
;

create table public.paciente (
  pac_id                    bigserial not null,
  pac_cedula                varchar(20) not null,
  pac_nombres               varchar(50) not null,
  pac_apellidos             varchar(50) not null,
  pac_direccion             varchar(50) not null,
  pac_fecha_nacimiento      timestamp not null,
  pac_telefono              varchar(15) not null,
  pac_email                 varchar(50),
  pac_seguro_medico         varchar(25),
  pac_activo                boolean,
  pac_fecha_registro        timestamp,
  pac_sexo                  varchar(20) not null,
  pac_estado_civil          varchar(20),
  pac_lugar_nacimiento      varchar(20),
  pac_ocupacion             varchar(20),
  usu_id                    bigint not null,
  constraint pk_paciente primary key (pac_id))
;

create table public.tipo_atencion_medica (
  tam_id                    bigserial not null,
  tam_nombre                varchar(50) not null,
  tam_duracion_horas        integer not null,
  tam_duracion_minutos      integer not null,
  tam_activo                boolean,
  tam_fecha_registro        timestamp,
  constraint pk_tipo_atencion_medica primary key (tam_id))
;

create table public.usuario (
  usu_id                    bigint not null,
  usu_user                  varchar(50) not null,
  usu_password              varchar(50) not null,
  usu_pregunta_recuperacion varchar(100) not null,
  usu_respuesta_recuperacion varchar(100) not null,
  usu_email                 varchar(50) not null,
  usu_activo                boolean,
  usu_fecha_registro        timestamp,
  constraint pk_usuario primary key (usu_id))
;

create sequence public.usuario_seq increment by 100;

alter table public.administrador add constraint fk_administrador_usuId_1 foreign key (usu_id) references public.usuario (usu_id);
create index ix_administrador_usuId_1 on public.administrador (usu_id);
alter table public.agenda_odontologo add constraint fk_agenda_odontologo_odoId_2 foreign key (odo_id) references public.odontologo (odo_id);
create index ix_agenda_odontologo_odoId_2 on public.agenda_odontologo (odo_id);
alter table public.atencion_medica add constraint fk_atencion_medica_hcdId_3 foreign key (hcd_id) references public.historia_clinica_detalle (hcd_id);
create index ix_atencion_medica_hcdId_3 on public.atencion_medica (hcd_id);
alter table public.atencion_medica add constraint fk_atencion_medica_odoId_4 foreign key (odo_id) references public.odontologo (odo_id);
create index ix_atencion_medica_odoId_4 on public.atencion_medica (odo_id);
alter table public.atencion_medica add constraint fk_atencion_medica_pacId_5 foreign key (pac_id) references public.paciente (pac_id);
create index ix_atencion_medica_pacId_5 on public.atencion_medica (pac_id);
alter table public.atencion_medica add constraint fk_atencion_medica_tamId_6 foreign key (tam_id) references public.tipo_atencion_medica (tam_id);
create index ix_atencion_medica_tamId_6 on public.atencion_medica (tam_id);
alter table public.historia_clinica_cabecera add constraint fk_historia_clinica_cabecera_p_7 foreign key (pac_id) references public.paciente (pac_id);
create index ix_historia_clinica_cabecera_p_7 on public.historia_clinica_cabecera (pac_id);
alter table public.historia_clinica_detalle add constraint fk_historia_clinica_detalle_hc_8 foreign key (hcc_id) references public.historia_clinica_cabecera (hcc_id);
create index ix_historia_clinica_detalle_hc_8 on public.historia_clinica_detalle (hcc_id);
alter table public.odontologo add constraint fk_odontologo_usuId_9 foreign key (usu_id) references public.usuario (usu_id);
create index ix_odontologo_usuId_9 on public.odontologo (usu_id);
alter table public.odontologo_especialidad add constraint fk_odontologo_especialidad_es_10 foreign key (esp_id) references public.especialidad (esp_id);
create index ix_odontologo_especialidad_es_10 on public.odontologo_especialidad (esp_id);
alter table public.odontologo_especialidad add constraint fk_odontologo_especialidad_od_11 foreign key (odo_id) references public.odontologo (odo_id);
create index ix_odontologo_especialidad_od_11 on public.odontologo_especialidad (odo_id);
alter table public.paciente add constraint fk_paciente_usuId_12 foreign key (usu_id) references public.usuario (usu_id);
create index ix_paciente_usuId_12 on public.paciente (usu_id);



# --- !Downs

drop table if exists public.administrador cascade;

drop table if exists public.agenda_odontologo cascade;

drop table if exists public.atencion_medica cascade;

drop table if exists public.especialidad cascade;

drop table if exists public.historia_clinica_cabecera cascade;

drop table if exists public.historia_clinica_detalle cascade;

drop table if exists public.odontologo cascade;

drop table if exists public.odontologo_especialidad cascade;

drop table if exists public.paciente cascade;

drop table if exists public.tipo_atencion_medica cascade;

drop table if exists public.usuario cascade;

drop sequence if exists public.usuario_seq;

