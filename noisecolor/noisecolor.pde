float inc = 0.06;
int density = 1;
float znoise = 0.0;

void setup() {
size(500, 500);
noStroke();
colorMode(HSB, 256);
frameRate(24);
}
 
void draw() {
	background(0);
	float xnoise = 0.0;
	float ynoise = 0.0;
	for (int y = 0; y < height; y += density) {
		for (int x = 0; x < width; x += density) {
			float n = noise(xnoise, ynoise, znoise) * 256;
			fill(255, 0, n); 
			rect(y, x, density, density);
			xnoise += inc;
		}
		xnoise = 0;
		ynoise += inc;
	}
	znoise += inc;
}