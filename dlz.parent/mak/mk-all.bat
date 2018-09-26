cd /d %~dp0
call mvn clean source:jar install -Dmaven.test.skip -offline
pause
