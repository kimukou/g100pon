import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.BoxLayout

//optional Clock.jar nessesary
import eu.hansolo.clock.*

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

			registerBeanFactory("analogClock", AnalogClock)

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
				
				//using Clock.jar
				analogClock(preferredSize: [200,200],
					//luminosity:Luminosity.LIGHT,
					luminosity:Luminosity.DARK,
					autoLuminosity:false,	//Need apoint luminosity
					secondPointerVisible:true
				)
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()

				registerBeanFactory("clock", Clock.class)

				clock(id:'clock',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
						backgroundColorFromTheme:false
				)
				clock(id:'clock2',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
						backgroundColorFromTheme:false
				)
				clock(id:'clock3',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
						backgroundColorFromTheme:false
				)
				clock(id:'clock4',preferredSize: [200,200],
						backgroundColor:BackgroundColor.WHITE,
						frameDesign:FrameDesign.SHINY_METAL,
						backgroundColorFromTheme:false
				)
				
				rect = clock.getBounds2D()
				println "rect=${rect}"
				clock.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

				rect = new Rectangle2D.Double(0.0d, 0.0d, 48.0d, 48.0d);
				println "rect=${rect}"
				clock2.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

				rect = new Rectangle2D.Double(22.0d, 22.0d, 160.0d, 160.0d);
				println "rect=${rect}"
				clock3.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

				rect = new Rectangle2D.Double(50.0d, 50.0d, 98.0d, 98.0d);
				println "rect=${rect}"
				clock4.setCustomBackground( new TexturePaint( ImageIO.read(new File('griffon-icon-48x48.png')), rect));

			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
			}

		}
	}

}

