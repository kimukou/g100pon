import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import eu.hansolo.steelseries.extras.* //since 2.1
import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.BoxLayout


class steeltest_page5{

	steeltest_page5(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraph: LinearBargraph ,
        linearBargraphLcd: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("linearBargraph", LinearBargraph)
			registerBeanFactory("linearBargraphLcd", LinearBargraphLcd)
			registerBeanFactory("led", Led)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				linearBargraph(preferredSize: [200,200],value:50,
					barGraphColor:ColorDef.CYAN
				)
				linearBargraphLcd(preferredSize: [200,200],value:50,
					barGraphColor:ColorDef.MAGENTA,
					orientation:javax.swing.SwingConstants.HORIZONTAL
				)
				panel(){
					boxLayout(axis:BoxLayout.Y_AXIS)
					led(preferredSize: [50,50],
						ledColor:LedColor.RED_LED,
						ledOn:true
					)
					led(preferredSize: [50,50],
						ledColor:LedColor.GREEN_LED,
						ledOn:false
					)
					led(preferredSize: [50,50],
						ledColor:LedColor.BLUE_LED,
						ledBlinking:true
					)
					led(preferredSize: [50,50],
						ledColor:LedColor.YELLOW_LED,
						ledBlinking:true,
						ledOn:false
					)
					led(preferredSize: [50,50],
						ledColor:LedColor.ORANGE_LED,
						ledBlinking:false,
						ledOn:true
					)
				}
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()

				registerBeanFactory("clock", Clock.class)

				clock(id:'clock',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
					customBackgroundVisible:true	//Åö NEED!!
				)
				clock(id:'clock2',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
					customBackgroundVisible:true	//Åö NEED!!
				)
				clock(id:'clock3',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
						customBackgroundVisible:true	//Åö NEED!!
				)
				clock(id:'clock4',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
						customBackgroundVisible:true	//Åö NEED!!
				)

				registerBeanFactory("radialBargraph1Lcd", RadialBargraph1Lcd)
				radialBargraph1Lcd(preferredSize: [300,300],
					barGraphColor:ColorDef.ORANGE,
					value:50,
					customLayerVisible:true,
					customLayer:ImageIO.read(new File('griffon-icon-48x48.png')),
					pointerType:eu.hansolo.steelseries.tools.PointerType.TYPE2
				)

				rect = clock.getBounds2D()
				//println "rect=${rect}"
				clock.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

				rect = new Rectangle2D.Double(0.0d, 0.0d, 48.0d, 48.0d);
				//println "rect=${rect}"
				clock2.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

				rect = new Rectangle2D.Double(22.0d, 22.0d, 160.0d, 160.0d);
				//println "rect=${rect}"
				clock3.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

				rect = new Rectangle2D.Double(50.0d, 50.0d, 98.0d, 98.0d);
				//println "rect=${rect}"
				clock4.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
			}

		}
	}

}

