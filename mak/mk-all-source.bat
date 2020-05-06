cd /d %~dp0
call mvn -f ../pom.xml clean source:jar install -Dmaven.test.skip -offline
call mvn -f ../dlz.webs/pom-web-all.xml clean source:jar install -Dmaven.test.skip -offline
pause
