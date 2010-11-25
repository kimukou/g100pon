set JAVA_HOME=C:\opt\jdk
set GROOVY_HOME=C:\opt\groovy-1.7.5

%JAVA_HOME%/bin/java -agentlib:jdwp=transport=dt_socket,server=y,address=5005 -cp ".;./lib/*" groovy.ui.GroovyMain youdebug_test.groovy

::%JAVA_HOME%/bin/java -cp ".;./lib/*" org.codehaus.groovy.tools.FileSystemCompiler  youdebug_test.groovy
::%JAVA_HOME%/bin/java -agentlib:jdwp=transport=dt_socket,server=y,address=5005 -cp ".;./lib/*" youdebug_test

