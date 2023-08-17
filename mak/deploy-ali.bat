cd /d %~dp0
cd ..
call mvn -N versions:update-child-modules
call mvn clean source:jar deploy -Prdc
cd mak

