import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.extras.* //since 2.1
import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*

import java.awt.*
import java.awt.event.*

class steeltest_page2{

	steeltest_page2(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")

			Radar.metaClass.add = { Poi poi ->
					delegate.addPoi(poi)
			}
			Radar.metaClass.leftShift = { Poi poi ->
					delegate.addPoi(poi)
			}
/*
			Poi.metaClass.setName = {String name ->
					delegate.NAME = name
			}
			registerBeanFactory("poi", Poi)
*/

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
					usingTitleAndUnitFont:true,										//Font Change(to Verdana)
					title:'はひふへほ～',
					//title:'hahihuheho',
					valueColor:ColorDef.GREEN
				)
				//println linear.dump()
/*
				linearLcdGauge(preferredSize: [300,300],value:50,
					valueColor:ColorDef.ORANGE
				) 
*/
				linearLcdGauge(
					id: 'invGauge', 
					backgroundColor: BackgroundColor.WHITE, 
					frameDesign: FrameDesign.SHINY_METAL, 
					valueColor: ColorDef.GREEN,
          ledColor: LedColor.GREEN_LED, 
					orientation: 0,
          title: 'Daily Investment (mx 35k)',
          maxValue: 350.0f,
          value: 30.0f,
          lcdUnitString: 'k')
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
/*
				poi(id:'RAITH',name:'Raith',lat:51.485605,lon:7.479544)
				poi(id:'HOME',name:'Home',lat:51.911784,lon:7.633789)
				poi(id:'MUENSTER',name:'Munster',lat:51.972502,lon:7.62989)
				poi(id:'ESSEN',name:'Essen',lat:51.462721,lon:7.015057)
				poi(id:'BOCHUM',name:'Bochum',lat:51.487526,lon:7.211781)
				poi(id:'WUPPERTAL',name:'Wuppertal',lat:51.260783,lon:7.149982)
*/
				//not better
				def RAITH = new Poi('Raith',51.485605,7.479544)
				def HOME = new Poi('Home',51.911784,7.633789)
				def MUENSTER = new Poi('Munster',51.972502,7.62989)
				def ESSEN = new Poi('Essen',51.462721,7.015057)
				def BOCHUM = new Poi('Bochum',51.487526,7.211781)
				def WUPPERTAL = new Poi('Wuppertal',51.260783,7.149982)

				radar(id:'radar',preferredSize: [300,300],
							range:70000,
							myLocation:RAITH
				)
				radar.add HOME
				radar.add MUENSTER
				radar.add ESSEN
				radar.add BOCHUM
				radar.add WUPPERTAL
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
					usingTitleAndUnitFont:true,										//Font Change(to Verdana)
					title:'cpu メータ',
					unitString:'パーセント',
					tickmarkSectionsVisible:true,
					tickmarkSections:[[10,20,Color.BLUE],[50,60,Color.GREEN]] as Section[],

					//how to use ?
					usingCustomTickmarkLabels:true,
					customTickmarkLabels:[0, 10, 50, 100] as Double[]  
				) 

				radial1VerticalGauge(id:'radial1Vertical',preferredSize: [300,300],
					customBackgroundVisible:true	//★ NEED!!
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
					customBackgroundVisible:true	//★ NEED!!
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

