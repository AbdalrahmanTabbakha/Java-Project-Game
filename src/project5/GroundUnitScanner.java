package project5;

import java.util.NavigableMap;
import java.util.HashSet;
import java.util.Set;

public class GroundUnitScanner extends UnitScanner {
	private static GroundUnitScanner unitScanner;
	private GroundUnitScanner() {}
	
	public static synchronized GroundUnitScanner getInstance() {
		if (unitScanner == null) unitScanner = new GroundUnitScanner();
		return unitScanner;
	}

	@Override
	public Set<Unit> getAllUnitsInRange(Unit unit) {
		Set<Unit> units = new HashSet<Unit>();
		SeizerGridController gc = SeizerGridController.getInstance();
		
		Complex unitCenter = unit.getCenter();
		int range = unit.getRangeRadius();

		for (int i = unit.getCenter().getReal() - unit.getRangeRadius();
				i < unit.getCenter().getReal() + unit.getRangeRadius() + 1;
				++i) {
			Complex c = new Complex(i, unitCenter.getImag());
			for (NavigableMap.Entry<Integer, Object> entry = gc.getLowerInCol(c);
					entry != null &&  Complex.dist(c.setImag(entry.getKey()), unitCenter) < range + 1;
					entry = gc.getLowerInCol(c))
				units.add(((Cell)entry.getValue()).getSeizer()); 
			
			c.setImag(unitCenter.getImag());
			for (NavigableMap.Entry<Integer, Object> entry = gc.getHigherInCol(c);
					entry != null &&  Complex.dist(c.setImag(entry.getKey()), unitCenter) < range + 1;
					entry = gc.getHigherInCol(c))
				units.add(((Cell)entry.getValue()).getSeizer());
		
		}
		
		units.remove(unit);
		return units;
	}
	
//	public static void main(String[] args) {
//		UnitFactory unitFactory = UnitFactory.getInstance();
//		GroundUnitScanner sc = new GroundUnitScanner();
//		
//		try {
//			Unit unit = unitFactory.createUnit(UnitType.Test, new Complex(0, 0));
//			Cell cell = new Cell(unit);
//			unit.getUnitGrid().put(unit);
//			
//			Unit unit1 = unitFactory.createUnit(UnitType.Test, new Complex(-3, -2));
//			Cell cell1 = new Cell(unit1);
//			unit1.getUnitGrid().put(unit1);
//			
//			Unit unit2 = unitFactory.createUnit(UnitType.Test, new Complex(0, 20));
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
