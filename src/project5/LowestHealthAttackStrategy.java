package project5;


public class LowestHealthAttackStrategy extends AttackStrategy {
	private static LowestHealthAttackStrategy attackStrategy;
	private LowestHealthAttackStrategy() {}
	
	public static synchronized LowestHealthAttackStrategy getInstance() {
		if (attackStrategy == null) attackStrategy = new LowestHealthAttackStrategy();
		return attackStrategy;
	}
	

    @Override
    public Unit cmp(Unit unit, Unit unit1) {
    	// return the unit with the lowest health
    	return unit != null &&
    			unit.getHealthProperty() < unit1.getHealthProperty() ?
    			unit : unit1;
    }
}
