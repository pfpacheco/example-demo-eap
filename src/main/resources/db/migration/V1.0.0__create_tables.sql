drop sequence
    if exists
        t_demo_seq;

drop table
    if exists
        t_demo;

create table
    if not exists t_demo(
        id bigint not null,
        name varchar(255) not null,
        description varchar(500),
        created_at timestamp not null default now(),
        updated_at timestamp not null default now(),
        constraint t_demo_pkey primary key (id),
        constraint uc_t_demo unique (name)
);

create sequence
    if not exists
        t_demo_seq
            increment 1
            start 1
            minvalue 1
            maxvalue 9223372036854775807
            cache 1;