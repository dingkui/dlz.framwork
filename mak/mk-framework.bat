cd /d %~dp0
call mvn -f ../dlz.framework/pom.xml clean source:jar deploy -Dmaven.test.skip
pause