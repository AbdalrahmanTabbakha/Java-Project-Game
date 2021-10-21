package project5;


public class MoveToPointInitialPlan extends InitialPlan{
 double taem;
 Complex center;
 @Override
 public void doInitialPlan(Unit unit)
 {
    
		UnitGrid grid = unit.getUnitGrid();
		int squarePerSec = (int)unit.getMovementUnitSpeedProperty();
		center.shrinkAll(squarePerSec);
		try {
			grid.moveUnit(unit, center);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
 }
 
