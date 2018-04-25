import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class landscape extends PApplet {

// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Landscape with height values according to Perlin noise

Landscape land;    
float theta = 0.0f;

public void setup() {
  

  // Create a landscape object
  land = new Landscape(20,800,400);
}

public void draw() {

  // Ok, visualize the landscape space
  background(255);
  pushMatrix();
  translate(width/2,height/2+20,-160);
  rotateX(PI/3);
  rotateZ(theta);
  land.render(); 
  popMatrix();

  land.calculate();
  
  theta += 0.0025f;
}

// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// "Landscape" example

class Landscape {

  int scl;           // size of each cell
  int w, h;           // width and height of thingie
  int rows, cols;    // number of rows and columns
  float zoff = 0.0f;  // perlin noise argument
  float[][] z;       // using an array to store all the height values 

  Landscape(int scl_, int w_, int h_) {
    scl = scl_;
    w = w_;
    h = h_;
    cols = w/scl;
    rows = h/scl;
    z = new float[cols][rows];
  }


  // Calculate height values (based off a neural network)
  public void calculate() {
    float xoff = 0;
    for (int i = 0; i < cols; i++)
    { 
      float yoff = 0;
      for (int j = 0; j < rows; j++)
      {
        z[i][j] = map(noise(xoff, yoff,zoff), 0, 1, -120, 120);
        yoff += 0.1f;
      }
      xoff += 0.1f;
    }
    zoff+=0.01f;
  }

  // Render landscape as grid of quads
  public void render() {
    // Every cell is an individual quad
    for (int x = 0; x < z.length-1; x++)
    {
      beginShape(QUAD_STRIP);
      for (int y = 0; y < z[x].length; y++)
      {
        // one quad at a time
        // each quad's color is determined by the height value at each vertex
        // (clean this part up)
        stroke(0xffff69b4);
        stroke(0xffbbbbbb);
        float currentElevation = z[x][y];
        float currentShade = map(currentElevation, -120, 120, 0, 255);
        // fill(currentShade, 255);
        noFill();
        float xCoordinate = x*scl-w/2;
        float yCoordinate = y*scl-h/2;
        vertex(xCoordinate, yCoordinate, z[x][y]);
        vertex(xCoordinate + scl, yCoordinate, z[x+1][y]);
      }
      endShape();
    }
  }
}
  public void settings() {  size(800,200,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#050000", "--hide-stop", "landscape" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
