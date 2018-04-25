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

public class perlin extends PApplet {

float t = 0;

public void setup(){
	
	background(0);
}

public void draw(){
	float n = noise(t);
	float x = map(n, 0, 1, 0, width);
	fill(255, 10);
	noStroke();
	ellipse(x, height/2, 16, 16);

	t += 0.01f;
}
  public void settings() { 	size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "perlin" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
