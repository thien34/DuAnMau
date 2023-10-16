USE master;
GO
DROP DATABASE IF EXISTS PTPM_FINALLY_JAVA_SOF2041;
GO
CREATE DATABASE PTPM_FINALLY_JAVA_SOF2041;
GO
USE PTPM_FINALLY_JAVA_SOF2041;
GO
DROP TABLE IF EXISTS Employee;
GO
CREATE TABLE Employee
(
    ID     NVARCHAR(10) PRIMARY KEY,
    Pass   NVARCHAR(25),
    [Name] NVARCHAR(25),
    [Role] BIT DEFAULT 0
);
GO
DROP TABLE IF EXISTS Thematic;
GO
CREATE TABLE Thematic
(
    ID            NVARCHAR(10) PRIMARY KEY,
    ThematicName  NVARCHAR(50),
    Tuition       DECIMAL(10, 2) DEFAULT 0,
    [Time]        INT,
    [Image]       NVARCHAR(50),
    [Description] NVARCHAR(255)
);
GO
DROP TABLE IF EXISTS Learner;
GO
CREATE TABLE Learner
(
    ID           NVARCHAR(10) PRIMARY KEY,
    IDEmployee   NVARCHAR(10) FOREIGN KEY REFERENCES Employee ON UPDATE CASCADE ON DELETE NO ACTION,
    [Name]       NVARCHAR(25),
    Gender       BIT  DEFAULT 1,
    Birth        DATE,
    Phone        NVARCHAR(15),
    Email        NVARCHAR(25),
    Note         NVARCHAR(255),
    Registration DATE DEFAULT GETDATE()
);
GO
DROP TABLE IF EXISTS Course;
GO
CREATE TABLE Course
(
    ID          INT IDENTITY PRIMARY KEY,
    IDThematic  NVARCHAR(10) FOREIGN KEY REFERENCES Thematic ON UPDATE CASCADE,
    IDEmployee  NVARCHAR(10) FOREIGN KEY REFERENCES Employee ON UPDATE CASCADE,
    Tuition     DECIMAL(10, 2) DEFAULT 0,
    [Time]      INT,
    OpeningDay  DATE,
    Note        NVARCHAR(255),
    DateCreated DATE           DEFAULT GETDATE()
);
GO
DROP TABLE IF EXISTS Student;
GO
CREATE TABLE Student
(
    ID        INT IDENTITY PRIMARY KEY,
    IDCourse  INT FOREIGN KEY REFERENCES Course ON UPDATE NO ACTION ON DELETE CASCADE,
    IDLearner NVARCHAR(10) FOREIGN KEY REFERENCES Learner ON UPDATE CASCADE ON DELETE CASCADE,
    Point     FLOAT DEFAULT -1,
);
DELETE Student
Where 1 = 1
Select *
From Course
-- ------------------------------------------------------------
Create proc sp_ThongKeNguoiHoc AS
Select YEAR(Registration) Nam,
       COUNT(*)           SoLuong,
       MIN(Registration)  [MIN],
       Max(Registration)  [MAX]
From Learner
GROUP By YEAR(Registration)
GO;
Exec sp_ThongKeNguoiHoc
-- * * **  ** * * * * * * * *  * * * * * * **  ** **
Create proc sp_BangDiem(@IDCourse INT) AS
Select L.ID MaNH, L.Name HoTen, S.Point Diem
From Student S
         Join Learner L on S.IDLearner = L.ID
Where S.IDCourse = @IDCourse
Order By S.Point Desc
GO;
-- * * **  ** * * * * * * * *  * * * * * * **  ** **
Create proc sp_TongHopDiem AS
Select T.ThematicName ChuyenDe,
       COUNT(S.ID)    TongHV,
       MIN(S.Point)   ThapNhat,
       MAX(S.Point)   CaoNhat,
       AVG(S.Point)   TrungBinh
From Course C
         Join Thematic T on T.ID = C.IDThematic
         Join Student S on C.ID = S.IDCourse
GROUP BY T.ThematicName
GO;
-- * * **  ** * * * * * * * *  * * * * * * **  ** **
Create proc sp_DoanhThu(@Year INT) AS
Select T.ThematicName       ChuyenDe,
       COUNT(DISTINCT C.ID) SoKhoa,
       COUNT(DISTINCT S.ID) SoHV,
       SUM(C.Tuition)       DoanhThu,
       MAX(C.Tuition)       CaoNhat,
       MIN(C.Tuition)       ThapNhat,
       AVG(C.Tuition)       TrungBinh
From Course C
         Join Thematic T on T.ID = C.IDThematic
         Join Student S on C.ID = S.IDCourse
Where YEAR(C.OpeningDay) = @Year
Group By T.ThematicName
GO;
-- ------------------------------------------------------------
SELECT *
FROM Employee
Insert Employee(ID, Pass, Name, Role)
Values ('E01', '1', 'Sếp Khánh', 1),
       ('E02', '1', 'Nhân viên Hà', 0),
       ('E03', '1', 'Nhân viên Trang', 0),
       ('E04', '1', 'Nhân viên Thảo', 0)

-----------------------------------------------------------
Select *
From Course
-------------------------------------------------------
-- Drop Trigger Delete_Thematic_Course
--     ON Thematic
--     Instead Of Delete
--     AS
--     DELETE Course Where Course.IDThematic IN (Select IDThematic From deleted)
--     DELETE Thematic Where ID IN (Select ID From deleted)