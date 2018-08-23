/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/8/23 11:21:45                           */
/*==============================================================*/


drop table if exists us_t_question;

drop index IX_ACTIVITY_QUETION_ITEM_QUETION_ID on us_t_question_item;

drop table if exists us_t_question_item;

drop table if exists us_t_user;

drop index IX_USER_ACITVITY_ANSWER_ID on us_t_user_answer;

drop table if exists us_t_user_answer;

/*==============================================================*/
/* Table: us_t_question                                         */
/*==============================================================*/
create table us_t_question
(
   id                   bigint(20) not null,
   question_group       char(1) not null comment '��Ŀ���:0-ѧ����/1-֧����/2-ָ��Ա��',
   question_type        char(1) not null comment '��Ŀ����:0-ѡ����/1-�����',
   question_content     varchar(1024) not null comment '��Ŀ����',
   question_sort        int not null comment '����',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   create_user          bigint comment '������',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   last_update_user     bigint comment '������',
   is_delete            char(1) not null default '0' comment '�Ƿ�ɾ��',
   version              int not null default 0 comment '�汾��',
   memo                 varchar(255) comment '��ע',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_question comment '����';

/*==============================================================*/
/* Table: us_t_question_item                                    */
/*==============================================================*/
create table us_t_question_item
(
   id                   bigint(20) not null,
   question_id          bigint(20) not null comment '���ID',
   item_no              varchar(10) not null comment '���',
   item_content         varchar(1024) not null comment '����',
   item_sort            int not null comment '����',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   create_user          bigint comment '������',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   last_update_user     bigint comment '������',
   is_delete            char(1) not null default '0' comment '�Ƿ�ɾ��',
   version              int not null default 0 comment '�汾��',
   memo                 varchar(255) comment '��ע',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_question_item comment '���Ŀѡ���';

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
   user_type            char(1) not null comment '�û�����:0-ѧ��/1-֧�����/2-����Ա',
   user_name            varchar(50) not null comment '�û�����',
   password             varchar(50) comment '�û�����',
   class_name           varchar(50) comment '�༶',
   user_no              varchar(50) comment 'ѧ��',
   team_name            varchar(50) comment '����֧��',
   team_leader          varchar(50) comment '֧�����',
   teacher              varchar(50) comment '����Ա',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   create_user          bigint comment '������',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   last_update_user     bigint comment '������',
   is_delete            char(1) not null default '0' comment '�Ƿ�ɾ��
            0-δɾ��/1-��ɾ��',
   version              int not null default 0 comment '�汾��',
   memo                 varchar(255) comment '��ע',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_user comment '�û���';

/*==============================================================*/
/* Table: us_t_user_answer                                      */
/*==============================================================*/
create table us_t_user_answer
(
   id                   bigint(20) not null,
   question_id          bigint(20) not null comment '���ID',
   user_id              bigint(20) not null comment '�û�ID',
   dest_user_id         bigint(20) comment '�����û�ID',
   answer               varchar(255) not null comment '��',
   create_date          datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   create_user          bigint comment '������',
   last_update_date     datetime not null default CURRENT_TIMESTAMP comment '����ʱ��',
   last_update_user     bigint comment '������',
   is_delete            char(1) not null default '0' comment '�Ƿ�ɾ��',
   version              int not null default 0 comment '�汾��',
   memo                 varchar(255) comment '��ע',
   primary key (id)
)
ENGINE = InnoDB
CHARSET = utf8
COLLATE = utf8_general_ci;

alter table us_t_user_answer comment '�û����ֱ�';

/*==============================================================*/
/* Index: IX_USER_ACITVITY_ANSWER_ID                            */
/*==============================================================*/
create index IX_USER_ACITVITY_ANSWER_ID on us_t_user_answer
(
   question_id,
   user_id,
   dest_user_id
);

