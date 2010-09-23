// #g100pon test

import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*

//@Grab(group = 'org.pushing-pixels', module='trident', version='1.2')
count = 0
new SwingBuilder().edt {
  frame(title:'Frame', size:[300,300], show: true) {
    borderLayout()
    textlabel = label(text:"Click the button!", constraints: BL.NORTH)
    button(text:'Click Me',
         actionPerformed: {count++; textlabel.text = "Clicked ${count} time(s)."; println "clicked"},
         constraints:BL.CENTER)

	//plugin use steelseries.jar,trident-1.3.jar
	registerBeanFactory("radial3LcdGauge", Radial3.class)
	radial3LcdGauge(id:'radial3Lcd',constraints:BL.SOUTH,
					preferredSize: [300,300],
					areaColor:java.awt.Color.CYAN,
					areaStart:40,
					areaStop:70,		
					areaVisible:true	//★ NEED!!
	) 
/*
   	Radial3 radial3 = new Radial3();
   	radial3.setAreaColor(Color.CYAN);
   	radial3.setAreaStart(40);
   	radial3.setAreaStop(70);
   	radial3.setAreaVisible(true);
	this.add(radial3,BL.SOUTH)
*/
  }
}

