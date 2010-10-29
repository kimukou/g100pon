import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.BoxLayout

//optional Clock.jar nessesary
import eu.hansolo.clock.*

//optional SignalTower.jar nessesary
import eu.hansolo.signaltower.*

//optional MButton.jar nessesary
import eu.hansolo.custom.mbutton.*

class steeltest_fun_comp{

	steeltest_fun_comp(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraph: LinearBargraph ,
        linearBargraphLcd: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("analogClock", AnalogClock)
			registerBeanFactory("backgroundPanel", nixieclock.BackgroundPanel)
			registerBeanFactory("nixieNumberScalable", nixieclock.NixieNumberScalable)
			registerBeanFactory("design42", Design42)

			registerBeanFactory("mButton", MButton)

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
				//using Clock.jar
				panel(){
					boxLayout(axis:BoxLayout.Y_AXIS)
					analogClock(preferredSize: [200,200],
						//luminosity:Luminosity.LIGHT,
						luminosity:Luminosity.DARK,
						autoLuminosity:false,	//Need apoint luminosity
						secondPointerVisible:true
					)

					//using NixieClock.jar
					backgroundPanel(preferredSize: [300,100]){
						boxLayout()
						nixieNumberScalable(
							preferredSize: [43, 73],//must need
							name:"hourLeft",
							number:1
						)
						nixieNumberScalable(
							preferredSize: [43, 73],
							name:"hourRight",
							number:6
						)
						nixieNumberScalable(
							preferredSize: [43, 73],
							name:"minLeft",
							number:4
						)
						nixieNumberScalable(
							preferredSize: [43, 73],
							name:"minRight",
							number:8
						)
						nixieNumberScalable(
							preferredSize: [43, 73],
							name:"secLeft",
							number:5
						)
						nixieNumberScalable(
							preferredSize: [43, 73],
							name:"secRight",
							number:0
						)
					}
				}

				//using SignalTower.jar
				design42(preferredSize: [100,200],
							redOn:true
				)
				design42(preferredSize: [100,200],
							yellowOn:true
				)
				design42(preferredSize: [100,200],
							greenOn:true
				)

				//using MButton.jar
				panel(){
					boxLayout(axis:BoxLayout.Y_AXIS)
					mButton(text:"homepage"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.RED
						,effectColorPressed:Color.RED
					)
					mButton(text:"about me"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.BLUE
						,effectColorPressed:Color.BLUE
					)
					mButton(text:"services"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.GREEN
						,effectColorPressed:Color.GREEN
					)
					mButton(text:"portfolio"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.ORANGE 
						,effectColorPressed:Color.ORANGE 
					)
					mButton(text:"contact"
						,preferredSize: [100,20]
						,alpha:0.0f
						,effectColor:Color.CYAN 
						,effectColorPressed:Color.CYAN 
					)
				}
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
			}

			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout()
			}

		}
	}

}

