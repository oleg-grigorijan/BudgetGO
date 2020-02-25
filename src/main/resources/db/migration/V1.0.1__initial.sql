create table categories
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table currencies
(
    id       bigint auto_increment
        primary key,
    iso_code varchar(3)   not null,
    name     varchar(255) not null
);

create table operations_key_sequence
(
    storage_id        bigint not null
        primary key,
    next_operation_id bigint null
);

create table storages
(
    id              bigint auto_increment
        primary key,
    balance         bigint       not null,
    description     varchar(255) not null,
    initial_balance bigint       not null,
    name            varchar(255) not null,
    currency_id     bigint       not null,
    constraint FK_storages_currency_id__currencies_id
        foreign key (currency_id) references currencies (id)
);

create table users
(
    id               bigint auto_increment
        primary key,
    email            varchar(255) not null,
    is_admin         bit          not null,
    is_email_public  bit          not null,
    login            varchar(255) not null,
    name             varchar(255) not null,
    password_hash    varchar(255) not null,
    surname          varchar(255) not null,
    main_currency_id bigint       not null,
    constraint UK_users_email
        unique (email),
    constraint UK_users_login
        unique (login),
    constraint FK_users_main_currency_id__currencies_id
        foreign key (main_currency_id) references currencies (id)
);

create table operations
(
    operation_id   bigint       not null,
    storage_id     bigint       not null,
    date           date         not null,
    date_created   date         not null,
    date_modified  date         not null,
    description    varchar(255) not null,
    money_delta    bigint       not null,
    category_id    bigint       not null,
    creator_id     bigint       not null,
    last_editor_id bigint       not null,
    primary key (operation_id, storage_id),
    constraint FK_operations_creator_id__users_id
        foreign key (creator_id) references users (id),
    constraint FK_operations_editor_id__users_id
        foreign key (last_editor_id) references users (id),
    constraint FK_operations_storage_id__storages_id
        foreign key (storage_id) references storages (id),
    constraint FK_operations_category_id__categories_id
        foreign key (category_id) references categories (id)
);

create table storages_relations
(
    storage_id                     bigint       not null,
    user_id                        bigint       not null,
    is_included_in_user_statistics bit          not null,
    is_invitation                  bit          not null,
    user_role                      varchar(255) not null,
    inviter_id                     bigint       not null,
    primary key (storage_id, user_id),
    constraint FK_storages_relations_user_id__users_id
        foreign key (user_id) references users (id),
    constraint FK_storages_relations_inviter_id__users_id
        foreign key (inviter_id) references users (id),
    constraint FK_storages_relations_storage_id__storages_id
        foreign key (storage_id) references storages (id)
);

create table user_categories
(
    category_id          bigint not null,
    user_id              bigint not null,
    is_used_for_incomes  bit    not null,
    is_used_for_outcomes bit    not null,
    primary key (category_id, user_id),
    constraint FK_user_categories_category_id__categories_id
        foreign key (category_id) references categories (id),
    constraint FK_user_categories_user_id__users_id
        foreign key (user_id) references users (id)
);

