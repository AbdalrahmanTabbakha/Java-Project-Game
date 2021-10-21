package project5;

import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.ArrayList;


public class Grid {
	private static Grid grid;
	final int SQUARES_COUNT;
	private Unit base;
	private NavigableMap<Integer, Object>[] allUnits;
	
	private Grid() {
		SQUARES_COUNT = 1000;
		allUnits = (TreeMap<Integer, Object>[]) new TreeMap[SQUARES_COUNT];
		
		for (int i = 0; i < 1000; ++i) allUnits[i] = new TreeMap<Integer, Object>();
	}
	
	public synchronized static Grid getInstance() {
		if (grid == null) {
			grid = new Grid();
		}
		return grid;
	}

	public Unit getBase() { return base; }
	public void setBase(Unit b) { base = b; }
	public int to_index(int xIdx) { return xIdx + SQUARES_COUNT / 2; }
	
	public void put(Complex c, Object obj) {
		allUnits[to_index(c.getReal())].put(c.getImag(), obj);
	}
	
	public Object get(Complex c) {
		return allUnits[to_index(c.getReal())].get(c.getImag());
	}
	
	public void remove(Complex c) {
		allUnits[to_index(c.getReal())].remove(c.getImag());
	}
	
	public void printInfo() {
		for (int i = 0; i < SQUARES_COUNT; ++i)
			for (NavigableMap.Entry<Integer, Object> entry : allUnits[i].entrySet())
				System.out.println(i + " " + entry.getKey());

	}
	
	public NavigableMap.Entry<Integer, Object> getLowerInCol(Complex c) {
		return allUnits[to_index(c.getReal())].lowerEntry(c.getImag());
	}
	
	public NavigableMap.Entry<Integer, Object> getHigherInCol(Complex c) {
		return allUnits[to_index(c.getReal())].higherEntry(c.getImag());
	}

	
	public static void main(String[] args) {
		Grid grid = new Grid();
		grid.put(new Complex(1, 4), (Integer) 4);
		grid.put(new Complex(1, 5), (Integer) 5);
		grid.put(new Complex(1, 1), (Integer) 1);
		grid.put(new Complex(1, 2), (Integer) 2);
		
		System.out.println(grid.getLowerInCol(new Complex(1, 3)));
		System.out.println(grid.getHigherInCol(new Complex(1, 3)));
		System.out.println(grid.getLowerInCol(new Complex(1, 2)));
		System.out.println(grid.getHigherInCol(new Complex(1, 4)));
		
	}
	
	
}
