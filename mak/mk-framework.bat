cd /d %~dp0
call mvn -f ../dlz.framework/pom.xml clean install -Dmaven.test.skip
pause