create database student;
use student;
create table stu(
stuId varchar(20),
stuName varchar(20),
stuSex varchar(20),
stuAge varchar(20),
stuJG varchar(20),
stuLX varchar(20),
stuBJ varchar(20)
);
drop table stu;

create table stu(
                    stuName varchar(20),
                    eamil varchar(20),
                    phone varchar(20),
                    sex varchar(10),
                    tag varchar(20)
);
alter table stu change eamil email varchar(20);

insert into stu(stuName, email, phone, sex, tag)
values("kennys","23782738@qq.com","12345678","男","家人");
insert into stu(stuName, email, phone, sex, tag)
values("kennys","23782738@qq.com","12345678","男","朋友");
insert into stu(stuName, email, phone, sex, tag)
values("ken","123com","1238","女","朋友");
insert into stu(stuName, email, phone, sex, tag)
values("陈","123com","1238","女","朋友");
show variables like '%char%';
select * from stu;
alter table stu add primary key (stuName);
alter table stu drop primary key ;
alter table stu add id INT AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE stu DROP COLUMN id;
insert into stu(stuName,email,phone,sex,tag)
values("陈w","123wcom","123w8","女","朋友");


