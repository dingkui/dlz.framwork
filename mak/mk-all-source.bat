cd /d %~dp0
call mvn -f ../pom.xml -N versions:update-child-modules
rem call mvn -f ../dlz.comm/pom.xml clean source:jar deploy -Dmaven.test.skip
rem call mvn -f ../pom.xml clean source:jar deploy -pl dlz.framework -am
rem call mvn -f ../pom.xml clean source:jar deploy -pl dlz.framework.db/dlz.framework.db.springjdbc -am
call mvn -f ../pom.xml clean source:jar deploy -pl dlz.comm -am
rem call mvn -f ../pom.xml clean source:jar install -Dmaven.test.skip
rem call mvn -f ../dlz.webs/pom-web-all.xml clean source:jar deploy -Dmaven.test.skip

