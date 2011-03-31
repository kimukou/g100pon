set JAVA_HOME=C:/opt/jdk
::set JAVA_OPTS=-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8

::%JAVA_HOME%/bin/java %JAVA_OPTS% -cp ".;./lib/*;./libprocess/*" groovy.ui.GroovyMain processing_test.groovy

::use groovyConsole
%JAVA_HOME%/bin/java %JAVA_OPTS% -cp ".;./lib/*;./libprocess/*" groovy.ui.Console processing_test.groovy

pause
