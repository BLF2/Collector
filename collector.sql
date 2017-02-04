CREATE DATABASE collector DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use collector;

create table UserInfo(
userId varchar(100) primary key,
userNum varchar(20) not null,
userPswd varchar(20),
userPhone varchar(20),
userGrade varchar(30) not null,
userRoleId varchar(100) not null,
userNote varchar(512)
);

create table ClassInfo(
classId varchar(100) primary key,
majorName varchar(30) not null,
classGrade varchar(10) not null,
classNum varchar(10) not null,
monitorId varchar(100) not null,
classNote varchar(512)
);

create table UserRoleInfo(
roleId varchar(100) primary key,
roleName varchar(30) not null,
roleRule varchar(512),
roleNote varchar(512)
);
