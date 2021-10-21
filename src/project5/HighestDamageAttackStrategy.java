package project5;


public class HighestDamageAttackStrategy extends AttackStrategy {
	private static HighestDamageAttackStrategy attackStrategy;
	private HighestDamageAttackStrategy() {}

	public static synchronized HighestDamageAttackStrategy getInstance() {
		if (attackStrategy == null) attackStrategy = new HighestDamageAttackStrategy();
		return attackStrategy;
	}
	
	@Override
	public Unit cmp(Unit unit, Unit unit1) {
		// return the unit with the highest damage
		return unit != null && 
				unit.getAttackDamageUnitProperty() > unit1.getAttackDamageUnitProperty() ?
				unit : unit1;	
	}
	
}
