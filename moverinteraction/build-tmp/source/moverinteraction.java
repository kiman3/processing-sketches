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

public class moverinteraction extends PApplet {

Mover[] movers = new Mover[20];

public void setup(){
	background(255);
	
	for (int i = 0; i < movers.length; i++){
		movers[i] = new Mover();
	}
}

public void draw(){
	background(255);

	for (int i = 0; i < movers.length; i++) {
		movers[i].update();
		movers[i].checkEdges();
		movers[i].display();
	}
}

class Mover{
	PVector location;
	PVector velocity;
	PVector acceleration;
	float topspeed;

	Mover(){
		location = new PVector(random(width), random(height));
		velocity = new PVector(0, 0);
		topspeed = 4;
	}

	public void update(){
		PVector mouse = new PVector(mouseX, mouseY);
		PVector dir = PVector.sub(mouse, location);
		dir.normalize();
		dir.mult(0.25f);
		acceleration = dir;
		velocity.add(acceleration);
		velocity.limit(topspeed);
		location.add(velocity);
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
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "moverinteraction" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
