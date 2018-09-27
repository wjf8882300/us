/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/9/2 0:17:54                             */
/*==============================================================*/


drop table if exists com_t_param;

drop table if exists us_t_question;

drop index IX_ACTIVITY_QUETION_ITEM_QUETION_ID on us_t_question_item;

drop table if exists us_t_question_item;

drop table if exists us_t_user;

drop index IX_USER_ACITVITY_ANSWER_ID on us_t_user_answer;

drop table if exists us_t_user_answer;

drop index IX_USER_ATTACHMENT_USER_ID on us_t_user_attachment;

drop table if exists us_t_user_attachment;

drop index IX_USER_LOGIN_USER_ID on us_t_user_login;

drop table if exists us_t_user_login;

/*==============================================================*/
/* Table: com_t_param                                           */
/*==============================================================*/
create table com_t_param
(
   id                   bigint(20) not null,
   parameter_type       varchar(50) comment '参数类型',
   parameter_name       varchar(50) not null comment '参数名',
   parameter_value      varchar(500) not null comment '参数值',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除
            0-未删除/1-已删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table com_t_param comment '基础参数表';

/*==============================================================*/
/* Table: us_t_question                                         */
/*==============================================================*/
create table us_t_question
(
   id                   bigint(20) not null,
   question_group       char(1) not null comment '题目类别:0-学生题/1-支部题/2-指导员题',
   question_type        char(1) not null comment '题目类型:0-选择题/1-填空题',
   question_content     varchar(1024) not null comment '题目内容',
   question_score       int comment '题目分值',
   question_desc        varchar(1024) comment '题目描述',
   question_sort        int not null comment '排序',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_question comment '题库表';

/*==============================================================*/
/* Table: us_t_question_item                                    */
/*==============================================================*/
create table us_t_question_item
(
   id                   bigint(20) not null,
   question_id          bigint(20) not null comment '题库ID',
   item_no              varchar(10) not null comment '序号',
   item_content         varchar(1024) not null comment '内容',
   item_sort            int not null comment '排序',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_question_item comment '活动题目选项表';

/*==============================================================*/
/* Index: IX_ACTIVITY_QUETION_ITEM_QUETION_ID                   */
/*==============================================================*/
create index IX_ACTIVITY_QUETION_ITEM_QUETION_ID on us_t_question_item
(
   question_id
);

/*==============================================================*/
/* Table: us_t_user                                             */
/*==============================================================*/
create table us_t_user
(
   id                   bigint(20) not null,
   user_type            char(1) not null comment '用户类型:0-学生/1-支部书记/2-辅导员',
   user_name            varchar(50) not null comment '用户名称',
   password             varchar(50) comment '用户密码',
   class_name           varchar(50) comment '班级',
   user_no              varchar(50) comment '学号',
   team_name            varchar(50) comment '所在支部',
   team_leader          varchar(50) comment '支部书记',
   team_leader_no       varchar(50) comment 'team_leader_no',
   team_leader_id       bigint(20) comment '支部书记ID',
   teacher              varchar(50) comment '辅导员',
   teacher_no           varchar(50) comment '辅导元工号',
   teacher_id           bigint(20) comment '辅导员ID',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除
            0-未删除/1-已删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_user comment '用户表';

/*==============================================================*/
/* Table: us_t_user_answer                                      */
/*==============================================================*/
create table us_t_user_answer
(
   id                   bigint(20) not null,
   question_id          bigint(20) not null comment '题库ID',
   user_id              bigint(20) not null comment '用户ID',
   dest_user_id         bigint(20) comment '评价用户ID',
   answer               varchar(255) not null comment '答案',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_user_answer comment '用户评分表';

/*==============================================================*/
/* Index: IX_USER_ACITVITY_ANSWER_ID                            */
/*==============================================================*/
create index IX_USER_ACITVITY_ANSWER_ID on us_t_user_answer
(
   question_id,
   user_id,
   dest_user_id
);

/*==============================================================*/
/* Table: us_t_user_attachment                                  */
/*==============================================================*/
create table us_t_user_attachment
(
   id                   bigint(20) not null,
   user_id              bigint(20) not null comment '用户ID',
   attachement_name     varchar(255) comment '附件名称',
   attachement_type     char(1) comment '附件类型',
   attachement_path     varchar(500) not null comment '附件路径',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除
            0-未删除/1-已删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_user_attachment comment '用户附件表';

/*==============================================================*/
/* Index: IX_USER_ATTACHMENT_USER_ID                            */
/*==============================================================*/
create index IX_USER_ATTACHMENT_USER_ID on us_t_user_attachment
(
   user_id,
   attachement_path
);

/*==============================================================*/
/* Table: us_t_user_login                                       */
/*==============================================================*/
create table us_t_user_login
(
   id                   bigint(20) not null,
   user_id              bigint(20) not null comment '用户ID',
   open_id              varchar(50) not null comment '用户名称',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '创建时间',
   create_user          bigint comment '创建人',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '更新时间',
   last_update_user     bigint comment '更新人',
   is_delete            char(1) not null default '0' comment '是否删除
            0-未删除/1-已删除',
   version              int not null default 0 comment '版本号',
   memo                 varchar(255) comment '备注',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_user_login comment '用户登录表';

/*==============================================================*/
/* Index: IX_USER_LOGIN_USER_ID                                 */
/*==============================================================*/
create index IX_USER_LOGIN_USER_ID on us_t_user_login
(
   user_id,
   open_id
);

