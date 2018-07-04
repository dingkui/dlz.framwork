@echo off
cd /d %~dp0
cd ..
set m=src\main
set r=..\..
mkdir %m%

xcopy %r%\dlz.framework\%m%\* %m% /e/y/Q
xcopy %r%\dlz.web\%m%\* %m% /e/y/Q
xcopy %r%\dlz.framework.ssme\%m%\* %m% /e/y/Q


rd /s /q target
@call mvn clean source:jar install -Dmaven.test.skip -offline
rd /s /q %m%
rd /s /q target\classes
rd /s /q target\generated-sources
rd /s /q target\maven-archiver
rd /s /q target\maven-status
pause