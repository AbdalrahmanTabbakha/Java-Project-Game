package project5;


public class SlowingUnitAttack implements UnitAttack {

	@Override
	public void performAttack(Unit unit) {
		unit.resetMovementUnitSpeedProperty(new MovementUnitSpeedProperty(100));
	}

}
