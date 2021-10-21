package project5;


public class OthersGridController extends GridController {
	private static OthersGridController gridOthers;

	private OthersGridController() {
		grid = Grid.getInstance();
	}
	
	public static synchronized OthersGridController getInstance() {
		if (gridOthers == null) gridOthers = new OthersGridController();
		return gridOthers;
	}

	@Override
	public void put(Complex pos, Object obj) {
		Unit unit = (Unit)obj;
	
		Cell cell;
		if ((cell = (Cell)grid.get(pos)) != null) {
			cell.addOthers(unit);
		} else {
			grid.put(pos, new Cell(null, unit));
		}
		
	}
	
	@Override
	public void remove(Complex pos, Object obj) {
		Unit unit = (Unit)obj;
	
		Cell cell = (Cell)grid.get(pos);
		if (cell.getSeizer() == null && cell.getOthersSize() == 1) {
			grid.remove(pos);
		} else {
			cell.getOthers().remove(unit);
		}
	}
	
	@Override
	public boolean contains(Complex pos, Object obj) {
		Cell cell = (Cell) grid.get(pos);
		
		return cell.containsOthers((Unit) obj);
	}
	
	@Override
	public boolean isEmpty(Complex pos) {
		Cell cell = (Cell)grid.get(pos);
		
		return cell == null || cell.getOthersSize() == 0;
	}
	
	@Override
	public boolean isFilled(Complex pos) {
		return false;
	}

}
