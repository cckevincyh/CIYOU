#drop database ciyou;
create database ciyou;

use ciyou;


CREATE TABLE `admin`(
  `adminId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `adminName` varchar(20) BINARY NOT NULL UNIQUE COMMENT '登录名',
  `name` varchar(20) NOT NULL  COMMENT '真实姓名',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '联系方式',
  `isAvalible` tinyint(1) DEFAULT 1 COMMENT '状态.1: 正常,0:冻结',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='管理员用户表';


CREATE TABLE `Permission` (
  `permissionId` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parentId` int(11) DEFAULT NULL COMMENT '权限父ID',
  `permissionName` varchar(100) NOT NULL  UNIQUE COMMENT '权限名',
  `permission` varchar(100) NOT NULL UNIQUE COMMENT '权限字符串',
  `url` varchar(255) DEFAULT NULL COMMENT '资源URL',
  `type` tinyint(1) DEFAULT '1' COMMENT '权限类型 1菜单 2功能',
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='权限表';


CREATE TABLE `admin_permission` (
  `adminId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  CONSTRAINT  FOREIGN KEY (`adminId`) REFERENCES `admin` (`adminId`),
  CONSTRAINT  FOREIGN KEY (`permissionId`) REFERENCES `Permission` (`permissionId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

CREATE TABLE `Grade` (
  `gradeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gradeName` varchar(100) NOT NULL UNIQUE COMMENT '年级',
	PRIMARY KEY (`gradeId`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='年级表';

CREATE TABLE `Classes` (
  `classesId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classes` int(11) NOT NULL  COMMENT '班级',
  `gradeId` int(11)  DEFAULT NULL COMMENT '年级',
   PRIMARY KEY (`classesId`),
   unique key (`gradeId`,`classes`),
   CONSTRAINT  FOREIGN KEY (`gradeId`) REFERENCES `Grade` (`gradeId`) ON DELETE SET NULL ON  UPDATE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='班级表';



CREATE TABLE `Subject` (
  `subjectId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subjectName` varchar(100) NOT NULL UNIQUE COMMENT '科目名',
  PRIMARY KEY (`subjectId`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='课程表';



CREATE TABLE `Student`(
	`sid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `studentId` varchar(20) NOT NULL UNIQUE COMMENT '学号',
	`name` varchar(20) NOT NULL  COMMENT '姓名',
	`password` varchar(32) NOT NULL COMMENT '登录密码',
	`sex` tinyint(1) DEFAULT '1' COMMENT '性别 1:男，2：女',
	`age` int(11) DEFAULT NULL  COMMENT '年龄',
	`createTime` DATETIME NOT NULL COMMENT '创建日期',
	`mobile` varchar(11) DEFAULT NULL COMMENT '个人联系方式',
    `parentMobile` varchar(11) DEFAULT NULL COMMENT '父母联系方式',
    `email` varchar(100) DEFAULT NULL COMMENT '个人邮箱',
	`parentEmail` varchar(100) DEFAULT NULL COMMENT '父母邮箱',
    `picImg` varchar(200) DEFAULT NULL COMMENT '个人头像',
	`classesId` int(11)DEFAULT NULL COMMENT '班级ID',
    `isAvalible` tinyint(1) DEFAULT 1 COMMENT '状态.1: 正常,0:冻结',
     `lockState` INT (11) NOT NULL DEFAULT 0,
	CONSTRAINT  FOREIGN KEY (`classesId`) REFERENCES `Classes` (`classesId`) ON DELETE SET NULL ON  UPDATE CASCADE,
    PRIMARY KEY (`sid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='学生表';


CREATE TABLE `Teacher`(
	`tid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `teacherId` varchar(20) NOT NULL UNIQUE COMMENT '教师号',
	`name` varchar(20) NOT NULL  COMMENT '姓名',
	`password` varchar(32) NOT NULL COMMENT '登录密码',
	`sex` tinyint(1) DEFAULT '1' COMMENT '性别 1:男，2：女',
	`createTime` DATETIME NOT NULL COMMENT '创建日期',
	`mobile` varchar(11) DEFAULT NULL COMMENT '个人联系方式',
    `email` varchar(100) DEFAULT NULL COMMENT '个人邮箱',
    `picImg` varchar(200) DEFAULT NULL COMMENT '个人头像',
	`subjectId` int(11)DEFAULT NULL COMMENT '科目',
    `isAvalible` tinyint(1) DEFAULT 1 COMMENT '状态.1: 正常,0:冻结',
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE SET NULL ON  UPDATE CASCADE,
    PRIMARY KEY (`tid`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='教师表';


CREATE TABLE `Video`(
	`videoId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`videoName` varchar(200) NOT NULL  COMMENT '视频名称',
	`videoUrl` varchar(200) NOT NULL COMMENT '视频路径',
	`imgUrl` varchar(200) NOT NULL COMMENT '封面路径',
	`teacherId` int(11) DEFAULT NULL COMMENT '上传教师',
	`subjectId` int(11) DEFAULT NULL COMMENT '科目',
	`gradeId` int(11) DEFAULT NULL COMMENT '年级',
	`createTime` DATETIME NOT NULL COMMENT '创建日期',
	`videoType` tinyint(1) DEFAULT 1 COMMENT '状态',
	CONSTRAINT  FOREIGN KEY (`teacherId`) REFERENCES `Teacher` (`tid`) ON DELETE SET NULL ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE SET NULL ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`gradeId`) REFERENCES `Grade` (`gradeId`) ON DELETE SET NULL ON  UPDATE CASCADE,
	PRIMARY KEY (`videoId`)
);


CREATE TABLE `Roster`(
  `rid` int(11) NOT NULL AUTO_INCREMENT COMMENT '班级分配ID',
  `tid` int(11) NOT NULL  COMMENT '教师ID',
  `classesId` int(11) NOT NULL  COMMENT '班级ID',
  `subjectId` int(11) NOT NULL  COMMENT '课程ID',
    CONSTRAINT  FOREIGN KEY (`tid`) REFERENCES `Teacher` (`tid`) ON DELETE CASCADE ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE CASCADE ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`classesId`) REFERENCES `Classes` (`classesId`) ON DELETE CASCADE ON  UPDATE CASCADE,
	unique key (`classesId`,`subjectId`),
	PRIMARY KEY (`rid`)
);



-- 小测的基本信息
CREATE TABLE `Quiz`(
	`quizId` INT(11) AUTO_INCREMENT PRIMARY KEY COMMENT '小测ID',
	`quizName` VARCHAR(64) UNIQUE NOT NULL ,	-- 小测试卷名
	`subjectId` INT(11) NOT NULL,	-- 小测所属的课程
	`gradeId` INT(11) NOT NULL, -- 所属年级
	`tid` INT(11) NOT NULL  COMMENT '教师ID',
	`quizTime` INT(60) NOT NULL DEFAULT 60, -- 小测时间
	`choiceNum` INT(5) NOT NULL DEFAULT 0,	-- 选择题个数
	`judgeNum` INT(5) NOT NULL DEFAULT 0,	-- 判断题个数
	`choiceScore` INT(5) NOT NULL DEFAULT 0,	-- 选择题分数
	`judgeScore` INT(5) NOT NULL DEFAULT 0,	-- 判断题分数
	`allScore` INT(5) NOT NULL DEFAULT 0,	-- 总分
	CONSTRAINT  FOREIGN KEY (`subjectId`) REFERENCES `Subject` (`subjectId`) ON DELETE CASCADE ON  UPDATE CASCADE,
    CONSTRAINT  FOREIGN KEY (`gradeId`) REFERENCES `Grade` (`gradeId`) ON DELETE CASCADE ON  UPDATE CASCADE,
   CONSTRAINT  FOREIGN KEY (`tid`) REFERENCES `Teacher` (`tid`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 选择题基本信息
CREATE TABLE `Choice`(
	`choiceId` INT(11) AUTO_INCREMENT PRIMARY KEY,	-- 选择题编号，自增长
	`quizId` INT(11)NOT NULL,	-- 所属试卷
	`question` VARCHAR(200) NOT NULL,	-- 问题
	`optionA` VARCHAR(100) NOT NULL,
	`optionB` VARCHAR(100) NOT NULL,
	`optionC` VARCHAR(100) NOT NULL,
	`optionD` VARCHAR(100) NOT NULL,
	`answer` VARCHAR(20) NOT NULL,	-- 答案
	CONSTRAINT  FOREIGN KEY (`quizId`) REFERENCES `Quiz` (`quizId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 判断题基本信息
CREATE TABLE `Judge`(
	`judgeId` INT(11) AUTO_INCREMENT PRIMARY KEY,	-- 判断题编号，自增长
	`quizId` INT(11) NOT NULL,	-- 所属试卷
	`question` VARCHAR(200) NOT NULL,	-- 问题
	`answer` VARCHAR(20) NOT NULL,	-- 答案
	CONSTRAINT  FOREIGN KEY (`quizId`) REFERENCES `Quiz` (`quizId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 考试答案
CREATE TABLE `Answer`(
	`answerId` INT(11) AUTO_INCREMENT PRIMARY KEY,	-- 答案编号,自增长
	`sid` INT(11) NOT NULL,	-- 学号
	`quizId` INT(11) NOT NULL,	-- 考试试卷
	`question` INT(5) NOT NULL,	-- 试题号
	`questionType` INT(5) NOT NULL,	-- 类型，选择判断(1:选择题，2:判断题)
	`answer` VARCHAR(20) NOT NULL DEFAULT '',	-- 答案
	`goodAnswer` VARCHAR(20) NOT NULL,	-- 正确答案
	`score` INT(5) NOT NULL DEFAULT 0,	-- 得分
	CONSTRAINT  FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`) ON DELETE CASCADE ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`quizId`) REFERENCES `Quiz` (`quizId`) ON DELETE CASCADE ON  UPDATE CASCADE
);

-- 考试成绩
CREATE TABLE `Score`(
	`scoreId` INT(11) AUTO_INCREMENT PRIMARY KEY,	-- 考试成绩编号，自增长
	`sid` INT(11) NOT NULL,	-- 学号
	`quizId` INT(11) NOT NULL,	-- 考试试卷
	`choiceScore` INT(5) NOT NULL DEFAULT 0,	-- 选择题分数
	`judgeScore` INT(5) NOT NULL DEFAULT 0,	-- 判断题分数
	`allScore` INT(5) NOT NULL DEFAULT 0,	-- 总分
	CONSTRAINT  FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`) ON DELETE CASCADE ON  UPDATE CASCADE,
	CONSTRAINT  FOREIGN KEY (`quizId`) REFERENCES `Quiz` (`quizId`) ON DELETE CASCADE ON  UPDATE CASCADE
);


Insert INTO ADMIN VALUE(1,'admin','超级管理员','3ef7164d1f6167cb9f2658c07d3c2f0a','13888888888',1);


INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
value (1,'权限管理','view:permission','/admin/managePermission',0);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (2,1,'添加权限','add:permission','/admin/addPermission',0);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (3,1,'修改权限','update:permission','/admin/updatePermission',0);


INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (4,1,'删除权限','delete:permission','/admin/deletePermission',0);


INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (5,'管理员管理','view:admin','/admin/manageAdmin',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (6,5,'添加管理员','add:admin','/admin/addAdmin',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (7,5,'修改管理员','update:admin','/admin/updateAdmin',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (8,5,'删除管理员','delete:admin','/admin/deleteAdmin',2);


INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (9,'学生管理','view:student','/admin/manageStudent',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (10,9,'添加学生','add:student','/admin/addStudent',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (11,9,'修改学生','update:student','/admin/updateStudent',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (12,9,'删除学生','delete:student','/admin/deleteStudent',2);



INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (13,'教师管理','view:teacher','/admin/manageTeacher',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (14,13,'添加教师','add:teacher','/admin/addTeacher',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (15,13,'修改教师','update:teacher','/admin/updateTeacher',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (16,13,'删除教师','delete:teacher','/admin/deleteTeacher',2);



INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (17,'年级管理','view:grade','/admin/manageGrade',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (18,17,'添加年级','add:grade','/admin/addGrade',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (19,17,'修改年级','update:grade','/admin/updateGrade',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (20,17,'删除年级','delete:grade','/admin/deleteGrade',2);


INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (21,'班级管理','view:classes','/admin/manageClasses',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (22,21,'添加班级','add:classes','/admin/addClasses',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (23,21,'修改班级','update:classes','/admin/updateClasses',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (24,21,'删除班级','delete:classes','/admin/deleteClasses',2);


INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (25,'科目管理','view:subject','/admin/manageSubject',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (26,25,'添加科目','add:subject','/admin/addSubject',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (27,25,'修改科目','update:subject','/admin/updateSubject',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (28,25,'删除科目','delete:subject','/admin/deleteSubject',2);


INSERT INTO permission(`permissionId`,`permissionName`,`permission`,`url`,`type`)
 value (29,'视频管理','view:video','/admin/checkVideo',1);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (30,29,'通过审核','pass:video','/admin/passVideo',2);

INSERT INTO permission(`permissionId`,`parentId`,`permissionName`,`permission`,`url`,`type`)
 value (31,29,'取消审核','notPass:video','/admin/notPassVideo',2);


InSERT INTO admin_permission VALUES(1,1);
InSERT INTO admin_permission VALUES(1,2);
InSERT INTO admin_permission VALUES(1,3);
InSERT INTO admin_permission VALUES(1,4);
InSERT INTO admin_permission VALUES(1,5);
InSERT INTO admin_permission VALUES(1,6);
InSERT INTO admin_permission VALUES(1,7);
InSERT INTO admin_permission VALUES(1,8);
InSERT INTO admin_permission VALUES(1,9);
InSERT INTO admin_permission VALUES(1,10);
InSERT INTO admin_permission VALUES(1,11);
InSERT INTO admin_permission VALUES(1,12);
InSERT INTO admin_permission VALUES(1,13);
InSERT INTO admin_permission VALUES(1,14);
InSERT INTO admin_permission VALUES(1,15);
InSERT INTO admin_permission VALUES(1,16);
InSERT INTO admin_permission VALUES(1,17);
InSERT INTO admin_permission VALUES(1,18);
InSERT INTO admin_permission VALUES(1,19);
InSERT INTO admin_permission VALUES(1,20);
InSERT INTO admin_permission VALUES(1,21);
InSERT INTO admin_permission VALUES(1,22);
InSERT INTO admin_permission VALUES(1,23);
InSERT INTO admin_permission VALUES(1,24);
InSERT INTO admin_permission VALUES(1,25);
InSERT INTO admin_permission VALUES(1,26);
InSERT INTO admin_permission VALUES(1,27);
InSERT INTO admin_permission VALUES(1,28);
InSERT INTO admin_permission VALUES(1,29);
InSERT INTO admin_permission VALUES(1,30);
InSERT INTO admin_permission VALUES(1,31);



INSERT INTO grade value(1,'一年级');
INSERT INTO grade value(2,'二年级');
INSERT INTO grade value(3,'三年级');
INSERT INTO grade value(4,'四年级');
INSERT INTO grade value(5,'五年级');
INSERT INTO grade value(6,'六年级');


INSERT INTO classes value(1,1,1);
INSERT INTO classes value(2,2,1);
INSERT INTO classes value(3,3,1);
INSERT INTO classes value(4,1,2);
INSERT INTO classes value(5,2,2);
INSERT INTO classes value(6,3,2);
INSERT INTO classes value(7,1,3);
INSERT INTO classes value(8,2,3);
INSERT INTO classes value(9,3,3);
INSERT INTO classes value(10,1,4);
INSERT INTO classes value(11,2,4);
INSERT INTO classes value(12,3,4);
INSERT INTO classes value(13,1,5);
INSERT INTO classes value(14,2,5);
INSERT INTO classes value(15,3,5);
INSERT INTO classes value(16,1,6);
INSERT INTO classes value(17,2,6);
INSERT INTO classes value(18,3,6);

INSERT INTO subject value(1,'语文');
INSERT INTO subject value(2,'数学');
INSERT INTO subject value(3,'英语');
INSERT INTO subject value(4,'品德与生活');
INSERT INTO subject value(5,'科学');
INSERT INTO subject value(6,'信息技术');



insert into admin(name,adminName,password,phone) values ('程勇鹏','admin1','13ed682b7463d8f3b38f185369618fac','13888888888');
insert into admin(name,adminName,password,phone) values ('张舒伊','admin2','ed718bc83ba6a810d68e939c7b77d862','13888888888');
insert into admin(name,adminName,password,phone) values ('刘新宇','admin3','0ebd5ed4026e8f8635a99b7ba993c90e','13444444444');
insert into admin(name,adminName,password,phone) values ('白世伟','admin4','823aab22a73147212a434bb495c3158f','13555555555');
insert into admin(name,adminName,password,phone) values ('夏禹','admin5','394e8ab04c2a0b38b58a78a47fcde2d6','13999999999');
insert into admin(name,adminName,password,phone) values ('闻笑','admin6','df5c9eeee430963fb0d8f984c00d3744','13111111111');
insert into admin(name,adminName,password,phone) values ('张华剑','admin7','cf9cc2817010c2edf529de0938bb5131','13222222222');
insert into admin(name,adminName,password,phone) values ('周瑾','admin8','1c2d9a3203b0846c07e7a3f26fd3f3dc','13333333333');
insert into admin(name,adminName,password,phone) values ('何焕宇','admin9','7c2743cb5759ea56d53d6383ff71a331','13555555555');
insert into admin(name,adminName,password,phone) values ('林达清','admin10','315e4e9f75fd2d729f087b42cc974aba','13000000000');
insert into admin(name,adminName,password,phone) values ('刘逸','admin11','ee4ef6e938f00aa67f587af9680304f1','13666666666');
insert into admin(name,adminName,password,phone) values ('潘皓泽','admin12','c8a90f285833377e337a1c5f6b07389b','13777777777');
insert into admin(name,adminName,password,phone) values ('陈海鑫','admin13','20fe446631e88c896a05426420aa74cd','13698888888');
insert into admin(name,adminName,password,phone) values ('梁锦海','admin14','2ed96448d6615ee891f6453e1d7fcd44','13885688888');
insert into admin(name,adminName,password,phone) values ('高日成','admin15','21fdbf0f45fe33414fcd4ac926e35944','13468888888');
insert into admin(name,adminName,password,phone) values ('黄维','admin16','766faf04644e711d9f6d9c3f79b82267','13488888888');
insert into admin(name,adminName,password,phone) values ('田沛然','admin17','1b95bafab28f404bdf88395b52107f4d','13328888888');
insert into admin(name,adminName,password,phone) values ('江映奎','admin18','081a7b1a78357dbf9c5a72fdf7005eb2','13888884488');
insert into admin(name,adminName,password,phone) values ('杜明','admin19','c5473eed06a7fb8f6b46241ea59a7a3a','13885688888');
insert into admin(name,adminName,password,phone) values ('魏骏生','admin20','2ec91558ef13fc73b34ea87ca35abc9e','13888884288');


insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('周熙云','20180101','3ae8005939463140df41b3fa5861a6f9','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('罗亚奇','20180102','172dbd306d2d1379965b4d7ed48f1f88','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈熙','20180103','ab76acde81742775510cdfc7886b667a','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('江莹','20180104','b54e73505d21727712362c9690dba43a','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张惠敏','20180105','1e799f42be92e3b5eb30c9938ed2d021','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('赵敏君','20180106','2e91b7725bdd5823d74d4552357e4e3f','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李佳','20180107','b6062e7889dcaa787a0724244322650a','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄嘉韵','20180108','6eb5dfb708906053799da5eed6bf0e2b','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('练盈乐','20180109','4d26416591f0129473ab4e71534a25f7','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('何燕芳','20180110','04d1e4bb1b7d7ddba883e88679469095','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张惠霞','20180111','ceafc849b80dfd6fb6ac5cc164d11f89','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('彭桂芝','20180112','308369c34836267840c919307676752c','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('具鑫雨','20180113','5f0ec25723a23b4e9985160f288581eb','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈雪盈','20180114','c1c4acf48ef8bf2e5e6f01fe61c566aa','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('喻芳芳','20180115','ba32b7e2b187ca30fe116c6e839e3f3b','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('谢欣雨','20180116','99c4599e3c3560d13f3227b393d36822','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邹宝仪','20180117','261e0ded95cd2e40c293b5442ec03eaf','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李欣欣','20180118','817722d74e630afc87399c887b8a3a3a','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('钟雨瞳','20180119','642c8d925449dd04b3b03ec2e02e0320','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('魏嘉欣','20180120','936507318283c24e3fc2e58ef7bc651d','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李芷萱','20180121','ad9a3e81863c10b4cef4494e8a38824d','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('魏伟娟','20180122','9b75327f08f7bc1d4b91fde07d26861e','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邱新利','20180123','4134c1bb04da4835bee56e8c68bbe091','2','2018-3-13 14:50:56','/static/img/girl.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张雨然','20130124','b8ea21d7f2a7be64c56f0799313bf45d','1','2018-3-13 14:50:56','/static/img/boy.png','1');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈瑞翔','20180201','a7900d4a40695f7c442ce745919858a5','2','2018-3-13 14:50:56','/static/img/girl.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('赖文浩','20180202','1987d81553c00634f7fe9c5e9a6ce1e0','2','2018-3-13 14:50:56','/static/img/girl.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('何文辉','20180203','108f24b1f2004de546f466d88b69446c','2','2018-3-13 14:50:56','/static/img/girl.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('徐林峰','20180204','feb0e3d51ba6d7beb582c4a0092a32ba','2','2018-3-13 14:50:56','/static/img/girl.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王裕祥','20180205','086f16e8770f1759514e01e218a3fe3d','2','2018-3-13 14:50:56','/static/img/girl.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈晓阳','20180206','d97f1ce213479b623399d25017781787','1','2018-3-13 14:50:56','/static/img/boy.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张绮桐','20180207','21ff4a2b38aedea472299bf62239f6da','1','2018-3-13 14:50:56','/static/img/boy.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘静','20180208','50d7a2b87768ba69b50ad5fa6266d4fe','1','2018-3-13 14:50:56','/static/img/boy.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈雅菲','20180209','54c7789fa31ab89f6d44cdf2d66bf584','1','2018-3-13 14:50:56','/static/img/boy.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('练晓妍','20180210','36f2b4f38761d4916adda733e2f29778','1','2018-3-13 14:50:56','/static/img/boy.png','2');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张梦婷','20170101','828a48233d4744da3295fa482221478c','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张富鑫','20170102','1a818e5b85a5b21f659eb0b2af83be49','1','2018-3-13 14:50:56','/static/img/boy.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈逸铭','20170103','ca5e4cc944a4e49b34e6c4c278f781d9','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘家骏','20170104','9da77d6f2640b9a9a7ac611176371bc4','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张惠杰','20170105','454ecd669c9851b8284d83d0e24fa323','1','2018-3-13 14:50:56','/static/img/boy.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('叶光辉','20170106','9e707c3b27751001ad42c4391f004da7','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('赖丽婷','20170107','3bf8a5595fc1ae424c03a018a4d2992d','1','2018-3-13 14:50:56','/static/img/boy.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('梁晴','20170108','0fb41939cc75a2de58a22b3d16297b31','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘媛媛','20170109','7d659ad692863a9419a0f3d76672dc2b','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('林家馨','20170110','ce8631dd4dcdf5c06e3d21560acf253f','1','2018-3-13 14:50:56','/static/img/boy.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张子涵','20170111','18f31d5aacb5660e79536d212d2363ff','2','2018-3-13 14:50:56','/static/img/girl.png','4');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张茹婷','20160101','4474d7e9477162cba6eb04c5d64f581c','2','2018-3-13 14:50:56','/static/img/girl.png','7');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('欧文熙','20160102','f2f5fc4b62383b935af75b164b50bb86','2','2018-3-13 14:50:56','/static/img/girl.png','7');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('林文浩','20160103','a4ab06859b9ff629c55107ee676e8837','2','2018-3-13 14:50:56','/static/img/girl.png','7');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('顾启亮','20160104','56cb393fa1e55c3a17f0e845990c9e95','1','2018-3-13 14:50:56','/static/img/boy.png','7');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('许家乐','20160105','2d7cacf42c7315c1012dac7a45dea285','2','2018-3-13 14:50:56','/static/img/girl.png','7');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('赖唐鸿','20160106','82094e7c8ccb6ea2277fae83a84e97f6','2','2018-3-13 14:50:56','/static/img/girl.png','7');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('吴睿','20160201','a3c7b34c973b1fa86826e0fe9126cb5f','1','2018-3-13 14:50:56','/static/img/boy.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈杏柔','20160202','e5ebda3788cdde126cf327c0ed3c1355','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('林嘉慧','20160203','c7cc4e402d77d276de0e0ad3d2a4198c','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('周甜甜','20160204','06005d47d6084987c73e5f29684e4c2f','1','2018-3-13 14:50:56','/static/img/boy.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张家敏','20160205','605e68618ce8ce1e16bb5146f257dea6','1','2018-3-13 14:50:56','/static/img/boy.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('钟嘉欣','20160206','2acdd2f4bd3f22168799153d04102f22','1','2018-3-13 14:50:56','/static/img/boy.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('彭国涛','20160207','abc9b819a31a8536f9a152499347cc4a','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('纪鉴钊','20160208','40dd225ec277d3e77613fedf1cfffd3b','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘柳婵','20160209','367101891fe29ebb2fe330402ea43fd8','1','2018-3-13 14:50:56','/static/img/boy.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邹龙龙','20160210','dcca52825fece937599bb98bf13d1c7c','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄瑞','20160211','55556906562fa9635fccb3ac945483e1','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('伍思恬','20160212','80f8e1f445efc772f1e839d9d64dc61e','1','2018-3-13 14:50:56','/static/img/boy.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邱俊','20160213','8e9208737b9fd0c973c49cf1c1af8a3b','2','2018-3-13 14:50:56','/static/img/girl.png','8');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('江均豪','20140101','a1b401e8c76abb9fba761d36357bce7a','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('袁烜坤','20140102','d4b4194b67cb2e7363d662ae348927f6','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾文豪','20140103','ab5793d6407476e5a50ed5a90ba2db66','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('盛蓬辉','20140104','db8692d7c02ed7bc453563425eb430bf','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('孔源','20140105','80de8d4c46dca2d5909f1f1960cbcfaa','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘佳俊','20140106','c2494744a3395fdaaa37303db143d667','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('谢瑞棋','20140107','392cd32450353014d67b5af4fff93e30','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('吴贝贝','20140108','0ea56840e6cf64ae3791905f839b3196','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邬国辉','20140109','c8e132a546042d5319cbcbfb5bfb5df6','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('谢语欣','20140110','63ac6047d49b1cab63f797ce4b66e077','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王诗怡','20140111','6e29b8c1b6837fae000e40ed8e47fa49','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('吕昱寰','20140112','04809ca78fb22d4cc2f7146dadd76bc3','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('蔡承航','20140113','ffd528d6884826d04de596616b8eb1da','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王雨雯','20140114','11200dc69f884379dcbf5dff7373553d','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈睿君','20140115','878db5ea0c9f322d2f7dd8b2703156e8','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邓嘉妮','20140116','8d247594b05a3a5572614a36c05626f4','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘裕菲','20140117','49dc2bca64549afec73f91ea43295311','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('罗奕晨','20140118','32a4a1b91b65f925bd72a3ddc282f685','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('魏琦静','20140119','4ae88f53a572ca1c35300e0bd0235acb','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('廖涵','20140120','3032500de801c08210dec6e3793ca896','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('蔡嘉裕','20140121','88b3104f697792fa19b37dc6c879c208','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈秋霞','20140122','21e23a4bb2180b282e8c47c09c34308a','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘粮豪','20140123','de348d9a29bd10b089f52229a27c9e98','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王毅','20140124','a74ced44891ac0821a419f8120bac75f','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈赛明','20140125','4f688918072631aaae047c49ba9728a8','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('赖瑞键','20140126','4b8527ce96421a7c72f41e4995a2767d','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('高翊雯','20140127','acbe26b0c0c8854a4249df93c43a0f14','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('蔡如淇','20140128','0c876b5fca78024e12db97068ebd1651','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('单伟强','20140129','aaef01795ba23c58cf333b3f5a4ec72c','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄薰仪','20140130','aa2a553e487fb570b127061b74e00898','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈英华','20140131','bbe2459a49ec2e9ccc7387a1f4819359','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄勋','20140132','670eb223f759533b40d9f071c490a0eb','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘运翔','20140133','aa118b3107fd286213cf3c928cef23e0','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈冰冰','20140134','96bcdf49bd7bac6e03fe18475e2600a5','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黎健辉','20140135','485083fabcbdab24b98788df8b3aeabb','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黎健翔','20140136','8e3e95c5c1f39651aee80db58f384829','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邱海城','20140137','fba8a853a44f9147ad74120b02f02508','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('翟倍媛','20140138','8606277e0d90074f80deea6f97a806df','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李想','20140139','97a825c11ea791cadd2457f07c01a95d','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄桃宁','20140140','1d7df91e5c0ec5f53ef5e3c61141e189','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('杨子琦','20140141','6795f3a40469f7597b363b6f4e7f1b63','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('翟正龙','20140142','3a6a1293f2e8a5e4229029abe0071981','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('吴燊锋','20140143','6ccd41d5f04b7a62c00dbbe97ba07e48','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('戴文昊','20140144','e6ca7d1d4d89103eb8e6332a1521b9d7','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李丹','20140145','b5ba8c2725d23724e5baecf78d5b4efa','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王玉柱','20140146','4a28b2617797f9e714366dc49d292134','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李填旭','20140147','4f128c44788badb307cc6ca160242ffb','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('沈晓彤','20140148','1556fc15bd288228a8526026bd2f0d18','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张慧荣','20140149','976dc8cf83f8ff6e048e7588a5a701e4','2','2018-3-13 14:50:56','/static/img/girl.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('阮程','20140150','34bf20614e0f476e4bda53c92eb4bd78','1','2018-3-13 14:50:56','/static/img/boy.png','10');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾精科','20130101','ffb55446919b28966cbc079a5397fc21','2','2018-3-13 14:50:56','/static/img/girl.png','13');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('于景彬','20130102','c64e3113a62a38520ebe322c56e9b966','2','2018-3-13 14:50:56','/static/img/girl.png','13');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('郭诚勇','20130103','4766093f6b2c8ff5ddb5da35d5e0d294','1','2018-3-13 14:50:56','/static/img/boy.png','13');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾祥锐','20130104','1e9def4f67357814a555e2d7cd2a8802','1','2018-3-13 14:50:56','/static/img/boy.png','13');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾繁杰','20130105','ce63917458d54bd50e39cc1c2c5394de','1','2018-3-13 14:50:56','/static/img/boy.png','13');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('朱俊桦','20130106','bf8ef35aa6523e41da8d0b69c73ccf93','1','2018-3-13 14:50:56','/static/img/boy.png','13');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张楷','20130201','3d073471e56e52eff987cb801c4b44e9','2','2018-3-13 14:50:56','/static/img/girl.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('朱佳宏','20130202','ee7343e3733e8338e45b5881374d628c','1','2018-3-13 14:50:56','/static/img/boy.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('蔡国杨','20130203','fa08d63a9ee5613b368fb50d9d133a9e','2','2018-3-13 14:50:56','/static/img/girl.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陆苡宝','20130204','99b06cc2981ac7a73ec00bb57999d889','2','2018-3-13 14:50:56','/static/img/girl.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张智标','20130205','00fd1712150bf5470b7a55506f46bcba','1','2018-3-13 14:50:56','/static/img/boy.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('叶子馨','20130206','3138ef401b400799659627a8424cfd22','1','2018-3-13 14:50:56','/static/img/boy.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('孔柠','20130207','b04a060e81025491f1d48e6158396c2c','1','2018-3-13 14:50:56','/static/img/boy.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('马晓丽','20130208','8ed42602ef6c8b811611ab5166b5da63','2','2018-3-13 14:50:56','/static/img/girl.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邝继扬','20130209','8d686e79e8e90dd60bdcb2d2050c4bfb','2','2018-3-13 14:50:56','/static/img/girl.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('林朝晖','20130210','a83b465099f442c22722591f476a2022','2','2018-3-13 14:50:56','/static/img/girl.png','14');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('马圳','20120101','de9a9fac5f120cae0545be5005f40e84','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('康晨阳','20120102','e0dd0e0e2a9fbab1c8e4e7fbef008987','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('郭永德','20120103','1f2d2db4098cd85503591d99a87f816b','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('薛懿','20120104','92e3726279f3991af81c0dc1948b31cd','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘洋','20120105','c072b50163e9a073c3e938eeb0c40221','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('马朋辉','20120106','144acf0455f476df5be66e1ad1941a29','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘冬影','20120107','c9c4d7feb9de2734bc5b0fd4da5d07d5','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('汪权涵','20120108','e3d95fa0f8f11dd438c53f213afd23a1','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王雨洁','20120109','3a352a8bb7402518c75e15655771121d','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邓芳艳','20120110','e66feba38e087badd6ec33e91c2c10dd','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘舒静','20120111','32154e2d8fb684fb37e49553624b7f4e','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陆苡威','20120112','b1617554bc028a458473af6f6c20fe1d','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('叶兆阳','20120113','5ce58a9131b4d2010f58ca775149aab8','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王嘉铄','20120114','2e628103e45545e5826a5e5303fc97ca','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('翟正杰','20120115','9dbd3d418fd4baaf7d1796f3fb82445c','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王铭轩 ','20120116','3da8f79e73ccc43255408c32d6b837fd','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('侯咏春','20120117','6c055631ea139d599c85e3da66188632','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黎家俊','20120118','922052f62eac66aca1355e687425128b','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('骆伟越','20120119','0dba0238265ae0af75d723b565574b18','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('俞彩仪','20120120','cd0a7385112db842a3989dc5a36cb20b','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈颢桐','20120121','aa640f769c78ee4b2295216684296be1','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('顾炜琪','20120122','455024b02de6fa40a315949e3e19d718','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('符媛媛','20120123','6503e79d8ddb9bf8ed36998ff11be6b5','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('朱远达','20120124','9b6e8dcaee1199e27d1f54bd4e1983db','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李思悦','20120125','648c2801fa85172537899fdef0832dda','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张思睿','20120126','222cd71304f0d8d82440a83a4d527ac4','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('廖嘉妍','20120127','93212602fc08107d52acf5bbb3399e7a','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('肖嘉俊','20120128','21c5708ea95e219c59c9432b5811781b','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张绮思','20120129','43f63c4427c4490c01211edc1d5762d8','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('文艺霖','20120130','4b855b46492503f5ecec1fbe8cf94115','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王清','20120131','b9e0b0ccc41859cff04d3842e23a88e0','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄梓枫','20120132','bd50f06505e6f655d6ce23f62adcf75c','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邹义政','20120133','d9d7ac081c4823e586a81ca0be7608ed','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘嘉祈','20120134','97f6388de1e56b23330b9d4da7561309','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('罗嘉欣','20120135','528eaad3994b6dda43c144642e0bb60f','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈宇航','20120136','4e4c0d01efd9631b33ad5ad9ba528ab5','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('容梓桓','20120137','f7c9130cb44720b3ce1dd6b9521b07e6','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾远龙','20120138','635238f26651c0139c4fbf9bd2290ff3','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘燕楚','20120139','90fdd81d3a4275d941645c0fc4961886','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('凌曼淇','20120140','7610c39f9189f87a1bd1af10bf89bc34','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('欧阳彦','20120141','67e22ce47be8ec3582a6916a92af99af','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('文彩芝','20120142','f37b16afd6eb357e07d4c77bd62a1f20','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾庆惠','20120143','dac80326cbd0bd89b1eda51bfd1bf35e','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('林晓婷','20120144','e0a10bd897ad0338b6cda18e13860f16','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘青青','20120145','b0c58f74d69a47ee277d7f040cbf3c13','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('杨曦','20120146','94091625512d6ce23d3a499effb0d8b4','2','2018-3-13 14:50:56','/static/img/girl.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('蒋家俊','20120147','0d2ce00b3120f31faa5719a361be9c7c','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('雷炜','20120148','c42849560d74736997468829d190815e','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('谢剑泉','20120149','7777698beecb4932c9be64bd622327b4','1','2018-3-13 14:50:56','/static/img/boy.png','16');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('周勇','20120201','974c9b3eceddd86c75502f55735787f1','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('骆文芳','20120202','b3f1e9f1e4e57751883cb8ba43c24bfb','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈金枫','20120203','f94516101b0d9543ad491f1f88a70418','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张悠','20120204','0819b48e75b77695272bc744bad6e532','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('叶智巧','20120205','579ac26d954e59020b18d98e219bb671','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('朱德檀','20120206','42cb06581592bcefd9b4142b11b58e9f','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曹家宝','20120207','8a1de0e9c10854939d169d51a9d81d70','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('何家凯','20120208','6f4ba9dff16f88565eeb9e9609d462ef','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('朱辉','20120209','863f706a7a9d56a00b3eb93a66b8267b','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('唐心仪','20120210','e23fdcf0fe65fc1927dda2d5f1446d8f','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('何思怡','20120211','53096457d951bb3016042be9db8733ce','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈连博','20120212','c72c03397e26aeb1542d7fb6ccafd939','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('江诚俊','20120213','492ba1ee5159b570dc628019e08bd049','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邱嘉燕','20120214','49d2f7577f8c63886e197f0d5457ed74','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄瀞仪','20120215','bdaaa3880452fb4b800c3898fe912d19','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李健峰','20120216','953bb1c6abfcc972d4c087088763f689','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('廖哲宏','20120217','28d9f3788e6d17fe3c7adac56738d4c8','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄华华','20120218','4010ab6bfb2bd4e0d882068c6e43af51','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('蔡宇涵','20120219','d276680148d08c4073a6032df1a5f01c','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄悦','20120220','424d410f7cf70486ef919bafcb296b64','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('杨舒可','20120221','4960166e973db5f76eb765c116f08a5d','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('叶典雅','20120222','3baa121d0d1404270c0f8be1f0426c34','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘锦豪','20120223','69b55dca447a0c16989a45deb01f497d','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('曾主纯','20120224','86386e49bf07fb8c46925e4c23fe1278','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('袁淑涵','20120225','97b961038279c8d59a595eb52e6475d7','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('叶定锐','20120226','f317052f48c6eb6da4940cb720556b9a','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('湛羽佳','20120227','291b3a8e0eb0b488f7574979437faebe','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陈秀云','20120228','87451498c71518b6ab2d2aaa4910c3d6','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邓登东','20120229','94a02759e80e74fcea31b434b89094a6','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('谭幼麟','20120230','b8336e92e47f8d11a972f4f095888792','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('何靖乾','20120231','309b136116ef25207c69e5d86d026d64','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王星炎','20120232','452c858bc1802deeed4728e9e369f123','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('周清华','20120233','dae1fc6bb28c524a1c4b1b03eb985e18','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('林伟昊','20120234','7751571a32bee9de52a5156e4cd96353','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('王雨欣','20120235','105180d0da301eba7ae13cef0d460e12','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黎紫芸','20120236','f3c8352662b2f8d5a4d5e5e90caf9445','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邓宝林','20120237','b420afd9aa428806ffcabb073479823d','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张瑞琪','20120238','7ab3696d52b655ee07e5ad2c9db48303','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('邹威','20120239','9d4c6c3a7d53b1c69fa425e7d3dda596','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('朱思祁','20120240','903f19269fbb054134367ddfad5cb931','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('李宇翔','20120241','70dc6f253f74321de51fe72045e50586','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('魏俊轩','20120242','9b43fc5b06fb1ea6fbd4f1021dfaf11b','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('罗润薇','20120243','26dc8f3b058317f48a0b1eadb0dc15eb','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('张漫依','20120244','1d78f37b2b7e26ea53fe9135b5f71eef','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘正宇','20120245','2f11eb24b8e1d81f7227e2a1f9d3a007','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('彭桂祥','20120246','fa85ea79d646d3e648a6fc056c25f93d','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('刘坤','20120247','e393d82b75c463a9915543d3c30b3a1e','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('陆富豪','20120248','c52d1b9b8027cd44af1df9469ad5c161','2','2018-3-13 14:50:56','/static/img/girl.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('黄琨淞','20120249','5a173a2b80de946dc05ee3286f13edd5','1','2018-3-13 14:50:56','/static/img/boy.png','17');
insert into Student(name,studentId,password,sex,createTime,picImg,classesId) values ('许智晴','20120250','2adeb0ab0e97666407d90971a91a4ecf','1','2018-3-13 14:50:56','/static/img/boy.png','17');


insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('潘熙艺','04140721','6d6662958a323d6031297fb0a01f7692','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('余伟靖','04140826','b79369746120e2ce439dc34825f4ad7d','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('陈子健','04140607','1f9545b45b63bd58b7c9f1ce48ee936b','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('鲁守业','04141115','bac7c55dcceb100a8c577448bfefba97','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张彬','04140929','90598415523495ae7483c8dc94cbf478','2','2018-3-13 15:00:32','/static/img/t-girl.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('凌浩军','04140619','b7d15f99d8fc35d2e7763ecaa72e70cf','1','2018-3-13 15:00:32','/static/img/t-boy.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张宁','04140828','91b26d58e15ceaa2e4885fe9376cffc0','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('岳鸿','04141101','ef566c5294a768b2bdee23fe0396041b','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张华槟','04140729','fca5c8733c9148d31687f4b75714e903','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('邵伟恒','04140817','a0bae0ffca1ee0aed0e49026d83b2866','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张德述','04140827','44fa50cdbc6eb15bc3b95ab3723664d2','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('翁小松','04140926','2a844b8b01e711bb073b11d728f337f3','2','2018-3-13 15:00:32','/static/img/t-girl.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('马钦坚','04140719','dda95900cf6ed9161fc1fd245320b8ad','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('谢勇强','04140927','24366b5b7c58d41397379c7fca0ae3ff','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('陈富华','04140705','c59eb3cb40df1a625e94be255899d6ff','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('陈泽武','04140708','d554d07c9075b5aa22f37ed65d883d39','1','2018-3-13 15:00:32','/static/img/t-boy.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('唐建章','04140819','c8fb9ea5ff83e70f21072994b073f10d','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('邹浩健','04140830','65e2a6beb3a36056318a386902602c3d','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('刘国铭','04140717','f855cec33c00a9dc39b7ac4696beb65d','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('老盛浩','04140713','ec090aa4819e1c2d2c686c84dc449a07','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李志伟','04140809','afea2b8d687b928be9877249877ce6ca','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('曹继涛','04140704','f66b5206b072954e38d9604e884bea1d','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('吴敏豪','04140822','cf247442eb2fe7dffdf27068d909f4d2','1','2018-3-13 15:00:32','/static/img/t-boy.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张泽锦','04140730','6233b70be78206dd10b33d1292b25df8','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('廖道伦','04140810','922ac40458289d57da790a26b225d568','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('何展生','04140907','6742fb7eaa01a144ce10a02772a78041','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('朱嘉欣','04140802','fafac9efdc204db0315e676b4bc7c0a1','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李永恒','04140808','dcb9e5d088c96bbcebf3aa94d0d4d419','2','2018-3-13 15:00:32','/static/img/t-girl.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('胡家楠','04140710','daefcff11b56d86bee99ae61c38441ab','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('余楚文','04140825','b9d8b73ca9387beae8a50c3c1b599413','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('叶少烽','04140728','2cc03f2806f6467698b8933a4c907b95','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李蕴力','04141012','6cff47f8dfbf0ed76f4e4d877f48c878','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('陈跃','04140707','db75f01f53253c9ec55daca05ecb5b96','1','2018-3-13 15:00:32','/static/img/t-boy.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('黄福成','04140711','a006c88fc374396673665f64538b287e','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('霍健聪','04140805','e97681c54983ceaf227500ed6366ebe2','1','2018-3-13 15:00:32','/static/img/t-boy.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('牟正轩','04141016','c12bc2da2eb08d0705227e5b68504a1b','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李杰','04140714','b70c7728aa951bb04ce321e8d1eb2dfc','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李铜','04140915','74cbee1d07483dfce0e2f182d364dd6f','1','2018-3-13 15:00:32','/static/img/t-boy.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('艾思','04140605','e79c6f1b22247c23dfe69b92f055e947','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李健鑫','04140913','846d2e384cba7815765e14932f869783','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张卓','04141125','3219c308acb991100add50c5b622a044','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('符宗元','04140804','8d9cc75ad0cf63f26de8a95652a31be6','1','2018-3-13 15:00:32','/static/img/t-boy.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('王向佳','04141022','22093cd2a36106c43f4e2797d1f8ba40','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('洪文哲','04140709','1d47859ad3cc2bd3ea55ae2ae7964326','1','2018-3-13 15:00:32','/static/img/t-boy.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('陈子豪','04140905','9722ca265b11b48426a023f4a72788c9','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李顺铨','04140715','905d6a410f0e1195cd73ca65a098e063','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('欧晨阳','04141017','0b66a115a417353dcd14de3dc5286567','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('王仲达','04141023','7af445a4cdf784629be14ccefdea2d8c','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('林国滨','04140811','72693d9bcc8d90a4dfcd7a26f660deeb','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('林文献','04140812','b2e2969c304c99d109ada372885abb6d','2','2018-3-13 15:00:32','/static/img/t-girl.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('刘前欢','04140813','58cd599ddcdda364ebdb680bc5926d90','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('周明昊','04140630','5b06999c2359173ac35f048bfffc4fa7','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('曾亚龙','04141123','aa91a6d246be209cd93c3b581720eb14','2','2018-3-13 15:00:32','/static/img/t-girl.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('王玉伟','04140604','bd26ad67962b5b941d2ab35fbab9b2e5','1','2018-3-13 15:00:32','/static/img/t-boy.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('林义杰','04140618','aedd30abb411710163e04240792621af','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李晓明','04140616','89f19ec9f96312ac13acda357795395e','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('范昌江','04141107','227f08f9e91a5c122c3569103c7a57db','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('马云龙','04141117','0e5d633d535b4a6bd4039bd56c6e6292','1','2018-3-13 15:00:32','/static/img/t-boy.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('刘永兴','04140919','14269c8815cb95b066ad19af1d14df2e','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('谢锋','04140823','4ccf10493df961d9b607d861336948f4','1','2018-3-13 15:00:32','/static/img/t-boy.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('廖振宇','04140617','65aca136fb972525bb0dc16e739d389d','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('曾迪波','04140928','e33471d3aabf556cf9d1c066c5d36527','2','2018-3-13 15:00:32','/static/img/t-girl.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('吕文啟','04140718','e03e931923909c88939126a97c298e08','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('王瑞佳','04140723','7786ed8ab548daf57f5f553cbfcb01c3','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('梁晓昀','04140917','10b2cd89e8a6c7976720cdd198d3caf5','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('秦钰博','04141119','d6775fae7c1789c3a12e4dac255dd752','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('马旭灶','04140720','fdd429d16c03ba4ac17f6240d1465b2e','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('陈响育','04140803','cb3dfd628a2332a8fd83135b43277b24','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('梁仙鹏','04141013','f4aaa42dafb23ae6e15a69812c1cc333','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('王欢','04141021','d3d23cd617d7d197ccb14d7d6d967579','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('莫善贻','04140816','f961e3c4bd3b93b5c47fd1c40333fae7','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('邱奕琪','04141018','29172557f774aeb86beecdeb675ae1ee','2','2018-3-13 15:00:32','/static/img/t-girl.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('郝世缘','04120722','8a85b60fb01c9d81b66ac4d83a5d24a4','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李文博','04141104','7ff5ffcf96ee9ea1ccd5d92dccaad35c','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('郭旭','04141005','0b3ae6bee0f68d360fa2e754cc9f0aec','1','2018-3-13 15:00:32','/static/img/t-boy.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('弋泽铭','04141231','2418430fe8f6ddcbde91efac6ce9c3c2','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('林俊宇','04141325','91b39ed3e7a1f89a3d673dfb4ce54376','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('钟颖彤','04141206','7601db361d8851bfd02c0438081839f6','2','2018-3-13 15:00:32','/static/img/t-girl.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('张杨童','04141205','d9a65132491ef594cc342fa71c247dcc','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('李达棋','04141217','cb042f2ead8ab05c8d4877a61d31ea95','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('叶楚翘','04141203','b7399d1ffaef82260767edb741132c53','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('谢志聪','04141227','144ab6ecd298ab6d4706d669ed839027','2','2018-3-13 15:00:32','/static/img/t-girl.png','4');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('方泽勋','04141315','b163134677f84b9ece961ca007ca549c','1','2018-3-13 15:00:32','/static/img/t-boy.png','5');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('杨扬','04141335','1dbefda8a5f666cdbce947e3905a55a7','1','2018-3-13 15:00:32','/static/img/t-boy.png','6');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('湛燕蔓','04141204','d8ac2864e0d162573b15dd58a6ce1546','1','2018-3-13 15:00:32','/static/img/t-boy.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('宋珂','04141201','b4f238064d2369bd0efc552ec6a7c42a','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('王梓阳','04141225','013763b88f9330f602d33992597869b5','2','2018-3-13 15:00:32','/static/img/t-girl.png','1');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('叶春华','04141228','6bbebf0d83a377ebcc66ec6cffb43a0c','2','2018-3-13 15:00:32','/static/img/t-girl.png','2');
insert into Teacher(name,teacherId,password,sex,createTime,picImg,subjectId) values ('黄灼杰','04141215','4286ceda519520515136e0f8b2ef293b','1','2018-3-13 15:00:32','/static/img/t-boy.png','3');





