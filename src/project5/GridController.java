package project5;


public abstract class GridController {
	protected Grid grid;	
	
	public abstract void put(Complex pos, Object obj);
	public abstract void remove(Complex pos, Object obj);
	public abstract boolean contains(Complex pos, Object obj);
	public abstract boolean isEmpty(Complex pos);
	public abstract boolean isFilled(Complex pos);
}
