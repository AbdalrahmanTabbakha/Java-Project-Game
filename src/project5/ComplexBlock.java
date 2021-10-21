package project5;


public class ComplexBlock {
	Complex first, second;
	
	public ComplexBlock(Complex c, Complex c1) {
		this.first = c;
		this.second = c1;
	}
	
	public Complex getFirst() { return first; }
	public Complex getSecond() { return second; }
	
	public ComplexBlock setFirst(Complex first) { this.first = first; return this; }
	public ComplexBlock setsecond(Complex second) { this.second = second; return this; }
	
//	public ComplexBlock orderTopLeftToBottomRight() {
//		Complex newFirst = Complex.getTopLeft(first, second);
//		Complex newSecond = Complex.getBottomRight(first, second);
//		
//		first = newFirst;
//		second = newSecond;
//		
//		return this;
//	}
//	
	public ComplexBlock orderBottomLeftToTopRight() {
		Complex newFirst = Complex.getBottomLeft(first, second);
		Complex newSecond = Complex.getTopRight(first, second);
		
		first = newFirst;
		second = newSecond;
		
		return this;
	}
}
