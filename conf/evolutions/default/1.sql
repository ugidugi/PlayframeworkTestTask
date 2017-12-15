# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table transaction (
  id                            integer auto_increment not null,
  transaction                   integer,
  recipient                     varchar(255),
  date                          datetime(6),
  lost_balance                  integer,
  status                        varchar(255),
  constraint pk_transaction primary key (id)
);


# --- !Downs

drop table if exists transaction;

