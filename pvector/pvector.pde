PVector v = new PVector(1,5);
PVector u = PVector.mult(v, 2);
PVector w = PVector.sub(v, u);
w.div(3);

println(w);