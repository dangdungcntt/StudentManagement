USE [master]
GO
/****** Object:  Database [btljava]    Script Date: 4/23/2017 1:10:36 PM ******/
CREATE DATABASE [btljava]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'btljava', FILENAME = N'E:\My Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\btljava.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'btljava_log', FILENAME = N'E:\My Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\btljava_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [btljava] SET COMPATIBILITY_LEVEL = 130
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [btljava].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [btljava] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [btljava] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [btljava] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [btljava] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [btljava] SET ARITHABORT OFF 
GO
ALTER DATABASE [btljava] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [btljava] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [btljava] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [btljava] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [btljava] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [btljava] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [btljava] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [btljava] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [btljava] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [btljava] SET  ENABLE_BROKER 
GO
ALTER DATABASE [btljava] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [btljava] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [btljava] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [btljava] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [btljava] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [btljava] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [btljava] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [btljava] SET RECOVERY FULL 
GO
ALTER DATABASE [btljava] SET  MULTI_USER 
GO
ALTER DATABASE [btljava] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [btljava] SET DB_CHAINING OFF 
GO
ALTER DATABASE [btljava] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [btljava] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [btljava] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'btljava', N'ON'
GO
ALTER DATABASE [btljava] SET QUERY_STORE = OFF
GO
USE [btljava]
GO
ALTER DATABASE SCOPED CONFIGURATION SET MAXDOP = 0;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET MAXDOP = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET LEGACY_CARDINALITY_ESTIMATION = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET LEGACY_CARDINALITY_ESTIMATION = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET PARAMETER_SNIFFING = ON;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET PARAMETER_SNIFFING = PRIMARY;
GO
ALTER DATABASE SCOPED CONFIGURATION SET QUERY_OPTIMIZER_HOTFIXES = OFF;
GO
ALTER DATABASE SCOPED CONFIGURATION FOR SECONDARY SET QUERY_OPTIMIZER_HOTFIXES = PRIMARY;
GO
USE [btljava]
GO
/****** Object:  User [admin]    Script Date: 4/23/2017 1:10:36 PM ******/
CREATE USER [admin] FOR LOGIN [admin] WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  Table [dbo].[thisinh]    Script Date: 4/23/2017 1:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[thisinh](
	[MaThiSinh] [int] NOT NULL,
	[HoVaTen] [nvarchar](50) NULL,
	[MaQueQuan] [int] NULL,
	[NgaySinh] [date] NULL,
	[GioiTinh] [tinyint] NULL,
	[Toan] [float] NULL,
	[Ly] [float] NULL,
	[Hoa] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaThiSinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[tinhthanh]    Script Date: 4/23/2017 1:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tinhthanh](
	[MaTinh] [int] NOT NULL,
	[TenTinh] [nvarchar](50) NULL,
	[TenTinhKhongDau] [nvarchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTinh] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[version]    Script Date: 4/23/2017 1:10:36 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[version](
	[id] [int] NOT NULL,
	[ver] [varchar](11) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (123, N'Nguyễn Đăng Dũng', 1, CAST(N'1997-11-11' AS Date), 1, 1, 1, 1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11111, N'Nguyễn Đăng Dũng', 1, CAST(N'1997-11-11' AS Date), 1, 10, 10, 10)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11112, N'Nguyễn Đức Thành', 19, CAST(N'1997-11-11' AS Date), 1, 9, 9, 9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11113, N'Lường Đình Quân', 28, CAST(N'1997-11-11' AS Date), 1, 8, 8, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11114, N'Lê Mạnh Anh', 3, CAST(N'1999-08-25' AS Date), 0, 5.3, 7.1, 5.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11115, N'Phạm Tuấn Anh', 6, CAST(N'1999-06-17' AS Date), 1, 8, 9.2, 7.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11116, N'Nguyễn Thiện Anh', 9, CAST(N'1999-08-21' AS Date), 0, 7.1, 9.6, 5.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11117, N'Thành Xuân Anh', 27, CAST(N'1999-07-08' AS Date), 1, 5.6, 5.5, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11118, N'Dương Thị Anh', 34, CAST(N'1999-01-20' AS Date), 0, 6.9, 5.8, 6.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11119, N'Nguyễn Văn Anh', 40, CAST(N'1999-02-20' AS Date), 1, 7.5, 8.9, 7.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11124, N'Trần Phương Anha', 61, CAST(N'1999-09-01' AS Date), 0, 5.9000000953674316, 5.8000001907348633, 5.0999999046325684)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11125, N'Vũ Ngọc Anh', 1, CAST(N'1999-09-02' AS Date), 1, 8.1, 7.6, 5.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11126, N'Nguyễn Thế Anh', 7, CAST(N'1999-05-25' AS Date), 0, 6.9, 9.1, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11127, N'Nguyễn Thị Anh', 21, CAST(N'1999-10-04' AS Date), 1, 6.2, 7, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11128, N'Nguyễn Đức Anh', 40, CAST(N'1999-09-11' AS Date), 0, 7.7, 9.1, 7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11129, N'Nguyễn Văn Anh', 41, CAST(N'1999-01-23' AS Date), 1, 9.2, 8.5, 5.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11130, N'Nguyễn Tuấn Anh', 47, CAST(N'1999-01-20' AS Date), 0, 6.7, 8.3, 9.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11131, N'Nguyễn Anh Ban', 51, CAST(N'1999-07-29' AS Date), 1, 7.3, 9.9, 7.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11132, N'Vũ Văn Bích', 56, CAST(N'1999-05-16' AS Date), 0, 6.1, 7.6, 5.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11133, N'Nguyễn Lê Cầm', 25, CAST(N'1999-02-28' AS Date), 1, 7.5, 9.9, 9.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11134, N'Đàm Việt Châm', 3, CAST(N'1998-11-07' AS Date), 0, 7.4, 6.5, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11135, N'Trần Hồng Châm', 39, CAST(N'1999-01-31' AS Date), 1, 6.7, 8.1, 6.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11136, N'Lê Thị Chung', 42, CAST(N'1999-07-21' AS Date), 0, 7.7, 6, 8.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11137, N'Nguyễn Mạnh Công', 24, CAST(N'1999-03-06' AS Date), 1, 9.2, 8.1, 9.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11138, N'Trần Văn Cương', 11, CAST(N'1999-12-13' AS Date), 0, 8.5, 7.9, 7.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11139, N'Nguyễn Việt Dân', 62, CAST(N'1999-05-16' AS Date), 1, 9.6, 6.6, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11140, N'Nguyễn Thi Đăng', 19, CAST(N'1999-10-16' AS Date), 0, 9.4, 7.9, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11141, N'Vũ Tuấn Đảo', 44, CAST(N'1999-02-14' AS Date), 1, 8.8, 8.2, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11142, N'Lê Văn Đạo', 16, CAST(N'1999-09-30' AS Date), 0, 5.7, 9.5, 9.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11143, N'Nguyễn Tuấn Đạt', 4, CAST(N'1998-11-03' AS Date), 1, 7.8, 8, 6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11144, N'Lê Quang Đạt', 47, CAST(N'1999-04-29' AS Date), 0, 6.5, 6.4, 7.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11145, N'Trần Đông Điềm', 55, CAST(N'1998-06-03' AS Date), 1, 6.5, 7.6, 8.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11146, N'Lê Giáp Dinh', 3, CAST(N'1999-12-16' AS Date), 0, 8.6, 7.3, 6.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11147, N'Lê Xuân Đô', 8, CAST(N'1999-03-20' AS Date), 1, 10, 8.8, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11148, N'Tống Vương Đôn', 26, CAST(N'1999-12-10' AS Date), 0, 7.5, 9.5, 7.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11149, N'Lê Văn Đông', 21, CAST(N'1999-09-04' AS Date), 1, 7.7, 6.2, 8.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11150, N'Bế Quang Đức', 4, CAST(N'1999-05-25' AS Date), 0, 7.2, 9.7, 5.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11151, N'Nguyễn Văn Đức', 16, CAST(N'1999-06-07' AS Date), 1, 9.9, 5.1, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11152, N'Trương Thị Đức', 22, CAST(N'1999-03-09' AS Date), 0, 8.3, 6.2, 9.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11153, N'Vũ Bá Đức', 34, CAST(N'1999-10-02' AS Date), 1, 7, 7, 5.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11154, N'Vũ Tiến Đức', 50, CAST(N'1999-01-19' AS Date), 0, 9, 9.8, 5.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11155, N'Mạnh Thành Dũng', 17, CAST(N'1997-02-25' AS Date), 1, 5.4, 9, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11156, N'Nguyễn Văn Dũng', 57, CAST(N'1997-11-01' AS Date), 0, 7.8, 8.6, 10)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11157, N'Phạm Hoàng Dương', 1, CAST(N'1999-01-18' AS Date), 1, 9.6, 5.3, 9.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11158, N'Nguyễn Thị Dương', 2, CAST(N'1998-04-12' AS Date), 0, 7.6, 5.1, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11159, N'Trần Văn Dương', 31, CAST(N'1999-08-16' AS Date), 1, 5.2, 7.9, 9.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11160, N'Vũ Công Duy', 7, CAST(N'1999-01-02' AS Date), 0, 7.3, 9.9, 8.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11161, N'Thái Hữu Duy', 41, CAST(N'1999-07-03' AS Date), 1, 8.6, 8.9, 7.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11162, N'Trần Đức Giang', 54, CAST(N'1999-08-18' AS Date), 0, 9.6, 9, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11163, N'Nguyễn Văn Giang', 63, CAST(N'1999-09-02' AS Date), 1, 6.8, 9, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11164, N'Trần Đức Hải', 45, CAST(N'1999-03-19' AS Date), 0, 9.1, 5.2, 6.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11165, N'Trần Văn Hải', 14, CAST(N'1999-04-09' AS Date), 1, 6.5, 8.3, 5.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11166, N'Nguyễn Văn Hằng', 48, CAST(N'1995-01-23' AS Date), 0, 7.8, 7.4, 5.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11167, N'Lê Văn Hảo', 54, CAST(N'1999-05-01' AS Date), 1, 8.8, 7.4, 8.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11168, N'Vũ Duy Hậu', 19, CAST(N'1999-10-23' AS Date), 0, 9, 9.5, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11169, N'Mai Xuân Hậu', 51, CAST(N'1999-04-08' AS Date), 1, 6.4, 6, 7.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11170, N'Lê Hồng Hiền', 60, CAST(N'1999-11-10' AS Date), 0, 6.2, 9, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11171, N'Hoàng Mạnh Hiền', 6, CAST(N'1999-08-22' AS Date), 1, 5.3, 9.9, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11172, N'Đặng Anh Hiển', 44, CAST(N'1999-12-07' AS Date), 0, 6.3, 7.7, 5.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11173, N'Lê Trạc Hiệp', 42, CAST(N'1999-01-13' AS Date), 1, 9.6, 8.3, 7.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11174, N'Đặng Đức Hiếu', 30, CAST(N'1999-02-16' AS Date), 0, 5.1, 5.8, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11175, N'Trịnh Thế Hiếu', 50, CAST(N'1999-11-24' AS Date), 1, 8.2, 5.5, 5.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11176, N'Hoàng Văn Hiếu', 24, CAST(N'1999-03-02' AS Date), 0, 8.3, 9, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11177, N'Phạm Minh Hiếu', 5, CAST(N'1999-02-14' AS Date), 1, 7.7, 5.4, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11178, N'Ngô Anh Hiệu', 30, CAST(N'1999-11-04' AS Date), 0, 8, 7.4, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11179, N'Nguyễn Văn Hoà', 21, CAST(N'1999-11-12' AS Date), 1, 5.8, 6.3, 6.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11180, N'Đặng Văn Hòa', 64, CAST(N'1999-07-25' AS Date), 0, 8, 8.5, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11181, N'Đỗ Văn Hòa', 17, CAST(N'1999-05-29' AS Date), 1, 9.7, 5.1, 6.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11182, N'Đinh Duy Hoan', 17, CAST(N'1997-02-04' AS Date), 0, 10, 6.4, 9.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11183, N'Nguyễn Minh Hoàng', 8, CAST(N'1999-06-20' AS Date), 1, 7, 9.2, 6.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11184, N'Trần Trọng Hoàng', 46, CAST(N'1999-02-02' AS Date), 0, 9, 7.5, 7.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11185, N'Trần Viết Hoàng', 51, CAST(N'1999-09-04' AS Date), 1, 8.3, 5.5, 5.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11186, N'Lê Thị Hoàng', 60, CAST(N'1999-07-24' AS Date), 0, 8.7, 5.1, 7.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11187, N'Đinh Ngọc Hoàng', 5, CAST(N'1999-06-05' AS Date), 1, 8.1, 9.9, 6.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11188, N'Nguyễn Ngọc Hoàng', 9, CAST(N'1999-02-14' AS Date), 0, 6.6, 5.5, 8.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11189, N'Phạm Viết Hoàng', 23, CAST(N'1999-11-08' AS Date), 1, 9.8, 5.9, 8.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11190, N'Cao Ngọc Huấn', 38, CAST(N'1999-04-01' AS Date), 0, 7.6, 9.9, 5.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11191, N'Trần Thế Hùng', 18, CAST(N'1999-07-01' AS Date), 1, 6.7, 5.6, 7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11192, N'Đỗ Hiếu Hùng', 1, CAST(N'1999-08-20' AS Date), 0, 8.8, 6, 7.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11193, N'Nguyễn Trọng Hưng', 53, CAST(N'1999-05-23' AS Date), 1, 7.8, 6.6, 7.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11194, N'Nguyễn Đức Hưng', 55, CAST(N'1999-12-17' AS Date), 0, 5.2, 9.8, 9.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11195, N'Trần Công Hương', 62, CAST(N'1999-02-13' AS Date), 1, 9.6, 8, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11196, N'Trần Văn Hương', 6, CAST(N'1999-02-11' AS Date), 0, 5.4, 6.2, 9.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11197, N'Phạm Đức Hương', 15, CAST(N'1999-04-05' AS Date), 1, 5.5, 9.9, 6.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11198, N'Hoàng Tuấn Huy', 2, CAST(N'1999-12-28' AS Date), 0, 6, 9.3, 8.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11199, N'Hoàng Đình Huy', 29, CAST(N'1998-06-03' AS Date), 1, 9.4, 9.8, 7.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11200, N'Lại Thị Huy', 47, CAST(N'1999-05-07' AS Date), 0, 6.3, 6.9, 8.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11201, N'Nguyễn Văn Huy', 49, CAST(N'1999-06-26' AS Date), 1, 5.9, 8, 6.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11202, N'Nguyễn Thị Huy', 33, CAST(N'1999-05-28' AS Date), 0, 9, 8.7, 10)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11203, N'Lê Thị Huy', 59, CAST(N'1998-03-23' AS Date), 1, 9.8, 5.9, 9.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11204, N'Vũ Thành Huyền', 56, CAST(N'1999-01-11' AS Date), 0, 7.5, 9.1, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11205, N'Nguyễn Khoa Huỳnh', 26, CAST(N'1999-01-22' AS Date), 1, 7, 6.4, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11206, N'Nguyễn Duy Khải', 10, CAST(N'1999-01-12' AS Date), 0, 9.1, 9.7, 7.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11207, N'Nguyễn Bá Khánh', 2, CAST(N'1999-12-24' AS Date), 1, 9.4, 7.9, 9.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11208, N'Nguyễn Xuân Khởi', 11, CAST(N'1999-10-04' AS Date), 0, 6.4, 8, 6.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11209, N'Đỗ Thị Kiên', 48, CAST(N'1997-01-09' AS Date), 1, 5.2, 5.7, 9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11210, N'Phạm Đình Linh', 19, CAST(N'1999-11-04' AS Date), 0, 8.6, 6.5, 10)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11211, N'Nguyễn Thanh Linh', 22, CAST(N'1999-09-02' AS Date), 1, 7.1, 9.6, 7.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11212, N'Đinh Thế Linh', 33, CAST(N'1999-07-14' AS Date), 0, 8.6, 6.8, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11213, N'Dương Duy Lộc', 52, CAST(N'1998-03-09' AS Date), 1, 7.1, 7.6, 5.6)
