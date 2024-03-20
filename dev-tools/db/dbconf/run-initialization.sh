sleep 20s

/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P Test@123 -d master -i create-database.sql
