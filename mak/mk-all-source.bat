cd /d %~dp0
#call mvn -f ../dlz.comm/pom.xml clean source:jar deploy -Dmaven.test.skip
call mvn -f ../pom.xml clean source:jar deploy -Dmaven.test.skip
#call mvn -f ../dlz.webs/pom-web-all.xml clean source:jar deploy -Dmaven.test.skip
pause
