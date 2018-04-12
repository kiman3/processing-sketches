Mover[] movers = new Mover[20]; // Declare object
Liquid liquid; // Declare object

void setup() {
	size(640, 360);
	pixelDensity(2);
	for (int i = 0; i < movers.length; i++){
		movers[i] = new Mover(random(0.1, 5), 0, random(0, height/2));
	}
	liquid = new Liquid(0, height/2, width, height/2, 0.1);
}

void draw() {
	background(0);
	liquid.display();

	for (int i = 0; i < movers.length; i++) {

		if (movers[i].isInside(liquid)) {
			movers[i].drag(liquid);
		}

		// Gravity force scaled by mass, Newton's second law
		float m = movers[i].mass;
		PVector gravity = new PVector(0, 0.1*m);

		// Applying forces
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
	float size;

	Mover(float m, float x, float y) {
		position = new PVector(x, y);
		velocity = new PVector(0, 0);
		acceleration = new PVector(0, 0);
		mass = m;
		size = m*10;
	}

	void applyForce(PVector force) {
		PVector f = PVector.div(force, mass);
		acceleration.add(f);
	}

	void update() {
		velocity.add(acceleration);
		position.add(velocity);
		acceleration.mult(0);
	}

	void display() {
		stroke(255, 80);
		strokeWeight(2);
		noFill();
		ellipse(position.x, position.y, size, size);
	}

	void checkEdges() {
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

class Liquid{
	float x, y, w, h;
	float c;

	Liquid(float x_, float y_, float w_, float h_, float c_){
		x = x_;
		y = y_;
		w = w_;
		h = h_;
		c = c_;
	}

	void display(){
		noStroke();
		fill(175);
		rect(x, y, w, h);
	}
}

boolean isInside(Liquid l){
	if (location.x>l.x && location.x<l.x+l.w && location.y>l.y && location.y<l.y+l.h){
		return true;
	} else {
		return false;
	}
}

void drag(Liquid l){
	float speed = velocity.mag();
	float dragMagnitude = l.c * speed * speed;

	PVector drag = velocity.get();
	drag.mult(-1);
	drag.normalize();
	drag.mult(dragMagnitude);
	applyForce(drag);
}