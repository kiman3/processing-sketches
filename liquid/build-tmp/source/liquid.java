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

public class liquid extends PApplet {

Mover[] movers = new Mover[9]; // Declare object
Liquid liquid; // Declare object

public void setup() {
	
	reset();
	liquid = new Liquid(0, height/2, width, height/2, 0.1f);
}

public void draw() {
	background(0);
	liquid.display();

	for (int i = 0; i < movers.length; i++) {

		// Is the Mover in the liquid?
		if (liquid.contains(movers[i])) {
			// Calculate drag force
			PVector dragForce = liquid.drag(movers[i]);
			// Apply drag force to Mover
			movers[i].applyForce(dragForce);
		}

		// Gravity force scaled by mass, Newton's second law
		PVector gravity = new PVector(0, 0.1f*movers[i].mass);
		// Apply gravity
		movers[i].applyForce(gravity);

		// Position, display and checking for edges
		movers[i].update();
		movers[i].display();
		movers[i].checkEdges();
	}
}

public void mousePressed() {
reset();
}

// Restart all the Mover objects randomly
public void reset() {
	for (int i = 0; i < movers.length; i++) {
		movers[i] = new Mover(random(0.5f, 3), 40+i*70, 0);
	}
}

class Mover {
	PVector position;
	PVector velocity;
	PVector acceleration;
	float mass;

	Mover(float m, float x, float y) {
		position = new PVector(x, y);
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		mass = m;
	}

	// Newton's 2nd law = F = M * A 
	// or A = F / M
	public void applyForce(PVector force) {
		// Divide by mass
		PVector f = PVector.div(force, mass);
		// Accumulate all forces in acceleration
		acceleration.add(f);
	}

	public void update() {
		velocity.add(acceleration);
		position.add(velocity);
		acceleration.mult(0);
	}

	public void display() {
		stroke(255, 80);
		strokeWeight(2);
		noFill();
		ellipse(position.x, position.y, mass*16, mass*16);
	}

	public void checkEdges() {
		if (position.y > height) {
			velocity.y *= -0.9f;  // A little dampening when hitting the bottom
			position.y = height;
		}
	}
}

class Liquid{
	// Liquid is a rectangle
	float x, y, w, h;
	// Coefficient of drag
	float c;

	Liquid(float x_, float y_, float w_, float h_, float c_){
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		c = c_;
	}

	// Is the Mover in the Liquid?
	public boolean contains(Mover m) {
		PVector l = m.position;
		return l.x > x && l.x < x + w && l.y > y && l.y < y + h;
	}

	// Calculate drag froce
	public PVector drag(Mover m){
		// Magnitude is coefficient * speed squared
		float speed = m.velocity.mag();
		float dragMagnitude = c * speed * speed;

		// Direction is inverse of velocity
		PVector dragForce = m.velocity.get();
		dragForce.mult(-1);

		// Scale accordring to magnitude
		// dragForce.setMag(dragMagnitude);
		dragForce.normalize();
		dragForce.mult(dragMagnitude);
		return dragForce;
	}

	public void display(){
		noStroke();
		fill(255, 20);
		rect(x, y, w, h);
	}
}
  public void settings() { 	size(640, 360); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "liquid" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
