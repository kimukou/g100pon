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
					unitString:'SEC',
					customBackground:java.awt.Color.MAGENTA,
					backgroundColorFromTheme:false //★ NEED!!
				) 

				radial2TopGauge(preferredSize: [300,300],value:20,
					unitString:'SEC',
					frameDesign:FrameDesign.BLACK_METAL
				)    // since 0.2.1 NEW★

				radial3Gauge(preferredSize: [300,300],
					unitString:'SEC',
					sectionsVisible :true,//★ NEED!!
					sections:[[0, 33, java.awt.Color.GREEN],[33, 66, java.awt.Color.YELLOW],[66, 100, java.awt.Color.RED]] as Section[] 
				)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radial3LcdGauge(id:'radial3Lcd',
					unitString:'SEC',
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
					trackStopColor:java.awt.Color.MAGENTA,
					trackSectionColor:java.awt.Color.PINK
				) 

/*
				radial4LcdGauge(preferredSize: [300,300],
					ledColor:LedColor.YELLOW_LED,
					pointerColor:PointerColor.WHITE,
					labelColor:java.awt.Color.GREEN,
					labelColorFromTheme:false				//★ NEED!!
				) 
*/

        //20100105 STAINLESS TEST add start=>
				radial4LcdGauge(
					id:'radial4LcdGauge',
					preferredSize: [300,300],
					frameDesign:FrameDesign.BLACK_METAL,
					backgroundColorFromTheme:false,
					labelColorFromTheme:false,				//★ NEED!!
					labelColor:java.awt.Color.BLACK,
					tickmarkColorFromTheme:false,			//★ NEED!!
					tickmarkColor:java.awt.Color.BLACK
				) 
				radial4LcdGauge.setCustomBackground(
						[
							false, 
							radial4LcdGauge.getCenter(), 
							-0.45f, 
							[
								0f,
						    0.03f,
						    0.10f,
						    0.14f,
						    0.24f,
						    0.33f,
						    0.38f,
						    0.5f,
						    0.62f,
						    0.67f,
						    0.76f,
						    0.81f,
						    0.85f,
						    0.97f,
						    1.0f 
							] as float[], 
							[
								new java.awt.Color(0xFDFDFD),
						    new java.awt.Color(0xFDFDFD),
						    new java.awt.Color(0xB2B2B4),
						    new java.awt.Color(0xACACAE),
						    new java.awt.Color(0xFDFDFD),
						    new java.awt.Color(0x6E6E70),
						    new java.awt.Color(0x6E6E70),
						    new java.awt.Color(0xFDFDFD),
						    new java.awt.Color(0x6E6E70),
						    new java.awt.Color(0x6E6E70),
						    new java.awt.Color(0xFDFDFD),
						    new java.awt.Color(0xACACAE),
						    new java.awt.Color(0xB2B2B4),
						    new java.awt.Color(0xFDFDFD),
						    new java.awt.Color(0xFDFDFD) 
							] as java.awt.Color[]
					] as ConicalGradientPaint
				)
        //20100105 STAINLESS TEST add end<=
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialCounterGauge(id:'radialCounter', preferredSize: [300,300],value:7) //since 0.2.1 NEW★
			}

		}
	}

}

