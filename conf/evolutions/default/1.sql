# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table artist (
  id                            bigint not null,
  artist                        varchar(255),
  genre_id                      bigint,
  venue                         varchar(255),
  seats                         integer,
  price                         double,
  constraint pk_artist primary key (id)
);
create sequence artist_seq;

create table department (
  id                            bigint not null,
  department                    varchar(255),
  constraint pk_department primary key (id)
);
create sequence department_seq;

create table employee (
  id                            bigint not null,
  employee                      varchar(255),
  department_id                 bigint,
  address                       varchar(255),
  constraint pk_employee primary key (id)
);
create sequence employee_seq;

create table genre (
  id                            bigint not null,
  genre                         varchar(255),
  constraint pk_genre primary key (id)
);
create sequence genre_seq;

create table user (
  email                         varchar(255) not null,
  role                          varchar(255),
  name                          varchar(255),
  password                      varchar(255),
  constraint pk_user primary key (email)
);

alter table artist add constraint fk_artist_genre_id foreign key (genre_id) references genre (id) on delete restrict on update restrict;
create index ix_artist_genre_id on artist (genre_id);

alter table employee add constraint fk_employee_department_id foreign key (department_id) references department (id) on delete restrict on update restrict;
create index ix_employee_department_id on employee (department_id);


# --- !Downs

alter table artist drop constraint if exists fk_artist_genre_id;
drop index if exists ix_artist_genre_id;

alter table employee drop constraint if exists fk_employee_department_id;
drop index if exists ix_employee_department_id;

drop table if exists artist;
drop sequence if exists artist_seq;

drop table if exists department;
drop sequence if exists department_seq;

drop table if exists employee;
drop sequence if exists employee_seq;

drop table if exists genre;
drop sequence if exists genre_seq;

drop table if exists user;

