float montecarlo(){
	while(true){
		float r1 = random(1);
		float probability = r1;
		float r2 = random(1);

		if (r2 < probability){
			return r1;
		}
	}
}