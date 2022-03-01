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


-- < username = 'max', password = 'password'>
insert ignore into USER(username, password) values ('max', '$2a$12$s79MExz5XMA3xJjA6208oenKkTsnafRtl.0chxctQu.n.vP5soqJa');
-- < username = 'rodney' ,password ='password'>
insert ignore into USER(username, password) values ('rodney', '$2a$12$s79MExz5XMA3xJjA6208oenKkTsnafRtl.0chxctQu.n.vP5soqJa');
-- < username = 'lizzie', password = 'password'>
insert ignore into USER(username, password) values ('lizzie', '$2a$12$s79MExz5XMA3xJjA6208oenKkTsnafRtl.0chxctQu.n.vP5soqJa');
-- < username='bea', password = 'password'>
insert ignore into USER(username, password) values ('bea', '$2a$12$s79MExz5XMA3xJjA6208oenKkTsnafRtl.0chxctQu.n.vP5soqJa');


-- roles of users
-- ---------------------
insert ignore into user_roles(username, rolename) values ('max', 'seller');
insert ignore into user_roles(username, rolename) values ('rodney', 'buyer');
insert ignore into user_roles(username, rolename) values ('lizzie', 'buyer');
insert ignore into user_roles(username, rolename) values ('bea', 'seller');


-- permissions of roles
-- ---------------------
insert ignore into role_permissions(rolename, permission) values ('seller', 'products:read,write');
insert ignore into role_permissions(rolename, permission) values ('buyer', 'products:read');

-- products
-- ---------------------
insert ignore into product(username,productname,productprice,productqty) values ("max",'Samsung 21inch TV',300,30);
insert ignore into product(username,productname,productprice,productqty) values ("max",'LG 21inch TV',440,4);
insert ignore into product(username,productname,productprice,productqty) values ("max",'Yamaha Blender',320,30);
insert ignore into product(username,productname,productprice,productqty) values ("bea",'RBL Guitar',5000,20);
insert ignore into product(username,productname,productprice,productqty) values ("bea",'Home Sofa',45000,23);
