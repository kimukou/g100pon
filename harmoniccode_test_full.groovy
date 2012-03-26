// #g100pon test

import groovy.lang.*
import org.codehaus.groovy.control.*
import groovy.swing.SwingBuilder
import javax.swing.WindowConstants as WC

//@Grab(group = 'org.pushing-pixels', module='trident', version='1.2')

def swingBuilder = new SwingBuilder()
swingBuilder.edt {
  frame(title:'HarmoniccodeFull', 
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
			ReloadUtil.getClass('harmoniccode_test_page1.groovy').newInstance(swingBuilder,"0.1 ver")
/*
			ReloadUtil.getClass('harmoniccode_test_page2.groovy').newInstance(swingBuilder,"0.2 ver")
			ReloadUtil.getClass('harmoniccode_test_other.groovy').newInstance(swingBuilder,"other")
*/
		}
  }
}


//Dynamic reloading class
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

