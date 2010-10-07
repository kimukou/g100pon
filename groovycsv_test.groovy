import com.xlson.groovycsv.*

println "args=${args}"
if(args.length < 1){
	println "==== args is 0 [String sample run]===="

	def csv = '''Name,Lastname
	Mark,Andersson
	Pete,Hansen'''

	def data = new CsvParser().parse(csv)
	data.each{
	    println it
	}
	//data.close()	//Exception occur. specification ?
	return
}
def input=new FileInputStream(args[0])
def is=new InputStreamReader(input, "UTF-8")

def data = new CsvParser().parse([separator:',',quoteChar:'"'],is)
//def data = new CsvParser().parse(is)
println "[0]data=${data.dump()}"
data.each{
    println it
}
println "[1]data=${data.dump()}"
//data.close()		//Exception occur . specification ?
println "[2]data=${data.dump()}"
