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

public class walker extends PApplet {

class Walker{
	int x;
	int y;

	Walker(){
		x = width/2;
		y = height/2;
	}

	public void display(){
		stroke(0);
		point(x, y);
	}

	public void step(){
		int stepx = PApplet.parseInt(random(3))-1;
		int stepy = PApplet.parseInt(random(3))-1;
		x += stepx;
		y += stepy;
	}
}

Walker w;

public void setup(){
	
	w = new Walker();
	background(255);
	filter(BLUR, 6);
}

public void draw(){
	w.step();
	w.display();
}
  public void settings() { 	size(640, 360, P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "walker" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
