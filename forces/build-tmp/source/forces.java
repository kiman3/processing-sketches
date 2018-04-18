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

public class forces extends PApplet {

Mover[] movers = new Mover[20]; // Declare object

public void setup() {
	
	
	for (int i = 0; i < movers.length; i++){
		movers[i] = new Mover(random(0.1f, 5), 0, random(0, height/2));
	}
}

public void draw() {
	background(0);

	for (int i = 0; i < movers.length; i++) {
		PVector wind = new PVector(0.01f, 0); // Wind force

		// Gravity force scaled by mass, Newton's second law
		float m = movers[i].mass;
		PVector gravity = new PVector(0, 0.1f*m);

		// Friction force
		float c = 0.01f;
		float normal = 1;
		float frictionMag = c*normal; // Magnitude of friction
		PVector friction = movers[i].velocity.get();
		friction.mult(-1);
		friction.normalize();
		friction.mult(frictionMag);

		// Applying forces
		movers[i].applyForce(friction);
		movers[i].applyForce(wind);
		movers[i].applyForce(gravity);

		// Position, display and checking for edges
		movers[i].update();
		movers[i].display();
		movers[i].checkEdges();
	}
}

class Mover {
	PVector position;
	PVector velocity;
	PVector acceleration;
	float mass;
	float massNormal;
	float size;

	Mover(float m, float x, float y) {
		position = new PVector(x, y);
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		mass = m;
		massNormal = map(m, 0.1f, 5, 1, 5); // Map for size calculation
		size = 50/massNormal; // Inverted size calc
	}

	public void applyForce(PVector force) {
		PVector f = PVector.div(force, mass);
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
		ellipse(position.x, position.y, size, size);
	}

	public void checkEdges() {
		if (position.x > width) {
			position.x = width;
			velocity.x *= -1;
		} else if (position.x < 0) {
			velocity.x *= -1;
			position.x = 0;
		}

		if (position.y > height) {
			velocity.y *= -1;
			position.y = height;
		}
	}
}
  public void settings() { 	size(640, 360); 	pixelDensity(2); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "forces" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
