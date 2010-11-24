set JAVA_HOME=C:\opt\jdk

::call run_youdebug_test_target.bat
%JAVA_HOME%/bin/java -cp ".;./lib/*;./libyoudebug/*" org.kohsuke.youdebug.YouDebug -socket 5005 youdebug_test.ydb

::%JAVA_HOME%/bin/java -jar libyoudebug/youdebug-1.2-jar-with-dependencies.jar -socket 5005 youdebug_test.ydb
pause

