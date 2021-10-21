package project5;


public class SpeedUnitAttackResult extends AttackResult{
    
    public SpeedUnitAttackResult(AttackResult attackResult) {
        super(attackResult);
    }
    @Override
    public void ApplyAttackResultOnReceiver(Unit unit) {
        this.attackResult.ApplyAttackResultOnReceiver(unit);
        unit.setMovementUnitSpeedProperty(unit.getMovementUnitSpeedProperty()/2);
    }
}