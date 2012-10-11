@ECHO ON
REM The script sets environment variables helpful for PostgreSQL
@SET PATH="%CD%\bin";%PATH%
@SET PGDATA=%CD%\data
@SET PGDATABASE=postgres
@SET PGUSER=postgres
@SET PGPORT=5439
@SET PGLOCALEDIR=%CD%\share\locale
REM %CD%\bin\initdb -U postgres -A trust
%CD%\bin\pg_ctl -D %CD%/data -l logfile start
ECHO "Click enter to stop"
pause
%CD%\bin\pg_ctl -D %CD%/data stop