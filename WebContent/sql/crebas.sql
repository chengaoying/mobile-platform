/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-10-12 14:54:27                          */
/*==============================================================*/


drop table if exists tb_activity_prize;

drop index Index_1 on tb_prize;

drop table if exists tb_prize;

drop table if exists tb_product;

drop table if exists tb_user;

drop table if exists tb_user_prize_record;

drop table if exists tb_user_product;

drop table if exists tb_game_record;

/*==============================================================*/
/* Table: tb_activity_prize                                     */
/*==============================================================*/
create table tb_activity_prize
(
   activityid           int not null auto_increment,
   starttime            datetime not null,
   endtime              datetime not null,
   primary key (activityid)
);

/*==============================================================*/
/* Table: tb_prize                                              */
/*==============================================================*/
create table tb_prize
(
   prizeid              int not null auto_increment,
   productid            int not null,
   activityid           int not null,
   name                 varchar(20) not null,
   location             varchar(50) not null,
   price                int not null,
   primary key (prizeid)
);

/*==============================================================*/
/* Index: Index_1                                               */
/*==============================================================*/
create unique index Index_1 on tb_prize
(
   name
);

/*==============================================================*/
/* Table: tb_product                                            */
/*==============================================================*/
create table tb_product
(
   productid            int not null auto_increment,
   productname          varchar(20) not null,
   appname              varchar(20) not null,
   provider             varchar(20) not null,
   state                int not null,
   uptime               datetime,
   downtime             datetime,
   producttype          int not null,
   downloads            int,
   authorization        int not null,
   primary key (productid)
);

/*==============================================================*/
/* Table: tb_user                                               */
/*==============================================================*/
create table tb_user
(
   userid               int not null auto_increment,
   accountid            varchar(20) not null,
   name                 varchar(20) not null,
   sex                  varchar(10),
   password             varchar(20) not null,
   tel                  varchar(20),
   email                varchar(20),
   address              varchar(50),
   createtime           datetime not null,
   amount               int default 0,
   scores               int default 0,
   level                int not null default 0,
   area                 varchar(30),
   nick                 varchar(30),
   type                 varchar(20) not null,
   avatar               varchar(50),
   primary key (userid)
);

/*==============================================================*/
/* Table: tb_user_prize_record                                  */
/*==============================================================*/
create table tb_user_prize_record
(
   id                   int not null auto_increment,
   userid               int not null,
   prizeid              int not null,
   time                 datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: tb_user_product                                       */
/*==============================================================*/
create table tb_user_product
(
   id                   int not null auto_increment,
   userid               int not null,
   productid            int not null,
   authorization        int not null,
   logintime            datetime,
   logouttime           datetime,
   gametime             bigint,
   ip                   varchar(20) not null,
   primary key (id)
);

create table tb_game_record
(
	id					int not null auto_increment,
	recordindex			int not null,
	productid			int not null,
	time				datetime,
	data				varchar(1000),
	primary key (id)
)
