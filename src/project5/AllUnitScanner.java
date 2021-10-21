package project5;

import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;

public class AllUnitScanner extends UnitScanner {
	private static AllUnitScanner unitScanner;
	
	private AllUnitScanner() {}
	
	public static synchronized AllUnitScanner getInstance() {
		if (unitScanner == null) unitScanner = new AllUnitScanner();
		return unitScanner;
	}
	

	@Override
	public Set<Unit> getAllUnitsInRange(Unit unit) {
		Grid grid = Grid.getInstance();
		Set<Unit> units = new HashSet<Unit>();
		
		Complex unitCenter = unit.getCenter();
		int range = unit.getRangeRadius();

		for (int i = unitCenter.getReal() - range;
				i < unitCenter.getReal() + range + 1;
				++i) {
			Complex c = new Complex(i, unit.getCenter().getImag());
			for (NavigableMap.Entry<Integer, Object> entry = grid.getLowerInCol(c);
					entry != null &&  Complex.dist(c.setImag(entry.getKey()), unitCenter) < range + 1;
					entry = grid.getLowerInCol(c))
				units.addAll(((Cell)entry.getValue()).getAllUnitsInCell());
			
			c.setImag(unit.getCenter().getImag());
			for (NavigableMap.Entry<Integer, Object> entry = grid.getHigherInCol(c);
					entry != null &&  Complex.dist(c.setImag(entry.getKey()), unitCenter) < range + 1;
					entry = grid.getHigherInCol(c))
				units.addAll(((Cell)entry.getValue()).getAllUnitsInCell()); 
		
		}
		
		units.remove(unit);
		return units;
	}

	
//	public static void main(String[] args) {
//
//		UnitFactory unitFactory = UnitFactory.getInstance();
//		AllUnitScanner sc = new AllUnitScanner();
//		
//		try {
//			Unit unit = unitFactory.createUnit(UnitType.Test, new Complex(0, 0));
//			Cell cell = new Cell(unit);
//			unit.getUnitGrid().put(unit);
//			
//			Unit unit1 = unitFactory.createUnit(UnitType.Test, new Complex(0, 10));
//			Cell cell1 = new Cell(unit1);
//			unit1.getUnitGrid().put(unit1);
//			
//			Unit unit2 = unitFactory.createUnit(UnitType.Test, new Complex(-10, 5));
//			Cell cell2 = new Cell(unit2);
//			unit2.getUnitGrid().put(unit2);
//			
//		
//			for (Unit temp : sc.getAllUnitsInRange(unit))
//				System.out.println(((Unit)temp).getCenter());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
}
