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


//---------------------------------------------------------------------
calendar = new GregorianCalendar()
_targetProjectionId=''
_range=120

int _loop_max=1
int len = 0
args.each{
	switch(len){
		case 0:_targetCol=it;break
		case 1:_targetProjectionId=it;break
		case 2:_range=it.toInteger();break
		case 3:
			parser =  new SimpleDateFormat("yyyy/MM/dd HH:mm")
			date = parser.parse(it)
			calendar.setTime(date)
			break
	}
	len++
}
assert len >= 2


//------------------------------------------------------------------------------
//DB conncetion sett
database = Class.forName('org.exist.xmldb.DatabaseImpl').newInstance()
assert database != null
DatabaseManager.registerDatabase(database)


//日付文字列
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


//------------------------------------------------------------------------------//コレクション取得
targetURI = XmlDbRpcUrl + _targetCol
col = DatabaseManager.getCollection(targetURI, XmlDbUid, XmlDbPwd)
service = (XQueryService) col.getService("XQueryService","1.0")

// set pretty-printing on
service.setProperty(OutputKeys.INDENT, "no")
service.setProperty(OutputKeys.ENCODING, "UTF-8")


//■プロパティファイルの読み込み
java.util.Properties props = new Properties()
props.load(new FileInputStream("tmpl_xml/${_targetProjectionId}.properties"))
def config = new ConfigSlurper().parse(props) 


def f = new File("tmpl_xquery/spaql.query".toString())
def engine = new groovy.text.GStringTemplateEngine()

//データ出力
file = new File( 'spaql_result.txt' )

config.keySet().each{key->
	_beanid = config[key]
	if(_beanid.contains(':')){
		arr = _beanid.tokenize(':')
		_beanid = arr[0]
	}

	def binding = [	
					'target_yymm': target_yymm,
					'target_dd':target_dd,
					'tm_stamp':tm_stamp,
					'_targetProjectionId':_targetProjectionId,
					'_storeId':_beanid ,
					'_targetCol':_targetCol,
					'target_hh':target_hh,
					'target_mm':target_mm]
	def template = engine.createTemplate(f).make(binding)
	_query = template.toString()

	println "_query=${_query}"
	file.append("-------------------------------------------------\n")
	file.append("_query=${_query}\n")
	file.append("-------------------------------------------------\n")

	compiled = service.compile(_query)
	result = service.execute(compiled)

	len_result = result==null ? 0:(int)result.getSize()
	println "len_result=${len_result}"
	file.append("len_result=${len_result}\n")
	file.append("-------------------------------------------------\n")
	for (i = 0 ;i < len_result ;i++) {
			resource = (XMLResource) result.getResource((long) i)
			str = (String)resource.getContent()
			println "str=${str}"
			file.append("str=${str}\n")
	}
	file.append("-------------------------------------------------\n")
}
col.close()
