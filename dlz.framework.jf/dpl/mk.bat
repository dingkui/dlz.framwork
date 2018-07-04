cd /d %~dp0
cd ..
mvn clean source:jar install -Dmaven.test.skip -offline
