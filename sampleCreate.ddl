create table AuthUser (id varchar(255) not null, hashPassword varchar(255), token varchar(255), serviceUser_id bigint, primary key (id))
create table hibernate_sequence (next_val bigint)
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
insert into hibernate_sequence values ( 1 )
create table Item (code bigint not null, name varchar(255), price double not null, stock integer not null, primary key (code))
create table OrderLine (orderLineId bigint not null, quantityItem integer not null, item_code bigint, order_orderId bigint, primary key (orderLineId))
create table ServiceOrder (orderId bigint not null, user_id bigint, primary key (orderId))
create table ServiceUser (id bigint not null, adress varchar(255), city varchar(255), firstName varchar(255) not null, identifiant varchar(255) not null, lastName varchar(255) not null, zip integer, order_orderId bigint, primary key (id))
alter table ServiceUser add constraint UK_kk4g5o9xb96v7yy4hhru9nhbv unique (identifiant)
alter table AuthUser add constraint FKh8yl1dl7kovfdvl7cg04f9wsu foreign key (serviceUser_id) references ServiceUser
alter table OrderLine add constraint FKkxdmp90fp1etbip9r0g905j32 foreign key (item_code) references Item
alter table OrderLine add constraint FKb125mp4i5a65skuirkvq8uy42 foreign key (order_orderId) references ServiceOrder
alter table ServiceOrder add constraint FKh6o59pr3f1kx73mp35s1qe15m foreign key (user_id) references ServiceUser
alter table ServiceUser add constraint FK7npr8721077guh6s7y7rj82k8 foreign key (order_orderId) references ServiceOrder
