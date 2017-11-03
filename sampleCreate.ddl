create table AuthUser (id varchar(255) not null, hashPassword varchar(255), token varchar(255) for bit data, primary key (id))
create table Item (id bigint not null, name varchar(255) not null, price double not null, stock integer not null, ORDER_ID bigint, primary key (id))
create table ServiceOrder (id bigint not null, user_id varchar(255) for bit data, primary key (id))
create table ServiceUser (id varchar(255) for bit data not null, adress varchar(255), city varchar(255), country varchar(255), firstName varchar(255) not null, identifiant varchar(255) not null, lastName varchar(255) not null, zip integer, order_id bigint, primary key (id))
alter table ServiceUser add constraint UK_kk4g5o9xb96v7yy4hhru9nhbv unique (identifiant)
alter table Item add constraint FKpqmh83s4b2txgjryo8wad5mhv foreign key (ORDER_ID) references ServiceOrder
alter table ServiceOrder add constraint FKh6o59pr3f1kx73mp35s1qe15m foreign key (user_id) references ServiceUser
alter table ServiceUser add constraint FK1pxktfcl7tnw920t239s60hqk foreign key (order_id) references ServiceOrder
