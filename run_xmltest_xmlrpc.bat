set JAVA_HOME=C:\opt\jdk

set MIN=60
::set ST_TIME="2010/10/10 19:00"

set PARENT_ID=test01
set STORE_ID=Store.test.0
set STORE_LABEL=hoge

::xmltest_xmlrpc.groovy parentID StoreID Store���x��
::xmltest_xmlrpc.groovy parentID StoreID Store���x�� �~�ϕ���
::xmltest_xmlrpc.groovy parentID StoreID Store���x�� �~�ϕ��� �J�n����

%JAVA_HOME%/bin/java -cp ".;./lib/*;./libxml/*" groovy.ui.GroovyMain xmltest_xmlrpc.groovy %PARENT_ID% %STORE_ID% %STORE_LABEL%
::%JAVA_HOME%/bin/java -cp ".;./lib/*;./libxml/*" groovy.ui.GroovyMain xmltest_xmlrpc.groovy %PARENT_ID% %STORE_ID% %STORE_LABEL% %MIN%
::%JAVA_HOME%/bin/java -cp ".;./lib/*;./libxml/*" groovy.ui.GroovyMain xmltest_xmlrpc.groovy %PARENT_ID% %STORE_ID% %STORE_LABEL% %MIN% %ST_TIME%
::pause