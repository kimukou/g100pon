import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.*

//optional Clock.jar nessesary
import eu.hansolo.clock.*

//optional SignalTower.jar nessesary
import eu.hansolo.signaltower.*

//optional MButton.jar nessesary
import eu.hansolo.custom.mbutton.*

//optional SteelCheckBox.jar nessesary
import eu.hansolo.custom.*
import eu.hansolo.tools.*

//optional RollingCounter.jar nessesary
import eu.hansolo.custom.*

//optional ContourGradientPaint.jar nessesary
import eu.hansolo.ContourGradientPaint

//optional FridayFunV.jar nessesary
import eu.hansolo.ledpanel.*


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
			registerBeanFactory("signalTower", Design42)
			registerBeanFactory("mbutton", MButton)
			registerBeanFactory("steelCheckBox", SteelCheckBox)
			registerBeanFactory("rollingCounter", Counter)
			registerBeanFactory("ledPanel", LedPanel)



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
				signalTower(preferredSize: [100,200],
							redOn:true
				)
				signalTower(preferredSize: [100,200],
							yellowOn:true
				)
				signalTower(preferredSize: [100,200],
							greenOn:true
				)

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
						preferredSize: [100,20]
						//ui
					)
					steelCheckBox(
						text:"green",
						preferredSize: [100,20],
						colored:true,
						rised:true,
						selectedColor:eu.hansolo.tools.ColorDef.GREEN
					)
					steelCheckBox(
						text:"red",
						preferredSize: [100,20],
						colored:true,
						rised:true,
						selectedColor:eu.hansolo.tools.ColorDef.RED
					)
					steelCheckBox(
						text:"disable",
						preferredSize: [100,20],
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
			registerBeanFactory("testPanel", TestPanel)
			panel(){
				boxLayout(axis:BoxLayout.X_AXIS)
/*
				widget(new Canvas(), paint: {c, g ->  
					def r=getBounds()
					g.setColor(Color.black)
					g.fillRect(0, 0, (Integer) r.width, (Integer) r.height)

					Graphics2D g2 = (Graphics2D)g

					Rectangle2D box = new Rectangle2D.Double(0, 0, 500, 250)
					float[] fractions = [ 0.0f, 1.0f ]
					Color[] colors = [ Color.RED, Color.YELLOW ]
					ContourGradientPaint cgp = new ContourGradientPaint(box.getBounds(), fractions, colors)
					g2.setPaint(cgp)
				})
*/
				//using FridayFunV.jar
				panel(){
					boxLayout(axis:BoxLayout.X_AXIS)
					ledPanel(
						preferredSize: [300,300],
						symbol:'libsteel/80.png',
						name:'ledMatrixPanel1',
						rasterStep:7	//5-10
					)
				}

			}
		}
	}
}
// refarence http://www.jroller.com/aalmiray/?cat=Groovy&date=200907
import java.awt.Graphics  
import javax.swing.JComponent  
  
class Canvas extends JComponent {  
  Closure paint  
  public void paintComponent(Graphics g) {  
    if(paint) paint(this, g)  
  }  
}

class TestPanel extends JPanel {
	//Rectangle r = new Rectangle(500,250)

	@Override
  public void paintComponent(Graphics g) {
			def r=getBounds()
    	g.setColor(Color.black)
    	g.fillRect(0, 0, (Integer) r.width, (Integer) r.height)

			Graphics2D g2 = (Graphics2D)g

			Rectangle2D box = new Rectangle2D.Double(0, 0, 500, 250)
			float[] fractions = [ 0.0f, 1.0f ]
			Color[] colors = [ Color.RED, Color.YELLOW ]
			ContourGradientPaint cgp = new ContourGradientPaint(box.getBounds(), fractions, colors)
			g2.setPaint(cgp)
//println g2.getPaint().dump()
			g2.fill(box)
//println g2.dump()

/*
			def box = new Rectangle2D.Double(0, 0, 500, 250);
			def cgp =  new ContourGradientPaint(
				box.getBounds(), 
				[0.0f, 1.0f] as float[],
				[Color.RED, Color.YELLOW] as Color[] 
			)
			g2.setPaint(cgp)
			g2.fill(box)
*/
  }
}


