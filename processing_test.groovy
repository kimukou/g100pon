//@Grab(group='geronimo', module='geronimo-installer-processing', version='1.1.1')

import processing.core.*

public class e2DProcessing extends PApplet {
	// Global variables
	float radius = 50.0f
	int X, Y
	int nX, nY
	int delay = 16

	// Setup the Processing Canvas
	public void setup(){
	  size( 200, 200 )
	  strokeWeight( 10 )
	  frameRate( 15 )
	  X = width / 2
	  Y = width / 2
	  nX = X
	  nY = Y  
	}

	// Main draw loop
	public void draw(){
	  
	  radius = radius + sin((float)( frameCount / 4 ))
	  
	  // Track circle to new destination
	  X+=(nX-X)/delay
	  Y+=(nY-Y)/delay
	  
	  // Fill canvas grey
	  background( 100 )
	  
	  // Set fill-color to blue
	  fill( 0, 121, 184 )
	  
	  // Set stroke-color white
	  stroke(255) 
	  
	  // Draw circle
	  ellipse( X, Y, radius, radius )                  
	}


	// Set circle's next destination
	public void mouseMoved(){
	  nX = mouseX
	  nY = mouseY  
	}
}


//■direct viewing
//PApplet.main([ "e2DProcessing" ] as String[]);


//■griffon like view
import groovy.swing.SwingBuilder
import javax.swing.WindowConstants as WC

def swingBuilder = new SwingBuilder()
swingBuilder.edt {
  frame(title:'steeltestFull', 
			size: [300,300],
			//pack: true,
  			location: [50,50],
  			resizable: true,
  			locationByPlatform:true,
			show: true,
			defaultCloseOperation:WC.EXIT_ON_CLOSE
	) 
	{
		pApplet2D = new e2DProcessing()
		pApplet2D.init()
		widget(pApplet2D)
	}
}
