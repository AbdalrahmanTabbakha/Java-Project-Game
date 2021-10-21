package project5;

import java.util.NavigableMap;

public class AttackerMovement extends Movement {
	private static AttackerMovement attackerMovement;
	
	private AttackerMovement() {}
	
	public static synchronized AttackerMovement getInstance() {
		if (attackerMovement == null) attackerMovement = new AttackerMovement();
		return attackerMovement; 
	}

	@Override
	public void move(Unit unit) {
		UnitGrid grid = SeizerUnitGrid.getInstance();
		int squarePerSec = (int)unit.getMovementUnitSpeedProperty();

		Unit target = Grid.getInstance().getBase();
		Complex movement = Complex.sub(target.getCenter(), unit.getCenter());
		movement.shrinkAll(squarePerSec);
		try {
			grid.moveUnit(unit, movement);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
