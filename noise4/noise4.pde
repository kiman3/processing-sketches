float noiseScale = 0.01;
float t = 0;

void setup(){
	size(640, 360);
}

void draw() {
	smooth(4);
	background(50);
	float n = noise(t);
	for (int x=0; x < width; x++) {
	float noiseVal = noise((mouseX+x)*noiseScale, mouseY*noiseScale);
		stroke(255);
		line(x, n, x, height);
	}

	t += 0.01;
}