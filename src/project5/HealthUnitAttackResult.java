package project5;


public class HealthUnitAttackResult extends AttackResult{

    public HealthUnitAttackResult(AttackResult attackResult) {
        super(attackResult);
    }
   @Override
    public void ApplyAttackResultOnAttacker(Unit unit) {
        unit.setHealthProperty(unit.getHealthProperty()+10);
    } 
    @Override
  public void ApplyAttackResultOnReceiver(Unit unit) {
        this.attackResult.ApplyAttackResultOnReceiver(unit);
        unit.getTargetedUnit().acceptDamage(unit.getAttackDamageUnitProperty());
    }  
}