package project5;


public class DefenderMovement extends Movement {
	private static DefenderMovement defenderMovement;	
	private final Grid grid;
	private final int LIMIT;
	
	private DefenderMovement() {
		grid = Grid.getInstance();
		LIMIT = grid.SQUARES_COUNT / 4;
	}
	
	public static synchronized DefenderMovement getInstance() {
		if (defenderMovement == null) defenderMovement = new DefenderMovement();
		return defenderMovement; 
	}

	@Override
	public void move(Unit unit) {
		UnitGrid grid = unit.getUnitGrid();
		int squarePerSec = (int)unit.getMovementUnitSpeedProperty();
		
		int unitX = unit.getCenter().getReal();
		int unitY = unit.getCenter().getImag();
		
		try {
			if (unitX < LIMIT && unitY >= LIMIT) {
				grid.moveUnit(unit, new Complex(squarePerSec, 0));
			} else if (unitX >= LIMIT && unitY > -LIMIT) {
				grid.moveUnit(unit, new Complex(0, -squarePerSec));
			} else if (unitX > -LIMIT && unitY <= -LIMIT) {
				grid.moveUnit(unit, new Complex(-squarePerSec, 0));
			} else {
				grid.moveUnit(unit, new Complex(0, squarePerSec));
			}
		} catch (Exception e) {
		}
	}
	
}
