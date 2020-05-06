cd /d %~dp0
call mvn -f ../dlz.framework.db/pom.xml clean install -Dmaven.test.skip
pause