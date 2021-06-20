CREATE DATABASE learners_academy;
show databases;

use learners_academy;

select * from class_table;
select * from students_table;
select * from years_table;
select * from teachers_table;
select * from subjects_table;
select * from assignFor_Subjects;
select * from `assignments`;



DROP TABLE IF EXISTS `admin_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`admin_table` (
	`ID` int(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `userName` VARCHAR(30) NOT NULL,
    `userSurname` VARCHAR(30) NOT NULL,
	`userId` VARCHAR(30) NOT NULL,
	`userPassword` VARCHAR(10) NOT NULL,
	`date_added` DATE NOT NULL,
    CONSTRAINT userId_unique UNIQUE (`userId`)
    )ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `admin_table` WRITE;
/*!40000 ALTER TABLE `admin_table` DISABLE KEYS */;
insert into `admin_table` values
(1,"Julia","Roberts","admin1","pass1",now()),
(2,"Maria","Maradonna","admin2","pass2",now());
/*!40000 ALTER TABLE `admin_table` ENABLE KEYS */;
UNLOCK TABLES;

select * from `admin_table`;

DROP TABLE IF EXISTS `students_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students_table` (
	`ID` bigint(20) NOT NULL AUTO_INCREMENT,
	`studentId` bigint(30) NOT NULL,
	`nationalId` VARCHAR(16) NOT NULL,
    `studentName` VARCHAR(30) NOT NULL,
    `studentSurname` VARCHAR(30) NOT NULL,
    `studentGender` VARCHAR(1) NOT NULL,
	`birthdate` DATE NOT NULL,
	`entryYear` VARCHAR(4) NOT NULL,
    `classId` bigint(20) NOT NULL,
    PRIMARY KEY (`ID`),
	CONSTRAINT studentId_unique UNIQUE (studentId)
    )ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `students_table` WRITE;
/*!40000 ALTER TABLE `students_table` DISABLE KEYS */;
insert into `students_table` values
(1,441920,"E1511157289","John","Smith","B","2015-11-25",2021,4),
(2,481141,"A1512579564","Ann","Johnson","G","2015-12-23",2021,3),
(3,411762,"D1509817987","Sandra","Williams","G","2015-09-06",2021,2),
(4,431985,"F1508459861","Ivan","Brown","B","2015-08-21",2021,2),
(5,529074,"G1604179578","Ann","Jones","G","2016-04-29",2021,2),
(6,513873,"T1408126578","John","Garcia","B","2014-08-14",2020,6),
(7,667238,"T1309915486","Mike","Miller","B","2013-09-13",2019,8),
(8,114769,"E1406817985","Jack","Davis","B","2014-06-10",2020,9),
(9,167987,"E1312817457","Sam","Rodriguez","G","2013-12-21",2021,10),
(10,131566,"B1409477987","Morris","Martinez","B","2014-09-21",2021,11),
(11,813769,"A1406517987","David","Hernandez","B","2014-06-09",2019,12),
(12,243587,"B1605851987","Clara","Lopez","G","2016-05-27",2021,4),
(13,109812,"K1209815187","Lyn","Gonzalez","G","2012-09-13",2018,11),
(14,113485,"F1304817517","John","Wilson","B","2013-04-03",2018,11),
(15,124192,"G1110817951","Sam","Anderson","G","2011-10-18",2017,15),
(16,134192,"L1201677987","James","Thomas","B","2012-01-09",2017,16),
(17,184087,"L1504867987","Mary","Taylor","G","2015-04-03",2020,7),
(18,182789,"H1212816787","Robert","Moore","B","2012-12-01",2018,13),
(19,181595,"H1305817677","Patricia","Jackson","G","2013-05-19",2018,12),
(20,189856,"Z1111817967","Jennifer","Martin","G","2011-11-11",2017,14),
(21,182587,"V1203347987","Michael","Lee","B","2012-03-17",2021,15),
(22,216789,"Z1109834987","Linda","Perez","G","2011-09-21",2017,16),
(23,216578,"B1601813487","William","Thompson","B","2016-01-19",2021,3),
(24,216356,"I1602817347","Elizabeth","White","G","2016-02-11",2021,4),
(25,215467,"I1504817934","David","Harris","B","2015-04-30",2020,5),
(26,215678,"F1409237987","Barbara","Smith","G","2014-09-28",2021,6),
(27,218795,"F1501823987","Robert","Sanchez","B","2015-01-02",2020,7),
(28,261187,"J1310812387","Mary","Clark","G","2013-10-21",2021,8),
(29,261234,"D1308817237","William","Williams","B","2013-08-25",2019,9),
(30,261378,"J1403817923","Richard","Ramirez","B","2014-03-18",2019,10),
(31,261356,"S1402897987","Susan","Lewis","G","2014-02-08",2019,10),
(32,262314,"S1412889987","Joseph","Robinson","B","2014-12-14",2021,5),
(33,262415,"J1506818987","Jessica","Walker","G","2015-06-10",2020,6),
(34,262513,"S1503817897","Thomas","Young","B","2015-03-11",2020,7),
(35,263465,"Q1604817989","Sarah","Allen","G","2016-04-25",2021,3),
(36,121199,"Q1409907987","Sarah","King","G","2014-09-10",2020,8),
(37,121287,"W1306890987","Mike","Wright","B","2013-06-23",2018,12),
(38,121378,"W1111819087","Sarah","Scott","G","2011-11-18",2017,8),
(39,121436,"Y1302817907","Charles","Torres","B","2013-02-11",2018,13),
(40,121578,"Y1311817990","Daniel","Nguyen","B","2013-11-15",2019,8),
(41,121596,"P1202147987","Karen","Hill","G","2012-02-16",2019,14),
(42,224192,"P1205814987","Nancy","Flores","G","2012-05-23",2020,15),
(43,224397,"P1108811487","Lisa","Green","G","2011-08-27",2021,16),
(44,224123,"L1208817147","Matthew","Adams","B","2012-08-12",2021,11),
(45,222192,"L1303817914","Betty","Nelson","G","2013-03-23",2020,12),
(46,221182,"G1211277987","Anthony","Baker","B","2012-11-24",2019,13),
(47,221369,"S1606827987","Margaret","Hall","G","2016-06-21",2021,2),
(48,221323,"P1508812787","Betty","Rivera","G","2015-08-11",2021,3),
(49,221568,"I1507817277","Charles","Campbell","B","2015-07-14",2021,4),
(50,221498,"M1502817927","John","Mitchell","B","2015-02-28",2021,8),
(51,222018,"N1407303598","Lisa","Carter","G","2014-07-31",2020,6),
(52,220568,"B1504178856","Karen","Roberts","G","2015-04-16",2020,7);
/*!40000 ALTER TABLE `students_table` ENABLE KEYS */;
UNLOCK TABLES;

select * from `students_table`;

DROP TABLE IF EXISTS `years_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`years_table` (
	`ID` bigint(20) NOT NULL AUTO_INCREMENT,
	`classYear` VARCHAR(12) NOT NULL,
	PRIMARY KEY (`ID`),
	CONSTRAINT classYear_uniqe UNIQUE (`classYear`)
    )ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `years_table` WRITE;
