cd /d %0\..
set JAVA_HOME=C:\opt\jdk

::set JAVA_OPTS=-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp ".;./lib/*;./libcsv/*" groovy.ui.GroovyMain groovycsv_test.groovy %*

::pause
