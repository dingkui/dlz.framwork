 cd /d %~dp0
call mvn -f ../dlz.comm/pom.xml clean install source:jar -Dmaven.test.skip
call mvn -f ../dlz.framework/pom.xml clean install source:jar -Dmaven.test.skip
pause