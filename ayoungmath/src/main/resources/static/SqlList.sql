create database ayoungmath;

use ayoungmath;

DROP TABLE IF EXISTS Video_List;
CREATE TABLE Video_List (
    Video_Seq INT AUTO_INCREMENT PRIMARY KEY,
    Grade INT NOT NULL,
    Title VARCHAR(100) NOT NULL,
    Video_Name VARCHAR(100) NOT NULL,
    File_Ext VARCHAR(10) NOT NULL
);

DROP TABLE IF EXISTS User_List;
create table User_List(
	User_Seq INT auto_increment primary KEY,
	UserId varchar(20) NOT NULL,
    Pass varchar(100) NOT NULL,
    UserName varchar(10) NOT NULL,
	Delete_Yn char(1) default 'Y' NOT NULL
);

insert into User_List(UserId,Pass,UserName)
values ('admin','admin','관리자'),
('taein33','taein33','권아영'),
('katonew','qwer1234','권태형')
;

