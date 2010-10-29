set JAVA_HOME=C:\opt\jdk

::set JAVA_OPTS=-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp ".;./lib/*;./libsteel/*" groovy.ui.GroovyMain steeltest_full.groovy

pause
