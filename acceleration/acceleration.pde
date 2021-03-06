Mover mover;

void setup(){
	background(255);
	size(640, 360);
	mover = new Mover();
}

void draw(){
	
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
		acceleration = new PVector(-0.001, 0.01);
		topspeed = 10;
	}

	void update(){
		acceleration = PVector.random2D();
		acceleration.mult(random(2));
		velocity.add(acceleration);
		velocity.limit(topspeed);
		location.add(velocity); // Motion 101, location changes by velocity
	}

	void display(){
		stroke(0);
		fill(175);
		ellipse(location.x, location.y, 16, 16);
	}

	void checkEdges(){
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