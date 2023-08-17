cd /d %~dp0
cd ..
ren pom.xml pom-back.xml
ren pom-sonatype.xml pom.xml
call mvn -N versions:update-child-modules
call mvn clean deploy -DskipTests -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Possrh
ren pom.xml pom-sonatype.xml
ren pom-back.xml pom.xml
cd mak