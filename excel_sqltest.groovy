// A reference page
//   Excell and Groovy SQL
//    http://forums.pragprog.com/forums/55/topics/1302
//
//   and use xlsql
//	   http://sourceforge.net/projects/xlsql/files/
//
//     first line is colum,but english only


import groovy.sql.Sql

//@Grab(group='org.apache.poi', module='poi', version='3.7-beta2')

/*
def sql = groovy.sql.Sql.newInstance("""
	jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)}; 
	DBQ=${source};READONLY=false""", '', '', 'sun.jdbc.odbc.JdbcOdbcDriver')
*/

def db = [url:"jdbc:nilostep:excel:${System.getProperty("user.dir")}", user:'', password:'', driver:'com.nilostep.xlsql.jdbc.xlDriver']
def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)

println "000" 
def cnt=0
sql_str ="SELECT * FROM \"sample1.Sheet1\" "
sql.eachRow(sql_str) {
    println "${cnt}:${it}" 
	cnt++
}


/*
sql_str = "DROP TABLE \"sample1.Sheet1\" IF EXISTS;"
sql_str = "CREATE TABLE \"sample1.Sheet1\" (shop varchar,month1 varchar,month2 varchar,month3 varchar,sum varchar);"
sql_str = "INSERT INTO \"sample1.Sheet1\" VALUES ('ìåêºìX','345','112','765');"
sql_str = "select count(*) from \"sample1.Sheet1\" "
*/

