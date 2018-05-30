cd /d %~dp0
cd ..
call mvn clean install -Dmaven.test.skip -offline
pause