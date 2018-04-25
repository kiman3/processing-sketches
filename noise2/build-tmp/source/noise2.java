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

public class noise2 extends PApplet {

float noiseScale = 0.01f;

public void setup(){
	
}

public void draw() {
	smooth(4);
	background(50);
	for (int x=0; x < width; x++) {
	float noiseVal = noise((mouseX+x)*noiseScale, mouseY*noiseScale);
		stroke(255);
		point(x, mouseY+noiseVal*80);
	}
}
  public void settings() { 	size(640, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "noise2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
