// #g100pon test

import groovy.lang.*
import org.codehaus.groovy.control.*
import groovy.swing.SwingBuilder
import javax.swing.WindowConstants as WC

//@Grab(group = 'org.pushing-pixels', module='trident', version='1.2')

def swingBuilder = new SwingBuilder()
swingBuilder.edt {
  frame(title:'steeltestFull', 
			//size: [900,900],
			pack: true,
  			location: [50,50],
  			resizable: true,
  			locationByPlatform:true,
			show: true,
			defaultCloseOperation:WC.EXIT_ON_CLOSE
	) 
	{
		tabbedPane(){
/*
			ReloadUtil.getClass('steeltest_page1.groovy').newInstance(swingBuilder,"page1")
			ReloadUtil.getClass('steeltest_page2.groovy').newInstance(swingBuilder,"page2")
			ReloadUtil.getClass('steeltest_page3.groovy').newInstance(swingBuilder,"page3")
			ReloadUtil.getClass('steeltest_page4.groovy').newInstance(swingBuilder,"page4")
			ReloadUtil.getClass('steeltest_page5.groovy').newInstance(swingBuilder,"page5")
*/
			ReloadUtil.getClass('steeltest_fun_comp.groovy').newInstance(swingBuilder,"fun_comp")
		}
  }
}


//動的ファイル再読込クラス
class ReloadUtil{
	private static Map<String, Class> loadMap = new HashMap<String, Class>()
	private static Map<String, Long> tmMap = new HashMap<String, Long>()

	private static def loader = null

	static Class getClass(String name){
		if(loader==null){
			ClassLoader parent = this.getClassLoader()
			CompilerConfiguration config = new CompilerConfiguration(sourceEncoding:'UTF-8')	//equals -Dgroovy.source.encoding=UTF-8
  		loader = new GroovyClassLoader(parent,config,false)
		}

		Class steelClass = null
		File fn = new File(name)
		long time = fn.lastModified()

		if(!loadMap.containsKey(name) || !tmMap.containsKey(name) || tmMap.get(name)!=time ){
			def source = new GroovyCodeSource(fn,"UTF-8")		//equals -Dfile.encoding=UTF-8
			//println source.scriptText
			steelClass = loader.parseClass(source)
			Class val = loadMap.get(name)
			if(val!=null)val.close()
			loadMap.put(name,steelClass)
			tmMap.put(name,time)
		}
		else{
			steelClass = loadMap.get(name)
		}
		return steelClass
	}
}

