CREATE SCHEMA client;

GO

create table client.users
(
    id  bigserial not null,
    login varchar(255),
    name varchar(255),
    password varchar(255),
    status varchar(255),
    surname varchar(255),
    primary key (id)
)

GO

create table client.roles
(
    id  bigserial not null,
    name varchar(255),
    primary key (id)
)

GO

create table client.profiles
(
    id  bigserial not null,
    whats_app  boolean default false,
    facebook  boolean default false,
    telegram  boolean default false,
    email  boolean default false,
    primary key (id)
)

GO

create table client.personal_data
(
    id  bigserial not null,
    whats_app varchar(255),
    facebook varchar(255),
    telegram varchar(255),
    email varchar(255),
    primary key (id)
)

GO

 create table client.users_roles (
                             user_id               int8 NOT NULL,
                             role_id               int8 NOT NULL,
                             primary key (user_id, role_id),
                             FOREIGN KEY (user_id)  REFERENCES client.users (id),
                             FOREIGN KEY (role_id)  REFERENCES client.roles (id)
)

GO

insert into client.roles (name)
values ('ADMIN'),
       ('USER')

GO

insert into client.users ( login, name, password, status, surname)
values ('test', 'test', 'test', 'ACTIVE', 'test'),
       ('2', '2', '$2y$12$IisvQWM2eDrL7GTGIvh75eoM.xKvthiFJsBg.5TAj.rCgEUaZkZeO', 'ACTIVE', '2')

GO

insert into client.users_roles (user_id, role_id)
values ('1', '2'),
 ('2', '2')



