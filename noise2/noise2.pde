float noiseScale = 0.01;

void setup(){
	size(640, 360);
}

void draw() {
	smooth(4);
	background(50);
	for (int x=0; x < width; x++) {
	float noiseVal = noise((mouseX+x)*noiseScale, mouseY*noiseScale);
		stroke(255);
		point(x, mouseY+noiseVal*80);
	}
}