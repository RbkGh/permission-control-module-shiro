drop table if exists USER;

create table USER (
                      id bigint primary key auto_increment,
                      username varchar(100) not null unique,
                      password varchar(100) not null
);

drop table if exists user_roles;

create table user_roles (
                            id bigint primary key auto_increment,
                            username varchar(100) not null,
                            rolename varchar(100) not null
);

drop table if exists role_permissions;

create table role_permissions (
                                  id bigint primary key auto_increment,
                                  rolename varchar(100) not null,
                                  permission varchar(100) not null
);

drop table if exists product;

create table product (
                         id bigint primary key auto_increment,
                         username varchar(100) not null,
                         productname varchar(100) not null,
                         productprice bigint not null,
                         productqty bigint not null

);
