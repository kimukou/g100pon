import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*
import java.awt.image.*
import javax.imageio.ImageIO
import java.awt.geom.*;

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
				clock(id:'clock',preferredSize: [200,200],
						//pointerColor:PointerColor.WHITE,
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL
				)
/*
				g = clock.backgroundImage.getGraphics()
				//g = clock.foregroundImage.createGraphics()
				
				//icon =swingBuilder.imageIcon('/griffon-icon-48x48.png').image
				icon = ImageIO.read(new File('griffon-icon-48x48.png'))
				g.drawImage(icon, 0,0, null)
				g.drawString("Sine Wave", 0, 0); // Draw some text
				g.dispose()
*/



				compass (id:'compass',preferredSize: [300,300])
				compass.setValueAnimated 90

				clock.CLOCK_TIMER.addActionListener([
					actionPerformed: { source -> 
						//println "clock == ${clock.hour}:${clock.minute} <${java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY)}:${clock.minute}>"
					}
				] as ActionListener)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				digitalRadialGauge (preferredSize: [300,300],value:50,
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),		//Font Change(to Verdana)
					useTitleAndUnitFont:true,										//Font Change(to Verdana)
					unitString:'秒'
				)
				digitalRadialLcdGauge (id:'lcdgauge',minValue:0,maxValue:100,lcdValue:50,value:80,
					valueColor:Color.RED,
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),		//Font Change(to Verdana)
					useTitleAndUnitFont:true,										//Font Change(to Verdana)
					title:'ほげほげ',
					unitString:'ふがふが',
					lcdUnitString:'まいう',
					preferredSize: [300,300])
/*
				displayCircular(id:'circular',minValue:0,maxValue:100,lcdValue:50,
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),			//Font Change(to Verdana)
					useTitleAndUnitFont:true,											//Font Change(to Verdana)
					title:'ほげほげ',unitString:'ふがふが',lcdUnitString:'まいう',
					preferredSize: [300,300])
*/
				displayCircular(id:'circular',value:15,lcdDecimals:3,
						customLcdUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),			//Font Change(to Verdana)
						useCustomLcdUnitFont:true,											//Font Change(to Verdana)
						lcdUnitString :'秒',
						lcdColor:LcdColor.ORANGE_LCD,
						backgroundColor:BackgroundColor.GREEN,
						frameDesign:FrameDesign.BLACK_METAL,
						digitalFont:true,
						preferredSize: [300,300])
				circular.setValueAnimated 100
/*
				displayCircular(id:'circular2',value:bind{model.count},lcdDecimals:0,
						unitString:'SEC',
						lcdColor:LcdColor.BLUEBLUE_LCD,
						backgroundColor:BackgroundColor.RED,
						frameDesign:FrameDesign.BLACK_METAL,
						digitalFont:true,preferredSize: [300,300]
				)
*/
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0),preferredSize: [420,80]){
				boxLayout()
				displayMulti(preferredSize: [140,40],value:30,
					customLcdUnitFont :new Font("ＭＳ ゴシック",Font.PLAIN,20),			//Font Change(to Verdana)
					useCustomLcdUnitFont :true,											//Font Change(to Verdana)
					unitString:'秒'
				)
				displayRectangular(preferredSize: [140,80],minimumSize: [140,80],value:50,
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),	//Font Change(to Verdana)
					useTitleAndUnitFont:true,									//Font Change(to Verdana)
					lcdUnitString:'sec'
					//lcdUnitString:'秒'
				) 
				displaySingle(preferredSize: [140,40],value:70,
					customLcdUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),			//Font Change(to Verdana)
					useCustomLcdUnitFont:true,											//Font Change(to Verdana)
					unitString:'秒'
				) 
			}

		}
	}

}

