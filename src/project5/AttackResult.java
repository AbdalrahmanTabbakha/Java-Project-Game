package project5;

public class AttackResult {
	protected AttackResult attackResult;

	protected AttackResult(AttackResult attackResult) {
		this.attackResult = attackResult;
	}

	public void ApplyAttackResultOnAttacker(Unit unit) {
		this.attackResult.ApplyAttackResultOnAttacker(unit);
	}

	public void ApplyAttackResultOnReceiver(Unit unit) {
		this.attackResult.ApplyAttackResultOnReceiver(unit);
	}
}
