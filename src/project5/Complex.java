package project5;

import java.lang.Math;

public class Complex {
	int real, imag;
	
	public Complex(int r, int i) {
		real = r;
		imag = i;
	}
	
	public Complex setReal(int r) { real = r; return this; }
	public Complex setImag(int i) { imag = i; return this; }
	
	public int getReal() { return real; }
	public int getImag() { return imag; }
	
	public Complex add(Complex other) {
		real += other.real;
		imag += other.imag;
		return this;
	}
	
	public Complex addReal(int r) {
		real += r;
		return this;
	}
	
	public Complex addImag(int i) {
		imag += i;
		return this;
	}
	
	
	public Complex sub(Complex other) {
		real -= other.real;
		imag -= other.imag;
		return this;
	}
	
	public Complex abs() {
		real = Math.abs(real);
		imag = Math.abs(imag);
		return this;
	}
	
	public Complex shrinkAll(int v) {
		if (real > 0 && real > v) real = v;
		else if (real < 0 && real < -v) real = -v;
		
		if (imag > 0 && imag > v) imag = v;
		else if (imag < 0 && imag < -v) imag = -v;
		
		return this;
	}
	
	public double dist(Complex other) {
		double x = Math.pow(real - other.real, 2);
		double y = Math.pow(imag - other.imag, 2);
		return Math.sqrt(x + y);
	}

	
	public static Complex add(Complex c, Complex c1) {
		return new Complex(c.real + c1.real, c.imag + c1.imag);
	}
	
	public static Complex sub(Complex c, Complex c1) {
		return new Complex(c.real - c1.real, c.imag - c1.imag);
	}
	
	public static Complex addAll(Complex c, int shift) {
		return new Complex(c.real + shift, c.imag + shift);
	}
	
	public static Complex subAll(Complex c, int shift) {
		return new Complex(c.real - shift, c.imag - shift);
	}
	
//	public static Complex getTopLeft(Complex c, Complex c1) {
//		return new Complex(Math.min(c.real,  c1.real), Math.max(c.imag, c1.imag));
//	}
//	
//	public static Complex getBottomRight(Complex c, Complex c1) {
//		return new Complex(Math.max(c.real,  c1.real), Math.min(c.imag, c1.imag));
//	}
//	
	public static Complex getTopRight(Complex c, Complex c1) {
		return new Complex(Math.max(c.real, c1.real), Math.max(c.imag, c1.imag));
	}

	public static Complex getBottomLeft(Complex c, Complex c1) {
		return new Complex(Math.min(c.real, c1.real), Math.min(c.imag, c1.imag));
	}
	
	public static double dist(Complex c, Complex c1) {
		double x = Math.pow(c.real - c1.real, 2);
		double y = Math.pow(c.imag - c1.imag, 2);
		return Math.sqrt(x + y);
	}

	public String toString() {
		return "(" + real + ", " + imag + ")";
	}
	
	public static void main(String[] args) {
		Complex c = new Complex(-1, 2);
		c.add(new Complex(2, 0));
		assert c.real == 1 && c.imag == 2 : "Error in .add()";
		
		c.sub(new Complex(0, -1));
		assert c.real == 1 && c.imag == 3 : "Error in .sub()";
		
		Complex c1 = Complex.sub(Complex.add(c, new Complex(-2, 0)), new Complex(0, 1));
		assert c1.real == -1 && c1.imag == 2 : "Error in static .add(), .sub()";
		
		c1 = Complex.addAll(c1, 2);
		assert c1.real == 1 && c1.imag == 4 : "Error in .addAll()";
		c1 = Complex.subAll(c1, 2);
		assert  c1.real == -1 && c1.imag == 2 : "Error in .subAll()";
		
		c = new Complex(1, 5);
		c1 = new Complex(5, 2);
		
		Complex bottomLeft = getBottomLeft(c, c1);
		assert bottomLeft.real == 1 && bottomLeft.imag == 2 : "Error in getTopLeft()";
		Complex topRight = getTopRight(c, c1);
		assert topRight.real == 5 && topRight.imag == 5 : "Error in getBottomRight()";
		
		System.out.println("Complex Test.");
		
		
	}
}
