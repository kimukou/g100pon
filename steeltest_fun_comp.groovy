
<!-- saved from url=(0071)https://raw.github.com/kimukou/g100pon/master/steeltest_fun_comp.groovy -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link type="text/css" rel="stylesheet" href="data:text/css,"></head><body><pre style="word-wrap: break-word; white-space: pre-wrap;">import groovy.swing.factory.LayoutFactory
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


//optional Gradients.jar nessesary
import eu.hansolo.gradients.*


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



			registerBeanFactory("canvasPanel", CanvasPanel)
			panel(){
				boxLayout(axis:BoxLayout.X_AXIS)
				//using FridayFunV.jar
				panel(){
					boxLayout(axis:BoxLayout.X_AXIS)
					ledPanel(
						preferredSize: [64,64],
						symbol:'libsteel/80.png',
						name:'ledMatrixPanel1',
						rasterStep:7	//5-10
					)
					canvasPanel(id:'canvas',preferredSize: [300,300])
        }
			}
			canvas.drawContourGradient()
		}
	}
}



//===========================================================
interface PaintOperation {
    void paint(Graphics g)
}

class CanvasPanel extends JPanel {
  private PaintOperation paintOperation

  public void setPaintOperation(PaintOperation paintOperation) {
     this.paintOperation = paintOperation
     if(isVisible()) {
        repaint()
     }
  }

  protected void paintComponent(Graphics g) {
     if(paintOperation != null) {
        Dimension size = getSize()
        g.clearRect((int)0, (int)0, (int)size.width, (int)size.height)
        Graphics2D g2 = (Graphics2D) g 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON)
        paintOperation.paint(g)
     }else{
        super.paintComponent(g)
     }
  }


  void drawContourGradient() {
				println "drawContourGradient"
	      this.setPaintOperation(new PaintOperation() {
	         public void paint(Graphics g) {
							println "drawContourGradient"
	            Graphics2D g2 = (Graphics2D) g 
              Rectangle2D box = new Rectangle2D.Double(0, 0, 500, 250)
              float[] fractions = [ 0.0f, 1.0f ]
              Color[] colors = [ Color.RED, Color.YELLOW ]
              ContourGradientPaint cgp =  
                  new ContourGradientPaint(box.getBounds(), fractions, colors)

              g2.setPaint(cgp)
              g2.fill(box)
	         }
	      })
	}

}




</pre></body><style type="text/css">embed[type*="application/x-shockwave-flash"],embed[src*=".swf"],object[type*="application/x-shockwave-flash"],object[codetype*="application/x-shockwave-flash"],object[src*=".swf"],object[codebase*="swflash.cab"],object[classid*="D27CDB6E-AE6D-11cf-96B8-444553540000"],object[classid*="d27cdb6e-ae6d-11cf-96b8-444553540000"],object[classid*="D27CDB6E-AE6D-11cf-96B8-444553540000"]{	display: none !important;}</style><link rel="stylesheet" type="text/css" href="data:text/css,"></html>