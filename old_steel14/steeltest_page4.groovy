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
/*

        radialBargraph1: RadialBargraph1,
        radialBargraph1Lcd: RadialBargraph1Lcd,
        radialBargraph2: RadialBargraph2,
        radialBargraph2Lcd: RadialBargraph2Lcd,
        radialBargraph3: RadialBargraph3,
        radialBargraph3Lcd: RadialBargraph3Lcd,
        radialBargraph4: RadialBargraph4,
        radialBargraph4Lcd: RadialBargraph4Lcd,
*/
			registerBeanFactory("radialBargraph1", RadialBargraph1)
			registerBeanFactory("radialBargraph1Lcd", RadialBargraph1Lcd)
			registerBeanFactory("radialBargraph2", RadialBargraph2)

			registerBeanFactory("radialBargraph2Lcd", RadialBargraph2Lcd)
			registerBeanFactory("radialBargraph3", RadialBargraph3)
			registerBeanFactory("radialBargraph3Lcd", RadialBargraph3Lcd)

			registerBeanFactory("radialBargraph4", RadialBargraph4)
			registerBeanFactory("radialBargraph4Lcd", RadialBargraph4Lcd)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialBargraph1(preferredSize: [300,300],
					barGraphColor:ColorDef.RED,
					value:50,
					customBackground:java.awt.Color.MAGENTA,
					backgroundColorFromTheme:false //★ NEED!!
				) 
				radialBargraph1Lcd(preferredSize: [300,300],
					barGraphColor:ColorDef.ORANGE,
					value:50
				)
				//■section color not action
				radialBargraph2(preferredSize: [300,300],
					barGraphColor:ColorDef.YELLOW,
					value:50,
					sectionsVisible :true,//★ NEED!!
					sections:[[0, 33, java.awt.Color.GREEN],[33, 66, java.awt.Color.YELLOW],[66, 100, java.awt.Color.RED]] as Section[] 
				)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				//■aria color not action
				radialBargraph2Lcd(preferredSize: [300,300],
					barGraphColor:ColorDef.GREEN,
					value:50,
					areaColor:java.awt.Color.CYAN,
					areaStart:40,
					areaStop:70,		
					areaVisible:true	//★ NEED!!
				) 
				radialBargraph3(preferredSize: [300,300],
					barGraphColor:ColorDef.BLUE,
					value:50,
					title:'hogehoge',unitString:'fuga',
					frameDesign:FrameDesign.BLACK_METAL,
					backgroundColor:BackgroundColor.BEIGE,
					tickmarkColor:java.awt.Color.RED,
					tickmarkColorFromTheme:false,			//★ NEED!!
					trackStartColor:java.awt.Color.LIGHT_GRAY,
					trackSectionColor:java.awt.Color.PINK,
					trackStopColor:java.awt.Color.MAGENTA
				) 
				radialBargraph3Lcd(preferredSize: [300,300],
					barGraphColor:ColorDef.RAITH,
					value:50,
					ledColor:LedColor.YELLOW_LED,
					pointerColor:PointerColor.WHITE,
					labelColor:java.awt.Color.GREEN,
					labelColorFromTheme:false				//★ NEED!!
				) 
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				radialBargraph4(preferredSize: [300,300],
					barGraphColor:ColorDef.GREEN_LCD,
					value:50
				)
				radialBargraph4Lcd(preferredSize: [300,300],
					titleAndUnitFont:new Font("ＭＳ ゴシック",Font.PLAIN,20),		//Font Change(to Verdana)
					useTitleAndUnitFont:true,										//Font Change(to Verdana)
					title:'ほげほげ',
					unitString:'ふがふが',
					lcdUnitString:'まいう',
					barGraphColor:ColorDef.JUG_GREEN,
					value:50
				)
			}

		}
	}

}

