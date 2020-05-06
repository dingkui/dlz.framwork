cd /d %~dp0
call mvn -f ../dlz.webs/pom.xml clean install -Dmaven.test.skip
pause