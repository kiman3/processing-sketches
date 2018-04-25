// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// Landscape with height values according to Perlin noise

Landscape land;    
float theta = 0.0;

void setup() {
  size(800,200,P3D);

  // Create a landscape object
  land = new Landscape(20,800,400);
}

void draw() {

  // Ok, visualize the landscape space
  background(255);
  pushMatrix();
  translate(width/2,height/2+20,-160);
  rotateX(PI/3);
  rotateZ(theta);
  land.render(); 
  popMatrix();

  land.calculate();
  
  theta += 0.0025;
}

// The Nature of Code
// Daniel Shiffman
// http://natureofcode.com

// "Landscape" example

class Landscape {

  int scl;           // size of each cell
  int w, h;           // width and height of thingie
  int rows, cols;    // number of rows and columns
  float zoff = 0.0;  // perlin noise argument
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
  void calculate() {
    float xoff = 0;
    for (int i = 0; i < cols; i++)
    { 
      float yoff = 0;
      for (int j = 0; j < rows; j++)
      {
        z[i][j] = map(noise(xoff, yoff,zoff), 0, 1, -120, 120);
        yoff += 0.1;
      }
      xoff += 0.1;
    }
    zoff+=0.01;
  }

  // Render landscape as grid of quads
  void render() {
    // Every cell is an individual quad
    for (int x = 0; x < z.length-1; x++)
    {
      beginShape(QUAD_STRIP);
      for (int y = 0; y < z[x].length; y++)
      {
        // one quad at a time
        // each quad's color is determined by the height value at each vertex
        // (clean this part up)
        stroke(#ff69b4);
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