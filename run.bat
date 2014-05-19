@echo off

rmdir /s/q config
java -Dfile.encoding=UTF-8 -jar target\ScheduleGenerator-1.0.jar
java -Dfile.encoding=UTF-8 -jar target\Launcher-1.0.jar
