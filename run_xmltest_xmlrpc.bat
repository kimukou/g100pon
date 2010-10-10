set JAVA_HOME=C:\opt\jdk
set EXIST_LIB=D:\Tooldev\eXist-1.2.6\WEB-INF\lib
set CLASSPATH=".;./lib/*;%EXIST_LIB%/*"

set MIN=60
::set ST_TIME="2010/10/10 19:00"

set PARENT_ID=test01
set STORE_ID=Store.test.0
set STORE_LABEL=hoge

::xmltest_xmlrpc.groovy parentID StoreID StoreLabel
::xmltest_xmlrpc.groovy parentID StoreID StoreLabel storeMin
::xmltest_xmlrpc.groovy parentID StoreID StoreLabel StoreMin StartTime

%JAVA_HOME%/bin/java -cp %CLASSPATH% groovy.ui.GroovyMain xmltest_xmlrpc.groovy %PARENT_ID% %STORE_ID% %STORE_LABEL%
::%JAVA_HOME%/bin/java -cp %CLASSPATH% groovy.ui.GroovyMain xmltest_xmlrpc.groovy %PARENT_ID% %STORE_ID% %STORE_LABEL% %MIN%
::%JAVA_HOME%/bin/java -cp %CLASSPATH% groovy.ui.GroovyMain xmltest_xmlrpc.groovy %PARENT_ID% %STORE_ID% %STORE_LABEL% %MIN% %ST_TIME%
::pause