JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home
export JAVA_OPTS='-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8'

SKYPE_FRAMEWORK_HOME=/Applications/Skype.app/Contents/Frameworks
::SKYPE_FRAMEWORK_HOME=/Library/Frameworks

PROJECT_HOME=`pwd`
export JAVA_OPTS=-Djava.library.path='$PROJECT_HOME/libsky:$SKYPE_FRAMEWORK_HOME'

::d32 ･･32bit JavaMode
$JAVA_HOME/bin/java -d32 $JAVA_OPTS -cp '.:./lib/*:./libsky/*' groovy.ui.GroovyMain skype4javatest.groovy
