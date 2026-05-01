
use master;
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'dbcxverso')
BEGIN
    CREATE DATABASE [dbcxverso];
    PRINT 'Database [dbcxverso] created successfully.';
END
ELSE
BEGIN
    PRINT 'Database [dbcxverso] already exists.';
END
GO

-- Verifica se o login "cxuser" existe e cria se não existir
IF NOT EXISTS (SELECT * FROM sys.sql_logins WHERE name = 'cxuser')
BEGIN
    CREATE LOGIN [cxuser] WITH PASSWORD = 'Password23', CHECK_POLICY = OFF;
    PRINT 'Login [cxuser] created successfully.';
    
    -- Adiciona o login à role sysadmin
    ALTER SERVER ROLE [sysadmin] ADD MEMBER [cxuser];
    PRINT 'Login [cxuser] added to sysadmin role.';
END
ELSE
BEGIN
    PRINT 'Login [cxuser] already exists.';
END
GO

-- Usa o banco de dados dbcxverso para criar o usuário
USE dbcxverso;
GO

-- Cria o usuário do banco de dados para o login se não existir
IF NOT EXISTS (SELECT * FROM sys.database_principals WHERE name = 'cxuser')
BEGIN
    CREATE USER [cxuser] FOR LOGIN [cxuser];
    PRINT 'Database user [cxuser] created successfully.';
    
    -- Adiciona o usuário à role db_owner do banco de dados
    ALTER ROLE [db_owner] ADD MEMBER [cxuser];
    PRINT 'User [cxuser] added to db_owner role.';
END
ELSE
BEGIN
    PRINT 'Database user [cxuser] already exists.';
END
GO