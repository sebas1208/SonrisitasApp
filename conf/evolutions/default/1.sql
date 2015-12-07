# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table ODONTOLOGO (
  odo_id                    varchar(255) not null,
  odo_cedula                varchar(20),
  odo_nombres               varchar(50),
  odo_apellidos             varchar(50),
  odo_direccion             varchar(100),
  odo_telefono              varchar(20),
  odo_email                 varchar(50),
  constraint pk_ODONTOLOGO primary key (odo_id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists ODONTOLOGO;

SET REFERENTIAL_INTEGRITY TRUE;

