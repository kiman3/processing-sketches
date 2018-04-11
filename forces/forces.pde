Mover m;

void setup() {
	size(640,360);
	m = new Mover(); 
}

void draw() {
	background(255);

	PVector wind = new PVector(0.03,0);
	PVector gravity = new PVector(0,0.15);
	m.applyForce(wind);
	m.applyForce(gravity);

	m.update();
	m.display();
	m.checkEdges();
}