import groovy.swing.factory.LayoutFactory
import net.miginfocom.swing.MigLayout

import java.awt.*
import java.awt.event.*
import javax.imageio.ImageIO
import java.awt.geom.*;
import javax.swing.*

//optional Clock.jar nessesary
import eu.hansolo.clock.*

//optional ContourGradientPaint.jar nessesary
import eu.hansolo.ContourGradientPaint

//optional FridayFunV.jar nessesary
import eu.hansolo.ledpanel.*


//optional Gradients.jar nessesary
import eu.hansolo.gradients.*

//optional FridayFunVIII.jar
import eu.hansolo.bubblepanel.BubblePanel


//optional LightBulb.jar
import eu.hansolo.lightbulb.*

//optional TrafficLight.jar
import trafficlight.TrafficLight


class harmoniccode_test_other{

	harmoniccode_test_other(swingBuilder,_title){
		swingBuilder.panel(title:_title){
			registerFactory("migLayout", new LayoutFactory(MigLayout))
			migLayout(layoutConstraints: "gap 0,insets 0,fill")
/*
        linearBargraph: LinearBargraph ,
        linearBargraphLcd: LinearBargraphLcd,
        led: Led,
*/
			registerBeanFactory("analogClock", AnalogClock)
			registerBeanFactory("ledPanel", LedPanel)
			registerBeanFactory("bubblePanel", BubblePanel)
			registerBeanFactory("lightBulb", LightBulb)
			registerBeanFactory("trafficLight", TrafficLight)


			swingBuilder.panel(constraints: "span,wrap, gapbottom 0,gaptop 0",border: emptyBorder(0)){
				boxLayout(axis:BoxLayout.Y_AXIS)
				panel(){
					boxLayout(axis:BoxLayout.X_AXIS)
					//using Clock.jar
					analogClock(preferredSize: [100,100],
						//luminosity:Luminosity.LIGHT,
						luminosity:Luminosity.DARK,
						autoLuminosity:false,	//Need apoint luminosity
						secondPointerVisible:true
					)

					//using FridayFunV.jar
					ledPanel(
						size: [100,100],
						preferredSize: [100,100],
						symbol:'libsteel/80.png',
						name:'ledMatrixPanel1',
						rasterStep:7	//5-10
					)
				}
				registerBeanFactory("canvasPanel", CanvasPanel)
				canvasPanel(id:'canvas',
									size: [100,200],
									preferredSize: [300,300])
				canvas.drawContourGradient()

				bubblePanel(
					preferredSize: [100, 100],
					background:new Color(0, 0, 51),
					border:emptyBorder(6),constraints:BorderLayout.SOUTH
				){
						
				}


		 }
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




