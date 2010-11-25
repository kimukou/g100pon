set JAVA_HOME=C:\opt\jdk
set GROOVY_HOME=C:\opt\groovy-1.7.5

::1)groovy command 
::set JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,address=5005
::start /B %GROOVY_HOME%/bin/groovy youdebug_test.groovy

::2)jar running 
start /B %JAVA_HOME%/bin/java -agentlib:jdwp=transport=dt_socket,server=y,address=5005 -cp ".;./lib/*" groovy.ui.GroovyMain youdebug_test.groovy

::3)groovyc running
::%JAVA_HOME%/bin/java -cp ".;./lib/*" org.codehaus.groovy.tools.FileSystemCompiler  youdebug_test.groovy
::start /B %JAVA_HOME%/bin/java -agentlib:jdwp=transport=dt_socket,server=y,address=5005 -cp ".;./lib/*" youdebug_test

echo "----------------------------------------------------------------"

::start /B run_youdebug_target.bat
%JAVA_HOME%/bin/java -cp ".;./lib/*;./libyoudebug/*" org.kohsuke.youdebug.YouDebug -socket 5005 youdebug_test.ydb

::%JAVA_HOME%/bin/java -jar libyoudebug/youdebug-1.3-jar-with-dependencies.jar -socket 5005 youdebug_test.ydb

