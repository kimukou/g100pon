import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*

class steeltest_page5{

	steeltest_page5(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraphGauge: LinearBargraph ,
        linearBargraphLcdGauge: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("linearBargraphGauge", LinearBargraph)
			registerBeanFactory("linearBargraphLcdGauge", LinearBargraphLcd)
			registerBeanFactory("led", Led)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				linearBargraphGauge(preferredSize: [300,150],value:50,
					barGraphColor:ColorDef.CYAN
				)
				linearBargraphLcdGauge(preferredSize: [150,300],value:50,
					barGraphColor:ColorDef.MAGENTA,
					orientation:javax.swing.SwingConstants.VERTICAL
				)
				led()
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
			}

		}
	}

}

