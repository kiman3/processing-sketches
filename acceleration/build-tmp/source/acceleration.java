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

public class acceleration extends PApplet {

Mover mover;

public void setup(){
	background(255);
	
	mover = new Mover();
}

public void draw(){
	
	mover.update();
	mover.checkEdges();
	mover.display();
}

class Mover{
	PVector location;
	PVector velocity;
	PVector acceleration;
	float topspeed;

	Mover(){
		location = new PVector(width/2, height/2);
		velocity = new PVector(0, 0);
		acceleration = new PVector(-0.001f, 0.01f);
		topspeed = 10;
	}

	public void update(){
		acceleration = PVector.random2D();
		acceleration.mult(random(2));
		velocity.add(acceleration);
		velocity.limit(topspeed);
		location.add(velocity); // Motion 101, location changes by velocity
	}

	public void display(){
		stroke(0);
		fill(175);
		ellipse(location.x, location.y, 16, 16);
	}

	public void checkEdges(){
		if (location.x > width){
			location.x = 0;
		} else if (location.x < 0){
			location.x = width;
		}

		if (location.y > height){
			location.y = 0;
		} else if (location.y < 0){
			location.y = height;
		}
	}
}
  public void settings() { 	size(640, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "acceleration" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
