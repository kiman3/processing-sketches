import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class noise1 extends PApplet {

public void setup(){
	
}

public void draw(){
	loadPixels();	

	float xoff = 0.0f;

	for (int x = 0; x < width; x++){
		float yoff = 0.0f;

		for (int y = 0; y < height; y++){
			float bright = map(noise(xoff, yoff), 0, 1, 0, 255);
			pixels[x+y*width] = color(bright);
			yoff += 0.01f;
		}
		xoff += 0.01f;
	}
	updatePixels();
}
  public void settings() { 	size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "noise1" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
