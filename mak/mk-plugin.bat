cd /d %~dp0
call mvn -f ../dlz.plugin/pom.xml clean install -Dmaven.test.skip
pause