/*!40000 ALTER TABLE `years_table` DISABLE KEYS */;
insert into `years_table` values
(1,"NOT ASSIGNED"),
(2,"1"),
(3,"2"),
(4,"3"),
(5,"4"),
(6,"5");
/*!40000 ALTER TABLE `years_table` ENABLE KEYS */;
UNLOCK TABLES;

select * from `years_table`;

DROP TABLE IF EXISTS `class_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`class_table` (
	`ID` bigint(20) NOT NULL AUTO_INCREMENT,
	`className` VARCHAR(2) NOT NULL,
	`classYearId` bigint(20) NOT NULL,
    `classDiv` VARCHAR(2) NOT NULL,
	PRIMARY KEY (`ID`),
	CONSTRAINT className_uniqe UNIQUE (className)
    )ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `class_table` WRITE;
/*!40000 ALTER TABLE `class_table` DISABLE KEYS */;
insert into `class_table` values
(1,"NA",1,"NA"),
(2,"1A",2,"A"),
(3,"1B",2,"B"),
(4,"1C",2,"C"),
(5,"2A",3,"A"),
(6,"2B",3,"B"),
(7,"2C",3,"C"),
(8,"3A",4,"A"),
(9,"3B",4,"B"),
(10,"3C",4,"C"),
(11,"4A",5,"A"),
(12,"4B",5,"B"),
(13,"4C",5,"C"),
(14,"5A",6,"A"),
(15,"5B",6,"B"),
(16,"5C",6,"C");
/*!40000 ALTER TABLE `class_table` ENABLE KEYS */;
UNLOCK TABLES;

select * from class_table;

DROP TABLE IF EXISTS `teachers_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`teachers_table` (
	`ID` bigint(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`teacherID` bigint(20) NOT NULL,
	`teacherName` VARCHAR(30) NOT NULL,
    `teacherSurname` VARCHAR(30) NOT NULL,
    `teacherContact` VARCHAR(30) NOT NULL,
	CONSTRAINT teacherID_uniqe UNIQUE (`teacherID`)
    )ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `teachers_table` WRITE;
/*!40000 ALTER TABLE `teachers_table` DISABLE KEYS */;
insert into `teachers_table` values
(1,0,"NOT ASSIGNED","NOT ASSIGNED","NOT ASSIGNED"),
(2,21479,"Liam","HOLT","+1324567890"),
(3,45698,"Noah","LAMBERT","+1545678976"),
(4,21987,"Oliver","FLETCHER","+1876345654"),
(5,10158,"William","WATTS","+18765234765"),
(6,10025,"Elijah","BATES","+1343767980"),
(7,10078,"James","HALE","+1545765345"),
(8,10009,"Benjamin","RHODES","+1212435768"),
(9,25003,"Lucas","PENA","+1980234767"),
(10,25007,"Olivia","BECK","+1212657983"),
(11,21007,"Emma","NEWMAN","+1212512903"),
(12,19670,"Ava","HAYNES","+1212767240"),
(13,88095,"Sophia","MCDANIEL","+1212123935"),
(14,70305,"Isabella","MENDEZ","+1212767494"),
(15,90070,"Charlotte","BUSH","+12346543267"),
(16,25469,"George","CHAMBERS","+1212345678");
/*!40000 ALTER TABLE `teachers_table` ENABLE KEYS */;
UNLOCK TABLES;

select * from `teachers_table`;

DROP TABLE IF EXISTS `subjects_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`subjects_table` (
	`ID` bigint(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `subjectCode` VARCHAR(8) NOT NULL,
    `subjectName` VARCHAR(200) NOT NULL,
	`classYearId` bigint(20) NOT NULL,
	CONSTRAINT subjectCode_uniqe UNIQUE (`subjectCode`)
    )ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `subjects_table` WRITE;
/*!40000 ALTER TABLE `subjects_table` DISABLE KEYS */;
insert into `subjects_table` values
(1,"MA1","Math",2),
(2,"RE1","Reading",2),
(3,"WR1","Writing",2),
(4,"SC1","Science",2),
(5,"SS1","Social Studies",2),
(6,"VA1","Visual Arts",2),
(7,"PE1","Physical Education",2),
(8,"MU1","Music",2),
(9,"SW1","Swimming",2),
(10,"MA2","Math",3),
(11,"RE2","Reading",3),
(12,"WR2","Writing",3),
(13,"SC2","Science",3),
(14,"SS2","Social Studies",3),
(15,"VA2","Visual Arts",3),
(16,"PE2","Physical Education",3),
(17,"MU2","Music",3),
(18,"SW2","Swimming",3),
(19,"MA3","Math",4),
(20,"RE3","Reading",4),
(21,"WR3","Writing",4),
(22,"SC3","Science",4),
(23,"SS3","Social Studies",4),
(24,"VA3","Visual Arts",4),
(25,"PE3","Physical Education",4),
(26,"MU3","Music",4),
(27,"SW3","Swimming",4),
(28,"MA4","Math",5),
(29,"RE4","Reading",5),
(30,"WR4","Writing",5),
(31,"SC4","Science",5),
(32,"SS4","Social Studies",5),
(33,"VA4","Visual Arts",5),
(34,"PE4","Physical Education",5),
(35,"MU4","Music",5),
(36,"SW4","Swimming",5),
(37,"MA5","Math",6),
(38,"RE5","Reading",6),
(39,"WR5","Writing",6),
(40,"SC5","Science",6),
(41,"SS5","Social Studies",6),
(42,"VA5","Visual Arts",6),
(43,"PE5","Physical Education",6),
(44,"MU5","Music",6),
(45,"SW5","Swimming",6);
/*!40000 ALTER TABLE `subjects_table` ENABLE KEYS */;
UNLOCK TABLES;

select * from `subjects_table`;

DROP TABLE IF EXISTS `assignFor_Subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`assignFor_Subjects` (
	`ID` bigint(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    `subjectsId` bigint(20) NOT NULL,
    `teacherId` bigint(20) NOT NULL,
	CONSTRAINT subjectCode_uniqe UNIQUE (`subjectsId`,`teacherId`)
    )ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `assignFor_Subjects` WRITE;
/*!40000 ALTER TABLE `assignFor_Subjects` DISABLE KEYS */;
insert into `assignFor_Subjects` values
(1,1,1),
(2,1,2),
(3,1,3),
(4,1,4),
(5,1,5),
(6,2,1),
(7,2,8),
(8,2,9),
(9,2,10),
(10,2,11),
(11,2,15),
(12,2,16),
(13,3,1),
(14,3,15),
(15,3,16),
(16,3,8),
(17,3,9),
(18,3,10),
(19,3,11),
(20,4,1),
(21,4,2),
(22,4,3),
(23,4,4),
(24,4,5),
(25,5,1),
(26,5,6),
(27,5,7),
(28,6,1),
(29,6,6),
(30,6,7),
(31,7,1),
(32,7,12),
(33,7,13),
(34,8,1),
(35,8,14),
(36,9,1),
(37,9,12),
(38,9,13),
(39,10,1),
(40,10,2),
(41,10,3),
(42,10,4),
(43,10,5),
(44,11,1),
(45,11,8),
(46,11,9),
(47,11,10),
(48,11,11),
(49,11,15),
(50,11,16),
(51,12,1),
(52,12,15),
(53,12,16),
(54,12,8),
(55,12,9),
(56,12,10),
(57,12,11),
(58,13,1),
(59,13,2),
(60,13,3),
(61,13,4),
(62,13,5),
(63,14,1),
(64,14,6),
(65,14,7),
(66,15,1),
(67,15,6),
(68,15,7),
(69,16,1),
(70,16,12),
(71,16,13),
(72,17,1),
(73,17,14),
(74,18,1),
(75,18,12),
(76,18,13),
(77,19,1),
(78,19,2),
(79,19,3),
(80,19,4),
(81,19,5),
(82,20,1),
(83,20,8),
(84,20,9),
(85,20,10),
(86,20,11),
(87,20,15),
(88,20,16),
(89,21,1),
(90,21,15),
(91,21,16),
(92,21,8),
(93,21,9),
(94,21,10),
(95,21,11),
(96,22,1),
(97,22,2),
(98,22,3),
(99,22,4),
(100,22,5),
(101,23,1),
(102,23,6),
(103,23,7),
(104,24,1),
(105,24,6),
(106,24,7),
(107,25,1),
(108,25,12),
(109,25,13),
(110,26,1),
(111,26,14),
(112,27,1),
(113,27,12),
(114,27,13),
(115,28,1),
(116,28,2),
(117,28,3),
(118,28,4),
(119,28,5),
(120,29,1),
(121,29,8),
(122,29,9),
(123,29,10),
(124,29,11),
(125,29,15),
(126,29,16),
(127,30,1),
(128,30,15),
(129,30,16),
(130,30,8),
(131,30,9),
(132,30,10),
(133,30,11),
(134,31,1),
(135,31,2),
(136,31,3),
(137,31,4),
(138,31,5),
(139,32,1),
(140,32,6),
(141,32,7),
(142,33,1),
(143,33,6),
(144,33,7),
(145,34,1),
(146,34,12),
(147,34,13),
(148,35,1),
(149,35,14),
(150,36,1),
(151,36,12),
(152,36,13),
(153,37,1),
(154,37,2),
(155,37,3),
(156,37,4),
(157,37,5),
(158,38,1),
(159,38,8),
(160,38,9),
(161,38,10),
(162,38,11),
(163,38,15),
(164,38,16),
(165,39,1),
(166,39,15),
(167,39,16),
(168,39,8),
(169,39,9),
(170,39,10),
(171,39,11),
(172,40,1),
(173,40,2),
(174,40,3),
(175,40,4),
(176,40,5),
(177,41,1),
(178,41,6),
(179,41,7),
(180,42,1),
(181,42,6),
(182,42,7),
(183,43,1),
(184,43,12),
(185,43,13),
(186,44,1),
(187,44,14),
(188,45,1),
(189,45,12),
(190,45,13);
/*!40000 ALTER TABLE `assignFor_Subjects` ENABLE KEYS */;
UNLOCK TABLES;

select * from `assignFor_Subjects`;

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE 
	`assignments` (
	`ID` bigint(20) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	`classId` bigint(20) NOT NULL,
    `subjectId` bigint(20) NOT NULL,
    `teacherId` bigint(20) NOT NULL,
	CONSTRAINT assignments_uniqe UNIQUE (`classId`,`subjectId`)
    )ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
insert into `assignments` values
(1,2,1,2),
(2,2,2,3),
(3,2,3,8),
(4,2,4,8),
(5,3,1,2),
(6,3,2,4),
(7,3,3,15),
(8,3,4,8),
(9,4,1,3),
(10,4,2,5),
(11,4,3,11),
(12,4,4,9);
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

select * from `assignments`;

show tables;