import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*

class steeltest_page4{

	steeltest_page4(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")

			registerBeanFactory("radialBargraph1Gauge", RadialBargraph1)
			registerBeanFactory("radialBargraph1LcdGauge", RadialBargraph1Lcd)
			registerBeanFactory("radialBargraph2Gauge", RadialBargraph2)

			registerBeanFactory("radialBargraph2LcdGauge", RadialBargraph2Lcd)
			registerBeanFactory("radialBargraph3Gauge", RadialBargraph3)
			registerBeanFactory("radialBargraph3LcdGauge", RadialBargraph3Lcd)

			registerBeanFactory("radialBargraph4Gauge", RadialBargraph4)
			registerBeanFactory("radialBargraph4LcdGauge", RadialBargraph4Lcd)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialBargraph1Gauge(preferredSize: [300,300]) 
				radialBargraph1LcdGauge(preferredSize: [300,300])
				radialBargraph2Gauge(preferredSize: [300,300],)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialBargraph2LcdGauge(preferredSize: [300,300]) 
				radialBargraph3Gauge(preferredSize: [300,300]) 
				radialBargraph3LcdGauge(preferredSize: [300,300]) 
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialBargraph4Gauge(preferredSize: [300,300])
				radialBargraph4LcdGauge(preferredSize: [300,300])
			}

		}
	}

}

