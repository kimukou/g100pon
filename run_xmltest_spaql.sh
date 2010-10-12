JAVA_HOME=/System/Library/Frameworks/JavaVM.framework/Versions/1.6/Home
JAVA_OPTS='-Dgroovy.source.encoding=UTF-8 -Dfile.encoding=UTF-8'

EXIST_LIB=/usr/share/eXist-1.2.6/WEB-INF/lib
#EXIST_LIB=./libxml

CLASSPATH='.:./lib/*:$EXIST_LIB/*'

Q_RANGE=120
#ST_TIME="2010/10/10 19:00"

PARENT_ID=test01
STORE_ID=Store.test.0

#xmltest_spaql.groovy parentID StoreID RANGE(PREPARE MINITUS GET)
#xmltest_spaql.groovy parentID StoreID RANGE(PREPARE MINITUS GET) StartTime(yyyy/MM/dd HH:mm)

$JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH groovy.ui.GroovyMain xmltest_spaql.groovy $PARENT_ID $STORE_ID $Q_RANGE
#$JAVA_HOME/bin/java $JAVA_OPTS -cp $CLASSPATH groovy.ui.GroovyMain xmltest_spaql.groovy $PARENT_ID $STORE_ID $Q_RANGE $ST_TIME