GO
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11214, N'Nguyễn Văn Lợi', 58, CAST(N'1999-09-27' AS Date), 0, 5.6, 8.1, 7.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11215, N'Nguyễn Khương Long', 24, CAST(N'1999-03-23' AS Date), 1, 7, 6.6, 5.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11216, N'Nguyễn Thanh Long', 57, CAST(N'1995-09-02' AS Date), 0, 6, 8.1, 8.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11217, N'Bùi Minh Lương', 31, CAST(N'1999-04-18' AS Date), 1, 6, 6.3, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11218, N'Lê Thị Lượng', 37, CAST(N'1999-06-22' AS Date), 0, 6.8, 6.8, 9.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11219, N'Hà Việt Luyến', 60, CAST(N'1999-09-24' AS Date), 1, 5.6, 9.9, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11220, N'Nguyễn Văn Lý', 63, CAST(N'1999-08-14' AS Date), 0, 6.4, 8.2, 5.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11221, N'Nguyễn Thị Mạnh', 28, CAST(N'1999-07-07' AS Date), 1, 8.2, 7.2, 9.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11222, N'Đồng Văn Mạnh', 53, CAST(N'1999-07-16' AS Date), 0, 8.7, 8.8, 8.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11223, N'Đỗ Tùng Mây', 25, CAST(N'1999-05-22' AS Date), 1, 5.5, 8.1, 6.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11224, N'Trần Danh Minh', 31, CAST(N'1999-01-30' AS Date), 0, 6.3, 6.3, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11225, N'Đỗ Duy Minh', 39, CAST(N'1999-10-03' AS Date), 1, 8.3, 8, 9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11226, N'Ngô Quang Minh', 58, CAST(N'1999-10-25' AS Date), 0, 6.1, 5.5, 8.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11227, N'Nguyễn Thị Minh', 45, CAST(N'1999-06-25' AS Date), 1, 6.5, 6.5, 7.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11228, N'Lê Sỹ Nam', 36, CAST(N'1999-11-14' AS Date), 0, 7.9, 9.1, 9.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11229, N'Đào Mạnh Nam', 38, CAST(N'1998-03-14' AS Date), 1, 8.2, 7.7, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11230, N'Nguyễn Văn Nam', 7, CAST(N'1999-12-01' AS Date), 0, 6.9, 7.5, 9.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11233, N'Bùi Đăng Nam', 58, CAST(N'1999-01-03' AS Date), 1, 7.5, 6.6, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11234, N'Nguyễn Khắc Ngân', 32, CAST(N'1999-05-19' AS Date), 0, 7, 5.2, 7.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11235, N'Lê Đình Nghĩa', 29, CAST(N'1999-08-24' AS Date), 1, 6, 8.6, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11236, N'Đỗ Văn Nghĩa', 2, CAST(N'1999-09-09' AS Date), 0, 5.2, 7.8, 8.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11237, N'Nguyễn Hoàng Nghĩa', 16, CAST(N'1999-04-05' AS Date), 1, 8.4, 6.7, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11238, N'Nguyễn Đức Ngọc', 50, CAST(N'1999-06-04' AS Date), 0, 5.9, 8.7, 5.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11239, N'Trần Văn Ngọc', 57, CAST(N'1999-04-28' AS Date), 1, 5.1, 6.3, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11240, N'Nguyễn Ngọc Nguyệt', 63, CAST(N'1998-10-28' AS Date), 0, 6.9, 8.2, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11241, N'Lê Thị Nhài', 54, CAST(N'1999-07-15' AS Date), 1, 6.5, 5.4, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11242, N'Nguyễn Văn Nhi', 46, CAST(N'1998-12-18' AS Date), 0, 8, 5.2, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11243, N'Phạm Quốc Như', 61, CAST(N'1999-01-03' AS Date), 1, 9.4, 6.7, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11244, N'Đặng Đình Ninh', 28, CAST(N'1999-01-15' AS Date), 0, 9.7, 8.5, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11245, N'Lê Trọng Oanh', 35, CAST(N'1999-11-20' AS Date), 1, 9.4, 6, 6.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11246, N'Nguyễn Thị Phấn', 48, CAST(N'1999-06-06' AS Date), 0, 8.3, 9, 5.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11247, N'Nguyễn Thị Phi', 12, CAST(N'1999-03-10' AS Date), 1, 5.1, 6, 5.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11248, N'Phạm Minh Phi', 52, CAST(N'1999-05-13' AS Date), 0, 8, 6.9, 6.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11249, N'Trương Quốc Phong', 45, CAST(N'1999-07-23' AS Date), 1, 5.3, 8.2, 7.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11250, N'Nguyễn Văn Quân', 27, CAST(N'1998-08-11' AS Date), 0, 6.9, 9.6, 7.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11251, N'Nguyễn Văn Quang', 38, CAST(N'1999-04-04' AS Date), 1, 6, 9.7, 8.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11252, N'Bùi Minh Quang', 8, CAST(N'1999-09-16' AS Date), 0, 8.5, 9.5, 6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11253, N'Vũ Tuấn Quang', 19, CAST(N'1999-02-16' AS Date), 1, 6.3, 6.6, 9.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11254, N'Nguyễn Thái Quảng', 53, CAST(N'1998-08-09' AS Date), 0, 8.6, 8.5, 6.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11255, N'Lê Thị Quý', 32, CAST(N'1999-09-04' AS Date), 1, 8.1, 7, 7.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11256, N'Nguyễn Đức Quyền', 27, CAST(N'1998-02-17' AS Date), 0, 6.6, 7.3, 6.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11257, N'Nguyễn Viết Quỳnh', 32, CAST(N'1999-10-31' AS Date), 1, 5.7, 8.3, 8.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11258, N'Nguyễn Văn Sơn', 15, CAST(N'1999-03-02' AS Date), 0, 5.6, 7, 5.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11259, N'Nguyễn Văn Sơn', 12, CAST(N'1999-11-21' AS Date), 1, 7.4, 8.2, 7.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11260, N'Lê Đình Sơn', 44, CAST(N'1998-09-15' AS Date), 0, 5.7, 9.7, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11261, N'Lê Anh Sơn', 37, CAST(N'1999-06-03' AS Date), 1, 6.4, 7.1, 5.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11262, N'Trương Văn Sỹ', 19, CAST(N'1999-10-10' AS Date), 0, 6, 5.9, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11263, N'Mai Tiến Tài', 19, CAST(N'1999-01-14' AS Date), 1, 5.3, 9.5, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11264, N'Lê Nguyễn Tài', 1, CAST(N'1999-06-28' AS Date), 0, 7.7, 8.5, 7.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11265, N'Lê Ánh Tài', 55, CAST(N'1999-04-22' AS Date), 1, 7.5, 6.1, 8.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11266, N'Đỗ Anh Thắng', 37, CAST(N'1999-08-26' AS Date), 0, 6.5, 6, 9.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11267, N'Nguyễn Mạnh Thắng', 40, CAST(N'1999-04-16' AS Date), 1, 5.5, 9.8, 5.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11268, N'Phan Hoàng Thắng', 22, CAST(N'1999-02-15' AS Date), 0, 7.2, 6.6, 6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11269, N'Nguyễn Thị Thanh', 10, CAST(N'1999-08-26' AS Date), 1, 5.2, 5.7, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11270, N'Trương Quang Thành', 23, CAST(N'1999-06-15' AS Date), 0, 9.2, 7.5, 6.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11271, N'Vũ Nam Thành', 26, CAST(N'1998-01-10' AS Date), 1, 6.7, 8.6, 5.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11272, N'Nguyễn Hoàng Thành', 36, CAST(N'1999-10-09' AS Date), 0, 6.3, 9.9, 7.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11273, N'Đỗ Xuân Thảo', 18, CAST(N'1998-09-27' AS Date), 1, 6.9, 6.4, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11274, N'Nguyễn Quang Thảo', 14, CAST(N'1999-09-28' AS Date), 0, 5.7, 7.4, 6.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11275, N'Nguyễn Tiến Thảo', 62, CAST(N'1999-04-20' AS Date), 1, 6.8, 8.3, 9.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11276, N'Đinh Thế Thiện', 29, CAST(N'1998-09-09' AS Date), 0, 5.3, 8.5, 7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11277, N'Đỗ Xuân Thư', 49, CAST(N'1998-12-04' AS Date), 1, 5.8, 7.6, 9.8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11278, N'Nguyễn Tiến Thuật', 14, CAST(N'1999-01-16' AS Date), 0, 5.8, 9.4, 8.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11279, N'Nguyễn Trường Thúy', 13, CAST(N'1999-03-23' AS Date), 1, 9.2, 5.4, 6.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11280, N'Nguyễn Văn Thúy', 39, CAST(N'1999-04-24' AS Date), 0, 6.7, 6.9, 5.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11281, N'Nguyễn Văn Thủy', 34, CAST(N'1999-01-07' AS Date), 1, 6.9, 8.9, 6.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11282, N'Nguyễn Duy Thủy', 64, CAST(N'1996-09-28' AS Date), 0, 9.4, 5.9, 8.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11283, N'Phan Ngọc Tiến', 59, CAST(N'1999-08-17' AS Date), 1, 5.1, 8.2, 8)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11284, N'Hà Mạnh Tiến', 13, CAST(N'1999-09-09' AS Date), 0, 9.7, 5.2, 9.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11285, N'Nguyễn Ngọc Tiến', 4, CAST(N'1999-08-25' AS Date), 1, 8.6, 5.3, 5.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11286, N'Dương Khắc Tình', 11, CAST(N'1999-07-07' AS Date), 0, 9.5, 7.9, 7.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11287, N'Nguyễn Xuân Tĩnh', 25, CAST(N'1998-06-12' AS Date), 1, 9.8, 6.6, 5.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11288, N'Nguyễn Mạnh Toàn', 41, CAST(N'1995-04-14' AS Date), 0, 6, 8.3, 6.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11289, N'Trần Huy Toàn', 64, CAST(N'1999-04-04' AS Date), 1, 9.2, 6.6, 6.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11290, N'Lê Việt Tràn', 61, CAST(N'1999-05-12' AS Date), 0, 5.2, 9.7, 8.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11291, N'Vũ Đình Trang', 3, CAST(N'1999-01-30' AS Date), 1, 8.2, 5.1, 9.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11292, N'Nguyễn Văn Trí', 28, CAST(N'1999-05-10' AS Date), 0, 7.6, 7.3, 8.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11293, N'Nguyễn Năng Triệu', 56, CAST(N'1999-02-23' AS Date), 1, 5.1, 6.2, 8.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11294, N'Thiều Văn Trung', 4, CAST(N'1998-08-24' AS Date), 0, 7.3, 7, 9.3)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11295, N'Nguyễn Khánh Trung', 49, CAST(N'1999-08-01' AS Date), 1, 5.5, 9.2, 9.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11296, N'Trần Minh Trường', 35, CAST(N'1999-07-19' AS Date), 0, 6.8, 8.4, 6.5)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11297, N'Bùi Văn Trường', 15, CAST(N'1999-04-02' AS Date), 1, 6.1, 5.1, 8.1)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11298, N'Đỗ Hoàng Trường', 59, CAST(N'1999-05-16' AS Date), 0, 6.8, 6.9, 8.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11299, N'Trịnh Duy Tú', 12, CAST(N'1999-10-22' AS Date), 1, 10, 8.5, 5.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11300, N'Nguyễn Minh Tú', 42, CAST(N'1999-04-28' AS Date), 0, 6.2, 7.1, 5.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11301, N'Phạm Đức Tú', 43, CAST(N'1999-01-19' AS Date), 1, 7, 5.9, 7.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11302, N'Nguyễn Văn Tuân', 35, CAST(N'1999-08-12' AS Date), 0, 8.6, 9.6, 7.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11303, N'Lê Văn Tuấn', 5, CAST(N'1999-03-01' AS Date), 1, 7.9, 5.2, 7.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11304, N'Nguyễn Vũ Tuấn', 9, CAST(N'1999-02-04' AS Date), 0, 9.9, 7.8, 9.2)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11305, N'Hoàng Văn Tuấn', 36, CAST(N'1999-04-11' AS Date), 1, 8.7, 6.3, 6.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11306, N'Đỗ Tất Tuấn', 30, CAST(N'1999-09-08' AS Date), 0, 7.3, 7.4, 7.7)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11307, N'Nguyễn Đức Việt', 52, CAST(N'1999-01-08' AS Date), 1, 9.3, 7.2, 6.6)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11308, N'Lê Quang Vinh', 5, CAST(N'1999-12-16' AS Date), 0, 9.9, 5.9, 6.4)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (11309, N'Trần Văn Vượng', 33, CAST(N'1999-07-28' AS Date), 1, 5.4, 9.1, 7.9)
INSERT [dbo].[thisinh] ([MaThiSinh], [HoVaTen], [MaQueQuan], [NgaySinh], [GioiTinh], [Toan], [Ly], [Hoa]) VALUES (696969, N'Liên Lung Link', 1, CAST(N'1997-01-12' AS Date), 1, 9, 9, 9)
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (1, N'Hà Nội', N'Ha Noi')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (2, N'Hồ Chí Minh', N'Ho Chi Minh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (3, N'Hải Phòng', N'Hai Phong')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (4, N'Đà Nẵng', N'Da Nang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (5, N'Hà Giang', N'Ha Giang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (6, N'Cao Bằng', N'Cao Bang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (7, N'Lai Châu', N'Lai Chau')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (8, N'Lào Cai', N'Lao Cai')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (9, N'Tuyên Quang', N'Tuyen Quang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (10, N'Lạng Sơn', N'Lang Son')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (11, N'Bắc Kạn', N'Bac Kan')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (12, N'Thái Nguyên', N'Thai Nguyen')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (13, N'Yên Bái', N'Yen Bai')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (14, N'Sơn La', N'Son La')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (15, N'Phú Thọ', N'Phu Tho')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (16, N'Vĩnh Phúc', N'Vinh Phuc')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (17, N'Quảng Ninh', N'Quang Ninh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (18, N'Bắc Giang', N'Bac Giang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (19, N'Bắc Ninh', N'Bac Ninh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (21, N'Hải Dương', N'Hai Duong')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (22, N'Hưng Yên', N'Hung Yen')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (23, N'Hòa Bình', N'Hoa Binh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (24, N'Hà Nam', N'Ha Nam')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (25, N'Nam Định', N'Nam Dinh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (26, N'Thái Bình', N'Thai Binh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (27, N'Ninh Bình', N'Ninh Binh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (28, N'Thanh Hóa', N'Thanh Hoa')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (29, N'Nghệ An', N'Nghe An')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (30, N'Hà Tĩnh', N'Ha Tinh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (31, N'Quảng Bình', N'Quang Binh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (32, N'Quảng Trị', N'Quang Tri')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (33, N'Thừa Thiên Huế', N'Thua Thien Hue')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (34, N'Quảng Nam', N'Quang Nam')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (35, N'Quảng Ngãi', N'Quang Ngai')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (36, N'Kon Tum', N'Kon Tum')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (37, N'Bình Định', N'Binh Dinh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (38, N'Gia Lai', N'Gia Lai')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (39, N'Phú Yên', N'Phu Yen')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (40, N'Đăk Lăk', N'Dak Lak')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (41, N'Khánh Hòa', N'Khanh Hoa')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (42, N'Lâm Đồng', N'Lam Dong')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (43, N'Bình Phước', N'Binh Phuoc')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (44, N'Bình Dương', N'Binh Duong')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (45, N'Ninh Thuận', N'Ninh Thuan')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (46, N'Tây Ninh', N'Tay Ninh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (47, N'Bình Thuận', N'Binh Thuan')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (48, N'Đồng Nai', N'Dong Nai')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (49, N'Long An', N'Long An')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (50, N'Đồng Tháp', N'Dong Thap')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (51, N'An Giang', N'An Giang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (52, N'Bà Rịa - Vũng Tàu', N'Ba Ria - Vung Tau')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (53, N'Tiền Giang', N'Tien Giang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (54, N'Kiên Giang', N'Kien Giang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (55, N'Cần Thơ', N'Can Tho')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (56, N'Bến Tre', N'Ben Tre')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (57, N'Vĩnh Long', N'Vinh Long')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (58, N'Trà Vinh', N'Tra Vinh')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (59, N'Sóc Trăng', N'Soc Trang')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (60, N'Bạc Liêu', N'Bac Lieu')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (61, N'Cà Mau', N'Ca Mau')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (62, N'Điện Biên', N'Dien Bien')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (63, N'Đăk Nông', N'Dak Nong')
INSERT [dbo].[tinhthanh] ([MaTinh], [TenTinh], [TenTinhKhongDau]) VALUES (64, N'Hậu Giang', N'Hau Giang')
INSERT [dbo].[version] ([id], [ver]) VALUES (1, N'1.0.0')
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [HoVaTen]
GO
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [MaQueQuan]
GO
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [NgaySinh]
GO
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [GioiTinh]
GO
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [Toan]
GO
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [Ly]
GO
ALTER TABLE [dbo].[thisinh] ADD  DEFAULT (NULL) FOR [Hoa]
GO
ALTER TABLE [dbo].[tinhthanh] ADD  DEFAULT (NULL) FOR [TenTinh]
GO
ALTER TABLE [dbo].[thisinh]  WITH CHECK ADD  CONSTRAINT [fk_thisinh_maquequan] FOREIGN KEY([MaQueQuan])
REFERENCES [dbo].[tinhthanh] ([MaTinh])
GO
ALTER TABLE [dbo].[thisinh] CHECK CONSTRAINT [fk_thisinh_maquequan]
GO
USE [master]
GO
ALTER DATABASE [btljava] SET  READ_WRITE 
GO
