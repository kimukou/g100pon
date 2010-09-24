import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*

class steeltest_page1{

	steeltest_page1(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")


			registerBeanFactory("altimeter", Altimeter.class)
			registerBeanFactory("clock", Clock.class)
			registerBeanFactory("compass", Compass.class)

			registerBeanFactory("digitalRadialGauge", DigitalRadial.class)
			registerBeanFactory("digitalRadialLcdGauge", DigitalRadialLcd.class)
			registerBeanFactory("displayCircular", DisplayCircular.class)

			registerBeanFactory("displayMulti", DisplayMulti.class)
			registerBeanFactory("displayRectangular", DisplayRectangular.class)
			registerBeanFactory("displaySingle", DisplaySingle.class)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				label(text:'1  ')

				altimeter(preferredSize: [300,300])
				clock(id:'clock',preferredSize: [300,300],
							backgroundColor:BackgroundColor.WHITE,
							frameDesign:FrameDesign.SHINY_METAL)
				//g = clock.backgroundImage.getGraphics()
				g = clock.foregroundImage.createGraphics()
				icon = swingBuilder.imageIcon('/griffon-icon-48x48.png').image
				g.drawImage(icon, 0,0, null)
				g.drawString("Sine Wave", 0, 0); // Draw some text
				g.dispose()

				compass (id:'compass',preferredSize: [300,300])
				compass.setValueAnimated 90

				clock.CLOCK_TIMER.addActionListener([
						actionPerformed: { source -> 
								println "clock == ${clock.hour}:${clock.minute} <${java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)}:${clock.minute}>"
						}
				] as ActionListener)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				digitalRadialGauge (preferredSize: [300,300])
				digitalRadialLcdGauge (id:'lcdgauge',minValue:0,maxValue:100,lcdValue:50,
						title:'ほげほげ',
						unitString:'ふがふが',
						lcdUnitString:'まいう',
						preferredSize: [300,300])
				//displayCircular(id:'circular',minValue:0,maxValue:100,lcdValue:50,title:'ほげほげ',unitString:'ふがふが',lcdUnitString:'まいう',preferredSize: [300,300])
				displayCircular(id:'circular',value:0,lcdDecimals:3,unitString:'SEC',
						lcdColor:LcdColor.ORANGE_LCD,
						backgroundColor:BackgroundColor.GREEN,
						frameDesign:FrameDesign.BLACK_METAL,
						digitalFont:true,
						preferredSize: [300,300])
				circular.setValueAnimated 100
/*
				displayCircular(id:'circular2',value:bind{model.count},lcdDecimals:0,unitString:'SEC',
						lcdColor:LcdColor.BLUEBLUE_LCD,
						backgroundColor:BackgroundColor.RED,
						frameDesign:FrameDesign.BLACK_METAL,
						digitalFont:true,preferredSize: [300,300]
				)
*/
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				displayMulti(preferredSize: [140,40])
				displayRectangular(preferredSize: [140,60],minimumSize: [140,60]) 
				displaySingle(preferredSize: [140,40]) 
			}

		}
	}

}

