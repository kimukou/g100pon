JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home
JAVA_OPTS='-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8'

EXIST_LIB=/usr/share/eXist-1.2.6/WEB-INF/lib
#EXIST_LIB=./libxml

CLASSPATH='.:./lib/*:$EXIST_LIB/*'

MIN=60
#ST_TIME='2010/10/10 19:00'

PARENT_ID=test01
STORE_ID=Store.test.0
STORE_LABEL=hoge


#xmltest_xmlrpc.groovy parentID StoreID StoreLabel
#xmltest_xmlrpc.groovy parentID StoreID StoreLabel storeMin
#xmltest_xmlrpc.groovy parentID StoreID StoreLabel StoreMin StartTime

$JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH groovy.ui.GroovyMain xmltest_xmlrpc.groovy $PARENT_ID $STORE_ID $STORE_LABEL
#$JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH groovy.ui.GroovyMain xmltest_xmlrpc.groovy $PARENT_ID $STORE_ID $STORE_LABEL $MIN
#$JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH groovy.ui.GroovyMain xmltest_xmlrpc.groovy $PARENT_ID $STORE_ID $STORE_LABEL $MIN $ST_TIME
::pause