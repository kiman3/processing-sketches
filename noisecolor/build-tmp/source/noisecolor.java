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

public class noisecolor extends PApplet {

float inc = 0.06f;
int density = 1;
float znoise = 0.0f;

public void setup() {

noStroke();
colorMode(HSB, 256);
frameRate(24);
}
 
public void draw() {
	background(0);
	float xnoise = 0.0f;
	float ynoise = 0.0f;
	for (int y = 0; y < height; y += density) {
		for (int x = 0; x < width; x += density) {
			float n = noise(xnoise, ynoise, znoise) * 256;
			fill(255, 0, n); 
			rect(y, x, density, density);
			xnoise += inc;
		}
		xnoise = 0;
		ynoise += inc;
	}
	znoise += inc;
}
  public void settings() { 
size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "noisecolor" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
