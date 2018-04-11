Mover[] movers = new Mover[100];

void setup() {
	size(640,360);
	for (int i = 0; i < movers.length; i++){
		movers[i] = new Mover(random(0.1, 5), 0, 0);
	}
}

void draw() {
	background(255);

	PVector wind = new PVector(0.03,0);
	PVector gravity = new PVector(0,0.15);

	for (int i = 0; i < movers.length; i++) {
		movers[i].applyForce(wind);
		movers[i].applyForce(gravity);
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
		position = new PVector(x,y);
		velocity = new PVector(0,0);
		acceleration = new PVector(0,0);
		mass = m;
		size = m*10;
	}

	void applyForce(PVector force) {
		PVector f = PVector.div(force,mass);
		acceleration.add(f);
	}

	void update() {
		velocity.add(acceleration);
		position.add(velocity);
		acceleration.mult(0);
	}

	void display() {
		noStroke();
		fill(127);
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