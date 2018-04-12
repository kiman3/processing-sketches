float noiseScale = 0.02;

void setup(){
	size(640, 360);
}

void draw() {
  background(0);
  for (int x=0; x < width; x++) {
    float noiseVal = noise((mouseX+x)*noiseScale, mouseY*noiseScale);
    stroke(noiseVal*127);
    line(x, mouseY+noiseVal*80, x, height);
  }
}