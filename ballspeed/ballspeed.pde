PVector location;
PVector velocity;
float r = 8;
float d = r*2;

void setup(){
	size(500, 500);
	background(0);

	location = new PVector(100, 100);
	velocity = new PVector(2.5, 5);
}

void draw(){
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