package project5;

import java.util.Set;

public abstract class AttackStrategy {
	
	public abstract Unit cmp(Unit unit, Unit unit1);

	public Unit prioritrizeUnitToAttack(Unit unit, Set<Unit> units) {
		Unit temp = null;
		
		for (Unit u: units) 
			if (unit.canAttack(u)) temp = cmp(temp, u);
		
		return temp;
	}

}
