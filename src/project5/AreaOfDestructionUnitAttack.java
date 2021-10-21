package project5;


import java.util.*;

public class AreaOfDestructionUnitAttack implements UnitAttack {

    @Override
    public void performAttack(Unit unit) {
     double damage=unit.getAttackDamageUnitProperty();
     Set<Unit>unitInRange= unit.getUnitScanner().getAllUnitsInRange(unit);
     Iterator<Unit> i = unitInRange.iterator();
     Unit unit1;
     while (i.hasNext())
     {
        unit1= (Unit)i.next(); // FIXME: check if unit1 is a destructible
        unit1.acceptDamage(damage);
        
     }
    }
    
}