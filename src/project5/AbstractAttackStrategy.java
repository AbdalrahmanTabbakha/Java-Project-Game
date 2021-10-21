//import java.util.NavigableMap;
//
//public abstract class AbstractAttackStrategy implements AttackStrategy {
//	
//	public boolean findTarget(Unit unit) {
//		Unit targetedUnit = null;		
//		Unit lastUnit = null;
//		
//		Grid grid = Grid.getInstance();
//		
//		int rangeRadius = unit.getRangeRadius();
//		Complex unitCenter = unit.getPositions().getCenter();
//		
//		int start = unitCenter.getReal() - rangeRadius;
//		int end = unitCenter.getReal() + rangeRadius + 1;
//		
//		for (int i = start; i < end; ++i) {
//			int j = unitCenter.getImag();
//			Complex c = new Complex(i, j);
//			
//			if (i < 0 || grid.allUnits[i] == null) continue;
//			while (unit.isInRange(c.setImag(j))) {
//				NavigableMap.Entry<Integer, Unit> entry = grid.allUnits[i].lowerEntry(j);
//				if (entry == null) break;
//				lastUnit = entry.getValue();
//				
//				// FIXME: check for player & canAttack!
//				if (unit.isInRange(c.setImag(entry.getKey())))
//					targetedUnit = prioritrizeUnitToAttack(targetedUnit, lastUnit);
//				j = lastUnit.getPositions().getBottomY();
//			}
//			
//			j = unitCenter.getImag();
//			while (unit.isInRange(c.setImag(j))) {
//				NavigableMap.Entry<Integer, Unit> entry = grid.allUnits[i].higherEntry(j);
//				if (entry == null) break;
//				lastUnit = entry.getValue();
//
//				
//				// FIXME: check for player & canAttack!
//				//System.out.println(i + " " + entry.getKey() + " " + unit.isInRange(c.setImag(entry.getKey())));
//				if (unit.isInRange(c.setImag(entry.getKey())))
//					targetedUnit = prioritrizeUnitToAttack(targetedUnit, lastUnit);
//				j = lastUnit.getPositions().getTopY();
//			}	
//		}
//		unit.targetedUnit = targetedUnit;
//		
//		return targetedUnit != null;
//	}
//
//}
120
1
2
3
100
150
Tank
200
100
MirageTank
20
30
Sniper
50
50
PrismTank
300
200
Sniper
40
80
Pillbox
1
2
3
10
50
Tank
20
100
MirageTank
200
30
Sniper
5
50
PrismTank
30
200
Sniper
70
80
Pillbox


