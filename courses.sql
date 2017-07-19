/*
 * Assignment:	Homework 5
 * Class: 	Insy 4305 Sec. 001
 * Name:	Michael Rodriguez
 * ID:		1000408768
 * Comments:
 */

DROP DATABASE IF EXISTS courses;

CREATE DATABASE courses;

USE courses;

CREATE TABLE Course
(
	CourseDelivery varchar(30) NOT NULL,
	CourseTitle varchar(30) NOT NULL,
	Instructor varchar(30) NOT NULL,
	Price float(6.2) NOT NULL,
	CourseType varchar(30) NOT NULL,
	StartDate date NOT NULL,
	EndDate date NOT NULL,
	ExamProtor varchar(30),
	Video varchar(4),
	Chapters int,
	Room varchar(30),
	StartTime time,
	EndTime time,
	PRIMARY KEY(CourseTitle)
);

CREATE TABLE Customer
(
	CustomerName varchar(30) NOT NULL,
	Address varchar(60) NOT NULL,
	AccountNumber int NOT NULL,
	CustomerType varchar(30) NOT NULL,
	PRIMARY KEY(CustomerName)
);


CREATE TABLE Customer_Courses
(
	CustomerName varchar(30) NOT NULL,
	CourseTitle  varchar(30) NOT NULL,
	PRIMARY KEY (CustomerName, CourseTitle),
	FOREIGN KEY (CustomerName) REFERENCES Customer(CustomerName),
	FOREIGN KEY (CourseTitle) REFERENCES Course(CourseTitle)
	
);

INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Online','Java 1','Davis',125.00,'PROGRAMMING','2015-1-1','2015-2-1','UTA','true',12, NULL, NULL, NULL);
INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Online','Java 2','Davis',125.00,'PROGRAMMING','2015-1-1','2015-2-1','UTA','true',12, NULL, NULL, NULL);
INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Online','Relieve Stress','Jones',35.00,'MISC','2015-3-2','2015-3-31','none','true',12, NULL, NULL, NULL);
INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Online','Painter 2015','Vikram',59.00,'PAINTING','2015-3-2','2015-3-31','TCU','true',12, NULL, NULL, NULL);
INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Inclass','Canon Pictures','Long',75.00,'PHOTOGRAPHY','2015-1-15','2015-2-3',NULL,NULL,NULL, 'COB142','17:30:00', '18:50:00');
INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Inclass','Play the Piano','Smith',40.00,'MUSIC','2015-4-1','2015-5-1',NULL,NULL,NULL, 'UH105','13:30:00', '15:30:00');
INSERT INTO Course(CourseDelivery, CourseTitle, Instructor, Price, CourseType, StartDate, EndDate, ExamProtor, Video, Chapters, Room, StartTime, EndTime) VALUES ('Inclass','Acting 101','Simon',69.00,'MISC','2015-4-5','2015-6-1',NULL,NULL,NULL, 'COB106','8:00:00', '10:00:00');


INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('Jones','786Cooper Arlington Texas 76019',12345,'STUDENT');
INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('Smith','921Bowen Arlington Texas 76006',65489,'FACULTY');
INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('Barker','621Lancaster FortWorth Texas 76090',54367,'STUDENT');
INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('Callan','978Lowe Dallas Texas 75009',98712,'FACULTY');
INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('Willis','123King Tulas Oklahoma 56909',25968,'GOVERNMENT');
INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('James','109Baker Dallas Texas 75010',73674,'FACULTY');
INSERT INTO Customer(CustomerName, Address, AccountNumber, CustomerType) VALUES ('Parsons','4523Azalea Burleson Texas 76134',76134,'GOVERNMENT');