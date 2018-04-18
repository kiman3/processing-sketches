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

public class ballspeed extends PApplet {

PVector location;
PVector velocity;
float r = 8;
float d = r*2;

public void setup(){
	
	background(0);

	location = new PVector(100, 100);
	velocity = new PVector(2.5f, 5);
}

public void draw(){
	background(0);
	fill(255);
	noStroke();

	location.add(velocity);

	if((location.x > width-r) || (location.x < r)){
		velocity.x = velocity.x * -1;
	}
	if((location.y > height-r) || (location.y < r)){
		velocity.y = velocity.y * -1;
	}

	ellipse(location.x, location.y, d, d);
}
  public void settings() { 	size(500, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "ballspeed" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
