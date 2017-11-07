cd /d %~dp0
cd ..
mvn clean install -Dmaven.test.skip -offline
