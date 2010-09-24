import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*

class steeltest_page3{

	steeltest_page3(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")

			registerBeanFactory("radial2LcdGauge", Radial2Lcd)
			registerBeanFactory("radial2TopGauge", Radial2Top)
			registerBeanFactory("radial3Gauge", Radial3)

			registerBeanFactory("radial3LcdGauge", Radial3Lcd)
			registerBeanFactory("radial4Gauge", Radial4)
			registerBeanFactory("radial4LcdGauge", Radial4Lcd)

			registerBeanFactory("radialCounterGauge", RadialCounter)


			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radial2LcdGauge(preferredSize: [300,300],
					customBackground:java.awt.Color.MAGENTA,
					,backgroundColorFromTheme:false //★ NEED!!
				) 

				radial2TopGauge(preferredSize: [300,300],value:20)    //NEW★

				radial3Gauge(preferredSize: [300,300],
							sectionsVisible :true,//★ NEED!!
							sections:[[0, 33, java.awt.Color.GREEN],[33, 66, java.awt.Color.YELLOW],[66, 100, java.awt.Color.RED]] as Section[] 
				)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radial3LcdGauge(id:'radial3Lcd',
					preferredSize: [300,300],
					areaColor:java.awt.Color.CYAN,
					areaStart:40,
					areaStop:70,		
					areaVisible:true	//★ NEED!!
				) 

				radial4Gauge(preferredSize: [300,300],
					title:'hogehoge',unitString:'fuga',
					frameDesign:FrameDesign.BLACK_METAL,
					pointerColor:PointerColor.BLUE,
					backgroundColor:BackgroundColor.BEIGE,
					tickmarkColor:java.awt.Color.RED,
					tickmarkColorFromTheme:false,			//★ NEED!!
					trackStartColor:java.awt.Color.LIGHT_GRAY,
					trackSectionColor:java.awt.Color.PINK,
					trackStopColor:java.awt.Color.MAGENTA
				) 

				radial4LcdGauge(preferredSize: [300,300],
					ledColor:LedColor.YELLOW_LED,
					pointerColor:PointerColor.WHITE,
					labelColor:java.awt.Color.GREEN,
					labelColorFromTheme:false				//★ NEED!!
				) 
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialCounterGauge(id:'radialCounter', preferredSize: [300,300],value:7) //NEW★
			}

		}
	}

}

