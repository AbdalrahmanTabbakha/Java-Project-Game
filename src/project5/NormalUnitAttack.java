package project5;


public class NormalUnitAttack implements UnitAttack {

    @Override
    public void performAttack(Unit unit) {
        double damage=unit.getAttackDamageUnitProperty();
        unit.getTargetedUnit().acceptDamage(damage);
    }
}