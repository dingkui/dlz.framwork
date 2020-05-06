cd /d %~dp0
call mvn -f ../dlz.comm/pom.xml clean install -Dmaven.test.skip
pause