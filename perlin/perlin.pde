float t = 0;

void setup(){
	size(500, 500);
	background(0);
}

void draw(){
	float n = noise(t);
	float x = map(n, 0, 1, 0, width);
	fill(255, 10);
	noStroke();
	ellipse(x, height/2, 16, 16);

	t += 0.01;
}