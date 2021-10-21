package project5;


public class UnitPosition {
	Complex center;
	int radius;
	
	public UnitPosition(Complex c, int r) {
		center = c;
		radius = r;
	}
	
	public Complex getCenter() { return center; }
	public int getRadius() { return radius; }
	
	public UnitPosition setCenter(Complex c) { center = c; return this; }
	public UnitPosition setRadius(int r) { radius = r; return this; }
	
	public UnitPosition addX(int x) {
		center.setReal(center.getReal() + x);
		return this;
	}
	
	public UnitPosition addY(int y) {
		center.setImag(center.getImag() + y);
		return this;
	}
	
	public int getRightX() { return center.real + radius; }
	public int getLeftX() { return center.real - radius; }
	
	public int getTopY() { return center.imag + radius; }
	public int getBottomY() { return center.imag - radius; }

}
