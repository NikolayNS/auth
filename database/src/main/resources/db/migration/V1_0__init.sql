create table if not exists person
(
    id           uuid         not null
        constraint person_pkey
            primary key,
    created_at   timestamp    not null,
    updated_at   timestamp    not null,
    first_name   varchar(255) not null,
    last_name    varchar(255),
    phone_number varchar(255) not null,
    email        varchar(255) not null,
    country      varchar(255)
);

create table if not exists app_user
(
    id         uuid         not null
        constraint app_user_pkey
            primary key,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    login      varchar(255) not null,
    password   varchar(255) not null,
    person_id uuid                    not null
        constraint user_person_id_fkey
            references person
);

create table if not exists role
(
    id          uuid         not null
        constraint role_pkey
            primary key,
    created_at  timestamp    not null,
    updated_at  timestamp    not null,
    description varchar(1024) not null,
    role_type   varchar(255) not null
);

create table if not exists user_role
(
    user_id uuid not null
        constraint user_role_user_id_fkey
            references app_user,
    role_id uuid not null
        constraint user_role_role_id_fkey
            references role,
    primary key (user_id, role_id)
);