cd /d %~dp0
call mvn -f ../pom.xml clean install -Dmaven.test.skip
pause