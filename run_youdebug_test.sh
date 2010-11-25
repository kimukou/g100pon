JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home
JAVA_OPTS='-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8'
GROOVY_HOME=/usr/local/groovy

#1)groovy command 
#JAVA_OPTS=$JAVA_OPTS -agentlib:jdwp=transport=dt_socket,server=y,address=5005
#$GROOVY_HOME/bin/groovy youdebug_test.groovy &

#2)jar running 
$JAVA_HOME/bin/java -agentlib:jdwp=transport=dt_socket,server=y,address=5005 -cp '.:./lib/*' groovy.ui.GroovyMain youdebug_test.groovy &

#3)groovyc running
#$JAVA_HOME/bin/java -cp ".:./lib/*" org.codehaus.groovy.tools.FileSystemCompiler  youdebug_test.groovy
#$JAVA_HOME/bin/java -agentlib:jdwp=transport=dt_socket,server=y,address=5005 -cp '.:./lib/*' youdebug_test

echo "----------------------------------------------------------------"

$JAVA_HOME/bin/java -cp '.:./lib/*;./libyoudebug/*' org.kohsuke.youdebug.YouDebug -socket 5005 youdebug_test.ydb

#$JAVA_HOME/bin/java -jar libyoudebug/youdebug-1.3-jar-with-dependencies.jar -socket 5005 youdebug_test.ydb

