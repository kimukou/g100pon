//	jxls-reader-test
//
// 	refarence http://d.hatena.ne.jp/waman/20110209/1297199326
//           http://d.hatena.ne.jp/ryoasai/20110215/1297782653
//

@Grab(group='net.sf.jxls', module='jxls-core', version='1.0-RC-1')
@Grab(group='net.sf.jxls', module='jxls-reader', version='1.0-RC-1')

def dataXLS="department.xls"

//■FileSelect
import javax.swing.*
import groovy.swing.SwingBuilder
import javax.swing.filechooser.FileFilter

def filter = [
                  getDescription: {-> "*.xls"},
                  accept:{ file-> file ==~ /.*?\.xls/ || file.isDirectory() }
             ] as FileFilter

def builder = new SwingBuilder()
def fc  = builder.fileChooser(dialogTitle:'Choose an XLS file', 
                                        fileSelectionMode:JFileChooser.FILES_ONLY, 
                                        fileFilter:filter)

if(fc.showOpenDialog() == JFileChooser.APPROVE_OPTION){
		dataXLS = fc.selectedFile.name
    println "You chose to open this file: $dataXLS" 
}
else{
		println "remain file file: $dataXLS"
}

//================================================================================
import java.io.*
import java.util.*
import net.sf.jxls.reader.*


public class Department {
    String name
    Employee chief = new Employee()
    List<Employee> staff = new ArrayList<Employee>()
}

public class Employee {
    String name
    int age
    Double payment
    Double bonus
    Date birthDate
    Employee superior
}


//■case 1 XmlRead
//xmlConfig="department.xml"
//inputXML = new BufferedInputStream(new FileInputStream(xmlConfig))


//■case 2 StringXmlRead
//<?xml version="1.0" encoding="utf-8"?>
xmlConfig = """
<workbook>
	<worksheet name="Sheet1">
		<section startRow="0" endRow="6">
			<mapping cell="B1">department.name</mapping>
			<mapping cell="A4">department.chief.name</mapping>
			<mapping cell="B4">department.chief.age</mapping>
			<mapping cell="D4">department.chief.payment</mapping>
			<mapping row="3" col="4">department.chief.bonus</mapping>
		</section>
		<loop startRow="7" endRow="7" items="department.staff" var="employee"
			varType="Employee">
			<section startRow="7" endRow="7">
				<mapping row="7" col="0">employee.name</mapping>
				<mapping row="7" col="1">employee.age</mapping>
				<mapping row="7" col="3">employee.payment</mapping>
				<mapping row="7" col="4">employee.bonus</mapping>
			</section>
			<loopbreakcondition>
				<rowcheck offset="0">
					<cellcheck offset="0">Employee Payment Totals:</cellcheck>
				</rowcheck>
			</loopbreakcondition>
		</loop>
	</worksheet>
</workbook>
"""
inputXML = new BufferedInputStream(new ByteArrayInputStream(xmlConfig.getBytes("UTF-8")))

//same route
XLSReader mainReader = ReaderBuilder.buildFromXML(inputXML)

//xls read
inputXLS = new BufferedInputStream(new FileInputStream(dataXLS))

Map model =  new HashMap()
Department department = new Department()
model.put("department",department)
mainReader.read(inputXLS, model)

println department.dump()
println department.getChief().dump()
println department.getStaff().dump()
