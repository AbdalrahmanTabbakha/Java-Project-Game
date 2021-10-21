package project5;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


public class Cell {
	private Unit seizer;
	private LinkedList<Unit> others;
	
	public Cell(Unit s) {
		seizer = s;
		others = null;
	}
	
	public Cell(Unit s, LinkedList<Unit> o) {
		seizer = s;
		others = o;
	}
	
	public Cell(Unit s, Unit o) {
		seizer = s;
		addOthers(o);
	}
	
	
	public boolean isEmpty() { return seizer == null && others == null; }
	
	public void setSeizer(Unit s) { seizer = s; }
	public void addOthers(Unit o) { 
		if (others == null) others = new LinkedList<Unit>();
		others.add(o);
	}
	public boolean containsOthers(Unit o) {
		return others.contains(o);
	}
	
	public Set<Unit> getAllUnitsInCell() {
		Set<Unit> units = new HashSet<Unit>();
		if (seizer != null) units.add(seizer);
		if (others != null) units.addAll(others);
		
		return units;
	}
	
	public Unit getSeizer() { return seizer; }
	public int getOthersSize() { return others != null ? others.size() : 0; }
	public LinkedList<Unit> getOthers() { return others; }
	//getRiver()
}
