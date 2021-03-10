create table course
(
    id          int auto_increment
        primary key,
    start_date  date         not null,
    title       varchar(100) not null,
    description text         not null,
    constraint title
        unique (title)
);

create table day_of_week
(
    id          int auto_increment,
    day_of_week varchar(20) null,
    constraint day_of_week
        unique (day_of_week),
    constraint id
        unique (id)
);

alter table day_of_week
    add primary key (id);

create table schedule
(
    id     int auto_increment
        primary key,
    time   time null,
    day_id int  null,
    constraint schedule_ibfk_1
        foreign key (day_id) references day_of_week (id)
);

create table courses_schedule
(
    schedule_id int null,
    course_id   int null,
    constraint courses_schedule_ibfk_1
        foreign key (schedule_id) references schedule (id)
            on update cascade on delete cascade,
    constraint courses_schedule_ibfk_2
        foreign key (course_id) references course (id)
            on update cascade on delete cascade
);

create index course_id
    on courses_schedule (course_id);

create index schedule_id
    on courses_schedule (schedule_id);

create index day_id
    on schedule (day_id);

create table task
(
    id          int auto_increment
        primary key,
    title       varchar(100) not null,
    description text         not null,
    deadline    datetime     null,
    course_id   int          not null,
    constraint title
        unique (title),
    constraint task_ibfk_1
        foreign key (course_id) references course (id)
            on update cascade on delete cascade
);

create index course_id
    on task (course_id);

create table task_answer_status
(
    id     int auto_increment
        primary key,
    status varchar(20) not null,
    constraint status
        unique (status)
);

create table user_role
(
    id   int auto_increment
        primary key,
    role varchar(20) not null,
    constraint role
        unique (role)
);

create table app_user
(
    id          int auto_increment
        primary key,
    login       varchar(20)  not null,
    password    varchar(255) not null,
    first_name  varchar(20)  not null,
    second_name varchar(20)  not null,
    email       varchar(40)  not null,
    is_banned   tinyint(1)   not null,
    role_id     int          not null,
    constraint email
        unique (email),
    constraint login
        unique (login),
    constraint app_user_ibfk_1
        foreign key (role_id) references user_role (id)
            on update cascade
);

create index role_id
    on app_user (role_id);

create table task_answer
(
    id             int auto_increment
        primary key,
    answer         text null,
    comment        text null,
    task_status_id int  null,
    user_id        int  not null,
    task_id        int  null,
    constraint task_answer_ibfk_1
        foreign key (user_id) references app_user (id)
            on update cascade on delete cascade,
    constraint task_answer_ibfk_2
        foreign key (task_id) references task (id)
            on update cascade on delete set null,
    constraint task_answer_ibfk_3
        foreign key (task_status_id) references task_answer_status (id)
            on update cascade
);

create index task_id
    on task_answer (task_id);

create index task_status_id
    on task_answer (task_status_id);

create index user_id
    on task_answer (user_id);

create table users_courses
(
    user_id      int null,
    course_id    int null,
    user_role_id int null,
    constraint users_courses_ibfk_1
        foreign key (user_id) references app_user (id)
            on update cascade on delete cascade,
    constraint users_courses_ibfk_2
        foreign key (course_id) references course (id)
            on update cascade on delete cascade,
    constraint users_courses_ibfk_3
        foreign key (user_role_id) references user_role (id)
);

create index course_id
    on users_courses (course_id);

create index user_id
    on users_courses (user_id);

create index user_role_id
    on users_courses (user_role_id);

