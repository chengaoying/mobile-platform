if object_id('[tb_user]') is null create table [tb_user](
	id int not null identity(0, 1),
	name varchar(16),
	passWord varchar(16),
	role varchar(20) not null,
	authority varchar(20) not null,
	createtime datetime ,
	logintime datetime 
	primary key(id)
);
	
if object_id('[tb_dataDictionary]') is null create table [tb_dataDictionary](
	id int not null identity(0, 1),
	name varchar(16) not null,
	value varchar(16) not null,
	primary key(id)
);

insert into tb_user(name,password,role,authority,createtime,logintime) values('ohyeah','ohyeah','admin','1','2011-12-02','2011-12-02');
insert into tb_dataDictionary(name,value) values('authority', '1');
insert into tb_dataDictionary(name,value) values('authority', '2');
insert into tb_dataDictionary(name,value) values('authority', '3');
insert into tb_dataDictionary(name,value) values('role', 'admin');
insert into tb_dataDictionary(name,value) values('role', 'worker');
insert into tb_dataDictionary(name,value) values('role', 'partner');
