create database ayoungmath;

use ayoungmath;

DROP TABLE IF EXISTS Video_List;
CREATE TABLE Video_List (
    Video_Seq INT auto_increment primary KEY,
    Grade INT NOT NULL,
    Title varchar(100) NOT NULL,
    Video_Name varchar(100) NOT NULL,
    File_Ext varchar(10) NOT NULL
);

DROP TABLE IF EXISTS User_List;
create table User_List(
	User_Seq INT auto_increment primary KEY,
	UserId varchar(20) NOT NULL,
    Pass varchar(100) NOT NULL,
    UserName varchar(10) NOT NULL,
	Delete_Yn char(1) default 'N' NOT NULL
);

insert into User_List(UserId,Pass,UserName)
values ('admin','admin','관리자'),
('taein33','taein33','권아영'),
('katonew','qwer1234','권태형')
;

DROP TABLE IF EXISTS grade_list;
create table grade_list(
	Grade_Seq int auto_increment primary key,
	Grade_Name varchar(10) not null,
	Value int not null	
);

insert into grade_list (Grade_Name,Value)
values('초등3학년',1),('초등4학년',2),('초등5학년',3),('초등6학년',4),('중등1학년',5),('중등2학년',6),('중등3학년',7),('고등1학년',8),('고등2학년',9),('고등3학년',10)


DROP TABLE IF EXISTS section_list;
create table section_list(
	Section_Seq int auto_increment primary key,
	Section_Name varchar(100) not null,
	Section_Value int not null,
	Grade_Seq int not null
);
insert into section_list (Section_Name,Section_Value,Grade_Seq)
values
('개념쎈-1학기',1,1),
('개념쎈-2학기',2,1),
('Tesom-상반기풀이',3,1),
('Tesom-하반기풀이',4,1),
('개념쎈-1학기',5,2),
('개념쎈-2학기',6,2),
('Tesom-상반기풀이',7,2),
('Tesom-하반기풀이',8,2),
('개념쎈-1학기',9,3),
('개념쎈-2학기',10,3),
('Tesom-상반기풀이',11,3),
('Tesom-하반기풀이',12,3),
('개념쎈-1학기',13,4),
('개념쎈-2학기',14,4),
('Tesom-상반기풀이',15,4),
('Tesom-하반기풀이',16,4),
('개념쎈-1학기',17,5),
('개념쎈-2학기',18,5),
('개념유형-1학기',19,5),
('개념유형-2학기',20,5),
('개념쎈-1학기',21,6),
('개념쎈-2학기',22,6),
('개념유형-1학기',23,6),
('개념유형-2학기',24,6),
('개념쎈-1학기',25,7),
('개념쎈-2학기',26,7),
('개념유형-1학기',27,7),
('개념유형-2학기',28,7),
('선행개념-공통수학1',29,8),
('선행개념-공통수학2',30,8),
('개념유형-공통수학1',31,8),
('개념유형-공통수학2',32,8),
('교과서풀이-미래엔',33,8),
('교과서풀이-신사고',34,8),
('선행개념-수Ⅰ',35,9),
('선행개념-수Ⅱ',36,9),
('개념유형-수Ⅰ',37,9),
('개념유형-수Ⅱ',38,9),
('교과서풀이-미래엔',39,9),
('교과서풀이-신사고',40,9),
('선행개념-확통',41,10),
('선행개념-미적분',42,10),
('개념유형-확통',43,10),
('개념유형-미적분',44,10),
('교과서풀이-확통',45,10),
('교과서풀이-미적분',46,10);
