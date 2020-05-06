cd /d %~dp0
call mvn -f ../dlz.webs/pom-web-all.xml clean install -Dmaven.test.skip
pause
