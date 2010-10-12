// g100pon #52 XMLDB(existが一番簡単かな？)の操作
//   蓄積編

import java.text.*
import java.util.*

import java.io.*

import org.xmldb.api.*
import org.xmldb.api.base.*
import org.xmldb.api.modules.*

import org.xml.sax.*
import javax.xml.parsers.*
import org.w3c.dom.*

//XMLDB connection setting
XmlDbRpcUrl='xmldb:exist://localhost:8080/exist/xmlrpc/db'
XmlDbUid='admin'
XmlDbPwd='admin'
BASE_XML="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Projections projectionId=\"%s\" projectionName=\"%s\"></Projections>"



//argument(default nowtime)
calendar = new GregorianCalendar()
date = calendar.getTime()

_targetParent='test01'
_targetProjectionId='Store.test.0'
_targetProjectionName='hoge'
_loop_max=1

IntRange.metaClass.define {
    //random data cleate
    random {
        int from = delegate.isReverse() ? to : from
        int to   = delegate.isReverse() ? from : to
        int size = to - from + 1
        (Math.floor(Math.random() * size) + from) as int
    }
}

//DB set
database = Class.forName('org.exist.xmldb.DatabaseImpl').newInstance()
assert database != null
DatabaseManager.registerDatabase(database)


//date str create
fmTm = new SimpleDateFormat("yyyy-MM-dd")
fmTm2 = new SimpleDateFormat("HH:mm:ss")
fmTm3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")


_loop_max.times{
	date_str = fmTm.format(date)
	time_str = fmTm2.format(date)
	tm_stamp=fmTm3.format(date)
	day_of_week=calendar.get(Calendar.DAY_OF_WEEK)

	target_yymm=new SimpleDateFormat("yyyyMM").format(date)
	target_dd = new SimpleDateFormat("dd").format(date)
	target_hh = new SimpleDateFormat("HH").format(date)
	target_mm = new SimpleDateFormat("mm").format(date)


//collection get

	strCol = new StringBuilder()
					.append(_targetParent).append("/").append(target_yymm).append("/")
					.append(target_dd).append("/").append(target_hh).append("/")
					.append(target_mm).toString()
	println "(0)strCol=${strCol}"
	//out << "(0)strCol=${strCol}" << "\n"
	col = DatabaseManager.getCollection(
					XmlDbRpcUrl + "/" + strCol, XmlDbUid, XmlDbPwd)


	if(null == col){
		root = DatabaseManager.getCollection(XmlDbRpcUrl, XmlDbUid, XmlDbPwd)
		mgtService = (CollectionManagementService)root.getService("CollectionManagementService", "1.0")

		target = new StringBuilder()
		strCol = target.append(_targetParent).toString()
		col = DatabaseManager.getCollection(XmlDbRpcUrl + "/" + strCol, XmlDbUid, XmlDbPwd)

		if(null == col){
			println "(1)strCol=${strCol}"
			col = mgtService.createCollection(strCol)
		}

		strCol = target.append("/").append(target_yymm).toString()

		col = col.getChildCollection(target_yymm)
		if(null == col){
			println "(2)strCol=${strCol}"
			col = mgtService.createCollection(strCol)
		}
		

		strCol = target.append("/").append(target_dd).toString()
		col = col.getChildCollection(target_dd)
		if(null == col){
			println "(3)strCol=${strCol}"
			col = mgtService.createCollection(strCol)
		}

		strCol = target.append("/").append(target_hh).toString()
		col = col.getChildCollection(target_hh)
		if(null == col){
			println "(4)strCol=${strCol}"
			col = mgtService.createCollection(strCol)
		}

		strCol = target.append("/").append(target_mm).toString()
		col = col.getChildCollection(target_mm)
		if(null == col){
			println "(5)strCol=${strCol}"
			col = mgtService.createCollection(strCol)
		}

		mgtService = null
		root.close()
	}

	//projectionId add
	res = col.getResource(_targetProjectionId)
	if (res == null){
		document = col.createResource(_targetProjectionId, "XMLResource")
		baseString = String.format(BASE_XML,_targetProjectionId, _targetProjectionName)

		def dbfactory = DocumentBuilderFactory.newInstance()
		def parser = dbfactory.newDocumentBuilder()
		def isrc = new InputSource(new StringReader(baseString))
		isrc.setEncoding("UTF-8")  
		Document doc = parser.parse(isrc)
		document.setContentAsDOM(doc)
		col.storeResource(document) 
	}


	def config =[
		hote:'temp.store.data.0'
		//,hoge:'temp.store.data.0.2'
		//,fuga;'temp.store.data.1:2'
	]


	idarr=[]
	valarr=[]
	labelarr=[]
	//storedata set
	config.keySet().each{key->
			labelarr.add key
			id_label = config[key]

			int keynum = 1
			if(id_label.contains(':')){
				arr = id_label.tokenize(':')
				id_label = arr[0]
				keynum = arr[1].toInteger()
			}
			idarr.add	id_label

			keynum.times{
				valarr.add "${(20..40).random()}.${(0..99).random()}".toString()
			}
		}

	idstr=idarr.join(',').toString()
	valstr=valarr.join(',').toString()
	labelstr=labelarr.join(',').toString()


	xupdate="""
<xu:modifications version="1.0" xmlns:xu="http://www.xmldb.org/xupdate">
<xu:append select="//Projections[@projectionId='${_targetProjectionId}']" child="last()">
<Projection>
	<date>${date_str}</date>
	<time>${time_str}</time>
	<dayofweek>${day_of_week}</dayofweek>
"""
	idarr = idstr.tokenize(',')
	valarr = valstr.tokenize(',')
	labelarr=labelstr.tokenize(',')
	cnt = 0
	idarr.each{id->
		xupdate+="<Store id=\"${id}\" name=\"${id}\" label=\"${labelarr[cnt]}\">" 
		xupdate+='\n'
		valarr.each{val->
			xupdate+="<value datatype=\"double\" name=\"m_data\" timestamp=\"${tm_stamp}\">${valarr[cnt]}</value>" 
			xupdate+='\n'
		}
		xupdate+='\n'
		xupdate+="</Store>" 
		xupdate+='\n'
		cnt++
	}

xupdate+="""
</Projection>     
</xu:append>
</xu:modifications>
"""

	println "xupdate=${xupdate}"


	// XUpdate
	updateservice = col.getService("XUpdateQueryService", "1.0")
	updateservice.update(xupdate)
	updateservice=null
	col.close()

	//1 minitus add
	calendar.add(Calendar.MINUTE, 1)
	date = calendar.getTime()
}

