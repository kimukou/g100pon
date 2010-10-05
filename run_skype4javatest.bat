set JAVA_HOME=c:/opt/jdk
::set JAVA_OPTS=-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8


for /F "delims=" %%s in ('cd') do @set PWD=%%s
set JAVA_OPTS=-Djava.library.path="%PWD%\libsky"
::set JAVA_OPTS=-Djava.library.path=./libsky

%JAVA_HOME%/bin/java %JAVA_OPTS% -cp ".;./lib/*;./libsky/*" groovy.ui.GroovyMain skype4javatest.groovy

pause
