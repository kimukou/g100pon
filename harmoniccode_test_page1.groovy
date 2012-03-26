import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.*


//optional SignalTower.jar nessesary
import eu.hansolo.signaltower.*

//optional MButton.jar nessesary
import eu.hansolo.custom.mbutton.*

//optional SteelCheckBox.jar nessesary
import eu.hansolo.custom.*
import eu.hansolo.tools.*

//optional RollingCounter.jar nessesary
import eu.hansolo.custom.*


class harmoniccode_test_page1{

	harmoniccode_test_page1(swingBuilder,_title)	{
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraph: LinearBargraph ,
        linearBargraphLcd: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("signalTower", Design42)
			registerBeanFactory("mbutton", MButton)
			registerBeanFactory("steelCheckBox", SteelCheckBox)
			registerBeanFactory("rollingCounter", Counter)



			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout(axis:BoxLayout.Y_AXIS)
				panel(){
					boxLayout(axis:BoxLayout.X_AXIS)

					//using SignalTower.jar
					signalTower(preferredSize: [100,200],
								redOn:true
					)
					signalTower(preferredSize: [100,200],
								yellowOn:true
					)
					signalTower(preferredSize: [100,200],
								greenOn:true
					)
				}
				//using MButton.jar
				panel(){
					boxLayout(axis:BoxLayout.Y_AXIS)
					mbutton(text:"homepage"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.RED
						,effectColorPressed:Color.RED
					)
					mbutton(text:"about me"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.BLUE
						,effectColorPressed:Color.BLUE
					)
					mbutton(text:"services"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.GREEN
						,effectColorPressed:Color.GREEN
					)
					mbutton(text:"portfolio"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.ORANGE 
						,effectColorPressed:Color.ORANGE 
					)
					mbutton(text:"contact"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.CYAN 
						,effectColorPressed:Color.CYAN 
					)
				}

				//using SteelCheckBox.jar
				panel(){
					boxLayout(axis:BoxLayout.Y_AXIS)
					steelCheckBox(
						text:"standard",
						preferredSize: [200,20]
						//ui
					)
					steelCheckBox(
						text:"green",
						preferredSize: [200,20],
						colored:true,
						rised:true,
						selectedColor:eu.hansolo.tools.ColorDef.GREEN
					)
					steelCheckBox(
						text:"red",
						preferredSize: [200,20],
						colored:true,
						rised:true,
						selectedColor:eu.hansolo.tools.ColorDef.RED
					)
					steelCheckBox(
						text:"disable",
						preferredSize: [200,20],
						enabled :false
					)
				}


				//using RollingCounter.jar
		    //BRIGHT,
		    //DARK,
		    //CUSTOM
				panel(){
					boxLayout(axis:BoxLayout.X_AXIS)
					rollingCounter(
						id:"rollingCounter1",
						preferredSize: [20,100],
						theme:Theme.BRIGHT,
						maxValue:3,
						switchTime:10,
						offsetIncrement:2,
						offsetDecrement:3
					)
					rollingCounter1.increment()
					rollingCounter(
						id:"rollingCounter2",
						preferredSize: [20,100],
						theme:Theme.DARK
					)
					rollingCounter2.decrement()
					rollingCounter(
						id:"rollingCounter3",
						preferredSize: [20,100],
						theme:Theme.CUSTOM,
						backgroundColor:new java.awt.Color(107, 105, 99, 255)
					)
				}
			}
		}
	}
}

