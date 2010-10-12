set JAVA_HOME=C:\opt\jdk
set EXIST_LIB=D:\Tooldev\eXist-1.2.6\WEB-INF\lib
::set EXIST_LIB=./libxml

set CLASSPATH=".;./lib/*;%EXIST_LIB%/*"

set Q_RANGE=120
::set ST_TIME="2010/10/10 19:00"

set PARENT_ID=test01
set STORE_ID=Store.test.0

::xmltest_spaql.groovy parentID StoreID RANGE(PREPARE MINITUS GET)
::mltest_spaql.groovy parentID StoreID RANGE(PREPARE MINITUS GET) StartTime(yyyy/MM/dd HH:mm)

%JAVA_HOME%/bin/java -cp %CLASSPATH% groovy.ui.GroovyMain xmltest_spaql.groovy %PARENT_ID% %STORE_ID% %Q_RANGE%
::%JAVA_HOME%/bin/java -cp %CLASSPATH% groovy.ui.GroovyMain xmltest_spaql.groovy %PARENT_ID% %STORE_ID% %Q_RANGE% %ST_TIME%
pause