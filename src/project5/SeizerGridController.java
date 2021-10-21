package project5;

import java.util.NavigableMap;

public class SeizerGridController extends GridController {
	private static SeizerGridController gridSeizer;
	
	private SeizerGridController() {
		grid = Grid.getInstance();
	}
	
	public static synchronized SeizerGridController getInstance() {
		if (gridSeizer == null) gridSeizer = new SeizerGridController();
		return gridSeizer;
	}

	@Override
	public void put(Complex pos, Object obj) {
		Unit unit = (Unit) obj;
		Cell cell;
		if ((cell = (Cell)grid.get(pos)) != null) {
			cell.setSeizer(unit);
		} else {
			grid.put(pos, new Cell(unit));
		}
		
	}
	
	@Override	
	public void remove(Complex pos, Object obj) {
		Unit unit = (Unit)obj;
		
		Cell cell = (Cell)grid.get(pos);
		if (cell.getSeizer().equals(unit) && cell.getOthersSize() == 0) {
			grid.remove(pos);
		} else {
			cell.setSeizer(null);
		}
	}

	public NavigableMap.Entry<Integer, Object> getLowerInCol(Complex pos) {
//		if (!isEmpty(pos)) {
//			int lastY = ((Cell) grid.get(pos)).getSeizer().getPositions().getBottomY();
//			pos = new Complex(pos.getReal(), lastY);
//		}
		
		NavigableMap.Entry<Integer, Object> entry;
		for (entry = grid.getLowerInCol(pos);
				entry != null && ((Cell) entry.getValue()).getSeizer() == null;
				entry = grid.getLowerInCol(pos.setImag(entry.getKey())))
				/* Nothing. */;
		
		return entry;
	}

	public NavigableMap.Entry<Integer, Object> getHigherInCol(Complex pos) {
//		if (!isEmpty(pos)) {
//			int firstY = ((Cell) grid.get(pos)).getSeizer().getPositions().getTopY();
//			pos = new Complex(pos.getReal(), firstY);
//		}
		
		NavigableMap.Entry<Integer, Object> entry;
		for (entry = grid.getHigherInCol(pos);
				entry != null && ((Cell) entry.getValue()).getSeizer() == null;
				entry = grid.getHigherInCol(pos.setImag(entry.getKey())))
				/* Nothing. */;
		
		return entry;
	}

	@Override
	public boolean contains(Complex pos, Object obj) {
		Unit unit = (Unit) obj;
		Cell cell = (Cell) grid.get(pos);
		if (cell != null && cell.getSeizer().equals(obj)) return true;
		
		return false;
	}
	
	
	@Override
	public boolean isEmpty(Complex pos) {
		Cell cell = (Cell)grid.get(pos);
		
		return cell == null || cell.getSeizer() == null;
	}
	
	@Override 
	public boolean isFilled(Complex pos) {
		return !isEmpty(pos);
	}
	
	public static void main(String[] args) {
		UnitFactory unitFactory = UnitFactory.getInstance();
		try {
			Unit unit = unitFactory.createUnit(UnitType.Test, new Complex(0, 0), 1);
			
			System.out.println(gridSeizer.contains(new Complex(-1, -1), unit));
			System.out.println(gridSeizer.contains(new Complex(-1, 0), unit));
			System.out.println(gridSeizer.contains(new Complex(0, -1), unit));
			System.out.println(gridSeizer.contains(new Complex(-1, 1), unit));
			System.out.println(gridSeizer.contains(new Complex(1, -1), unit));
			System.out.println(gridSeizer.contains(new Complex(0, 0), unit));
			System.out.println(gridSeizer.contains(new Complex(1, 0), unit));
			System.out.println(gridSeizer.contains(new Complex(0, 1), unit));
			System.out.println(gridSeizer.contains(new Complex(1, 1), unit));
			System.out.println();
		} catch (IllegalUnitType e) {
			
		} 
	}

}
