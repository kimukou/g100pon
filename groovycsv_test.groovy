import com.xlson.groovycsv.*

/*
def csv = '''Name,Lastname
Mark,Andersson
Pete,Hansen'''

def data = new CsvParser().parse(csv)
data.each{
    println it
}
*/
println args
def input=new FileInputStream(args[0])
def is=new InputStreamReader(input, "UTF-8")

def data = new CsvParser().parse([separator:',',quoteChar:'"'],is)
//def data = new CsvParser().parse(is)
data.each{
    println it
}
data.close()
