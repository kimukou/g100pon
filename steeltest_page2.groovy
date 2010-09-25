import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*

class steeltest_page2{

	steeltest_page2(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")


			registerBeanFactory("level", Level)
			registerBeanFactory("linearGauge", Linear)
			registerBeanFactory("linearLcdGauge", LinearLcd)

			registerBeanFactory("radar", Radar)
			registerBeanFactory("radial1Gauge", Radial1)
			registerBeanFactory("radial1LcdGauge", Radial1Lcd)

			registerBeanFactory("radial1SquareGauge", Radial1Square)
			registerBeanFactory("radial1VerticalGauge", Radial1Vertical)
			registerBeanFactory("radial2Gauge", Radial2)


			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				level (preferredSize: [300,300],value:50)
				linearGauge(id:'linear',preferredSize: [300,300],value:50,
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),		//Font Change(to Verdana)
					useTitleAndUnitFont:true,										//Font Change(to Verdana)
					title:'はひふへほ～',
					//title:'hahihuheho',
					valueColor:ColorDef.GREEN
				)
				//println linear.dump()
				linearLcdGauge(preferredSize: [300,300],value:50,
					valueColor:ColorDef.ORANGE
				) 
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radar(id:'radar',preferredSize: [300,300])
				//poi(name:'hogehoge',lat:30,lon:70)
				//poi(id:'hogehoge',name:'hogehoge',lat:30,lon:70)
				//radar.add(hogehoge)
				radar.animate() 

				radial1Gauge(id:'radial1',preferredSize: [300,300],minValue:0,maxValue:10000,
					scaleDividerPower:3
					//trackVisible :true,
					//trackSection :100
				)
				 
				radial1LcdGauge(id:'radial1Lcd',preferredSize: [300,300],value:bind{model.cpu_usage},
					thresholdVisible:true,
					threshold:50,
					minMeasuredValueVisible :true,
					maxMeasuredValueVisible :true
				) 
				radial1Lcd.resetMinMeasuredValue 10
				radial1Lcd.resetMaxMeasuredValue 70
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				//TickmarkSection test
				radial1SquareGauge(id:'squaregauge',preferredSize: [300,300],
					minValue:0,
					maxValue:100,
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),		//Font Change(to Verdana)
					useTitleAndUnitFont:true,										//Font Change(to Verdana)
					title:'cpu メータ',
					unitString:'パーセント',
					tickmarkSectionsVisible:true,
					tickmarkSections:[[10,20,Color.BLUE],[50,60,Color.GREEN]] as Section[],

					//how to use ?
					useCustomTickmarkLabels:true,
					customTickmarkLabels:[0, 10, 50, 100] as Double[]  
				) 

				radial1VerticalGauge(id:'radial1Vertical',preferredSize: [300,300],
					backgroundColorFromTheme:false	//★ NEED!!
				) 
				radial1Vertical.setCustomBackground(
					new java.awt.LinearGradientPaint(
						new java.awt.geom.Point2D.Double(radial1Vertical.getBounds2D().minX + 20, radial1Vertical.getBounds2D().minY + 20), 
						new java.awt.geom.Point2D.Double(radial1Vertical.getBounds2D().maxX - 20, radial1Vertical.getBounds2D().maxY - 20), 
						[0.0f,0.25f,0.5f,0.75f,1.0f ] as float[], 
						[java.awt.Color.MAGENTA,java.awt.Color.YELLOW,java.awt.Color.GREEN,java.awt.Color.BLUE,java.awt.Color.RED] as java.awt.Color[]
					)
				)

				radial2Gauge(id:'radial2',preferredSize: [300,300],
					backgroundColorFromTheme:false	//★ NEED!!
				) 
				radial2.setCustomBackground(
					new java.awt.RadialGradientPaint(
						//radial2.center, 
						radial1Vertical.center, 
						(float)(radial2.getBounds2D().width * 0.4f), 
						[0.0f,0.25f,0.5f,0.75f,1.0f ] as float[], 
						[java.awt.Color.MAGENTA,java.awt.Color.YELLOW,java.awt.Color.GREEN,java.awt.Color.BLUE,java.awt.Color.RED] as java.awt.Color[]
					)
				)
			}
		}

	}

}

