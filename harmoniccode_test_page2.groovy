import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.*

//optional NixieClock.jar nessesary
import eu.hansolo.custom.*
import eu.hansolo.tools.*


//optional AnimatedProgress.jar nessesary



//optional StickyNote.jar nessesary
import eu.hansolo.stickynote.*

//optional RangeSlider.jar nessesary
import eu.hansolo.rangeslider.*


//optional FlipChar.jar nessesary
import eu.hansolo.flipchar.*

//semaphore


class harmoniccode_test_page2{

	harmoniccode_test_page2(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraph: LinearBargraph ,
        linearBargraphLcd: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("backgroundPanel", nixieclock.BackgroundPanel)
			registerBeanFactory("nixieNumber", nixieclock.NixieNumberScalable)
			registerBeanFactory("animatedProgress", AnimatedProgress)
			registerBeanFactory("rangeSlider", eu.hansolo.rangeslider.RangeSlider)
			registerBeanFactory("note", eu.hansolo.stickynote.Note)
			registerBeanFactory("flipChar", eu.hansolo.flipchar.FlipChar)
			//registerBeanFactory("semaphore", )

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout(axis:BoxLayout.Y_AXIS)

				//using NixieClock.jar
				backgroundPanel(preferredSize: [300,100]){
					boxLayout()
					nixieNumber(
						preferredSize: [43, 73],//must need
						name:"hourLeft",
						number:1
					)
					nixieNumber(
						preferredSize: [43, 73],
						name:"hourRight",
						number:6
					)
					nixieNumber(
						preferredSize: [43, 73],
						name:"minLeft",
						number:4
					)
					nixieNumber(
						preferredSize: [43, 73],
						name:"minRight",
						number:8
					)
					nixieNumber(
						preferredSize: [43, 73],
						name:"secLeft",
						number:5
					)
					nixieNumber(
						preferredSize: [43, 73],
						name:"secRight",
						number:0
					)
				}

				//use AnimatedProgressDemo.jar
				animatedProgress(
						id:'animeted',
						//size: [200, 30],
						preferredSize: [200, 50],
						maxValue: 100,
						value: 30,
						valueVisible:true,
						percentageVisible:true,
						tilted:true,
						roundedCorners:true,
						cornerRadius:4,
						//infinite:true,
						//infiniteText:"loading...",
						//infiniteTextVisible:true
						//color:new Color(204, 0, 0),
						//foregroundColor:new Color(0, 0, 153),
				)

			}
		}
	}
}


