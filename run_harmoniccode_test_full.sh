JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home
JAVA_OPTS='-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8'


$JAVA_HOME/bin/java $JAVA_OPTS -cp ".:./lib/*:./libsteel/*" groovy.ui.GroovyMain harmoniccode_test_full.groovy

