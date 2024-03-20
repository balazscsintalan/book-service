IF
(db_id(N'book') IS NULL)
BEGIN
        CREATE
DATABASE [book] COLLATE HUNGARIAN_CI_AS;
        ALTER
DATABASE [book] SET RECOVERY SIMPLE
END
GO

USE [book]
GO

IF NOT EXISTS(SELECT [name]
              FROM [sys].[database_principals]
              WHERE [type] = N'S'
                AND [name] = N'book')
BEGIN
        CREATE
LOGIN book WITH PASSWORD = 'Test@123';
        CREATE
USER book FOR LOGIN book;
END
GO

exec sp_addrolemember 'db_owner', 'book'