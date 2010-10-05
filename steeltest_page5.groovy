import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import eu.hansolo.steelseries.tools.*
import eu.hansolo.steelseries.gauges.*
import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;

class steeltest_page5{

	steeltest_page5(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraphGauge: LinearBargraph ,
        linearBargraphLcdGauge: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("linearBargraphGauge", LinearBargraph)
			registerBeanFactory("linearBargraphLcdGauge", LinearBargraphLcd)
			registerBeanFactory("led", Led)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				linearBargraphGauge(preferredSize: [300,150],value:50,
					barGraphColor:ColorDef.CYAN
				)
				linearBargraphLcdGauge(preferredSize: [150,300],value:50,
					barGraphColor:ColorDef.MAGENTA,
					orientation:javax.swing.SwingConstants.VERTICAL
				)
				led()
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

