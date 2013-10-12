/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-10-11 11:46:36                          */
/*==============================================================*/


drop table if exists tb_acitvity_prize;

drop table if exists tb_user_prize_record;

drop table if exists tb_user_product;

drop table if exists tb_resources;

drop table if exists tb_product;

drop table if exists tb_user;

/*==============================================================*/
/* Table: tb_acitvity_prize                                     */
/*==============================================================*/
create table tb_acitvity_prize
(
   id                   int not null auto_increment,
   starttime            date not null,
   endtime              date not null,
   primary key (id)
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
   uptime               date,
   downtime             date,
   producttype          int not null,
   downloads            int,
   authorization        int not null,
   primary key (productid)
);

/*==============================================================*/
/* Table: tb_resources                                          */
/*==============================================================*/
create table tb_resources
(
   prizeid              int not null auto_increment,
   productid            int not null,
   name                 varchar(20) not null,
   type                 varchar(20) not null,
   location             varchar(30) not null,
   price                int not null,
   groupid              int,
   primary key (prizeid)
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
   createtime           date not null,
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
   time                 date not null,
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
   logintime            date,
   logouttime           date,
   gametime             bigint,
   ip                   varchar(20) not null,
   primary key (id)
);

alter table tb_resources add constraint FK_Reference_3 foreign key (productid)
      references tb_product (productid) on delete restrict on update restrict;

alter table tb_user_prize_record add constraint FK_Reference_4 foreign key (userid)
      references tb_user (userid) on delete restrict on update restrict;

alter table tb_user_prize_record add constraint FK_Reference_5 foreign key (prizeid)
      references tb_resources (prizeid) on delete restrict on update restrict;

alter table tb_user_product add constraint FK_Reference_1 foreign key (userid)
      references tb_user (userid) on delete restrict on update restrict;

alter table tb_user_product add constraint FK_Reference_2 foreign key (productid)
      references tb_product (productid) on delete restrict on update restrict;

