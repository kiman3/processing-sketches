import java.util.*; // Manually import java utilities for some reason …

Random generator;

void setup(){
	background(0);
	size(640, 360);
	generator = new Random();
}

void draw(){
	float num = (float) generator.nextGaussian(); // Normal distribution, mean of 0 standard deviation of 1
	float sd = 60; // Standard devation
	float mean = 320; // Mean -> average

	float x = sd * num + mean; // Multiply by the standard deviation and add the mean;

	noStroke();
	fill(255, 10);
	ellipse(x, 180, 16, 16);
}