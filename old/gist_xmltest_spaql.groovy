// g100pon #52 XMLDB(existが一番簡単かな？)の操作
//   検索編

import java.text.*
import java.util.*


import javax.xml.transform.OutputKeys
import org.exist.xmldb.XQueryService
import org.xmldb.api.DatabaseManager
import org.xmldb.api.base.*
import org.xmldb.api.modules.XMLResource

//XMLDB connection setting
XmlDbRpcUrl='xmldb:exist://localhost:8080/exist/xmlrpc/db'
XmlDbUid='admin'
XmlDbPwd='admin'


calendar = new GregorianCalendar()
_targetParent='test01'
_targetProjectionId='Store.test.0'
_range=120

//DB conncetion set
database = Class.forName('org.exist.xmldb.DatabaseImpl').newInstance()
assert database != null
DatabaseManager.registerDatabase(database)


//date str create
calendar.add(Calendar.SECOND, -1 * _range)
date = calendar.getTime()
println date

//fmTm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
fmTm = new SimpleDateFormat("yyyy-MM-dd")
fmTm2 = new SimpleDateFormat("HH:mm:ss")
fmTm3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")


date_str = fmTm.format(date)
time_str = fmTm2.format(date)
tm_stamp=fmTm3.format(date)
//tm_stamp='2010-02-25T13:00:00'

day_of_week=calendar.get(Calendar.DAY_OF_WEEK)

target_yymm=new SimpleDateFormat("yyyyMM").format(date)
target_dd = new SimpleDateFormat("dd").format(date)
target_hh = new SimpleDateFormat("HH").format(date)
target_mm = new SimpleDateFormat("mm").format(date)


//collection set
targetURI = XmlDbRpcUrl + _targetParent
col = DatabaseManager.getCollection(targetURI, XmlDbUid, XmlDbPwd)
service = (XQueryService) col.getService("XQueryService","1.0")

// set pretty-printing on
service.setProperty(OutputKeys.INDENT, "no")
service.setProperty(OutputKeys.ENCODING, "UTF-8")


def config =[
	hote:'temp.store.data.0'
	//,hoge:'temp.store.data.0.2'
	//,fuga;'temp.store.data.1:2'
]


def f = new File("tmpl_xquery/spaql.query".toString())
//def engine = new groovy.text.GStringTemplateEngine()


config.keySet().each{key->
	_storeId = config[key]
	if(_storeId.contains(':')){
		arr = _storeId.tokenize(':')
		_storeId = arr[0]
	}

_query="""
for \$dir in collection("/db/${_targetParent}/${target_yymm}/${target_dd}/${target_hh}") 
for \$objs in \$dir/Projections[@projectionId&='${_targetProjectionId}']/Projection/Store[@id='${_storeId}'] 
where ("${tm_stamp}" <= \$objs/value/@timestamp)  
order by \$objs/value/@timestamp[0][0] 
return <result>{\$objs/value[@name='m_data']}</result>
"""

	println "_query=${_query}"

	compiled = service.compile(_query)
	result = service.execute(compiled)

	len_result = result==null ? 0:(int)result.getSize()
	println "len_result=${len_result}"
	for (i = 0 ;i < len_result ;i++) {
			resource = (XMLResource) result.getResource((long) i)
			str = (String)resource.getContent()
			println "str=${str}"
	}
}
col.close()
