package project5;

import java.util.Set;

public class UnitFactory {

    static UnitFactory unitFactory;

    private UnitFactory() {
    }

    public static UnitFactory getInstance() {
        if (unitFactory == null) {
            unitFactory = new UnitFactory();
        }
        return unitFactory;
    }

    public Unit createUnit(UnitType unitType, Complex c, int teamType)
            throws IllegalUnitType {
        int radius;
        int rangeRadius;

        Movement movement = null;
        UnitType[] canAttack;
        UnitPosition positions = null;
        UnitProperty[] unitProperties = new UnitProperty[5];
        UnitAttack unitAttack = new NormalUnitAttack();
        UnitScanner unitScanner;
        UnitGrid unitGrid = SeizerUnitGrid.getInstance();
        AttackStrategy attackStrategy;
        if (teamType == 2 && (unitType.getAbstraction() == unitType.Soldier)) {
            movement = DefenderMovement.getInstance();
        } else if (teamType == 2 && (unitType.getAbstraction() != unitType.Soldier)) {
            movement = null;
        } else if (teamType == 1) {
            movement = AttackerMovement.getInstance();
        }
        positions.setCenter(c);
        Unit targetedUnit;
        Set<Unit> unitObservers;

        switch (unitType) {
            case Test:
                positions.radius = 1;
                rangeRadius = 10;
                canAttack = new UnitType[]{UnitType.Structure, UnitType.Soldier};
                unitProperties[0] = new HealthUnitProperty(1000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.6);
                unitProperties[2] = new ArmorUnitProperty(0.5);
                unitProperties[3] = new AttackDamageUnitProperty(3000);
                unitProperties[4] = new MovementUnitSpeedProperty(30);
                movement = AttackerMovement.getInstance();
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                unitAttack = new AreaOfDestructionUnitAttack();
//      uinitAttack = new unitAttack();
                break;
            case TeslaTank:
                positions.radius = 20;
                rangeRadius = 250;
                canAttack = new UnitType[]{UnitType.Tank, UnitType.Soldier};
                unitProperties[0] = new HealthUnitProperty(1000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.6);
                unitProperties[2] = new ArmorUnitProperty(0.5);
                unitProperties[3] = new AttackDamageUnitProperty(300);
                unitProperties[4] = new MovementUnitSpeedProperty(30);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
//      uinitAttack = new unitAttack();
                break;
            case Sniper:
                positions.radius = 3;
                rangeRadius = 700;
                canAttack = new UnitType[]{UnitType.Soldier};
                unitProperties[0] = new HealthUnitProperty(250);
                unitProperties[1] = new AttackSpeedUnitProperty(0.6);
                unitProperties[2] = new ArmorUnitProperty(0.4);
                unitProperties[3] = new AttackDamageUnitProperty(75);
                unitProperties[4] = new MovementUnitSpeedProperty(10);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = LowestHealthAttackStrategy.getInstance();
                break;
            case MirageTank:
                positions.radius = 15;
                rangeRadius = 10;
                canAttack = new UnitType[]{UnitType.Soldier, UnitType.Tank, UnitType.Structure};
                unitProperties[0] = new HealthUnitProperty(750);
                unitProperties[1] = new AttackSpeedUnitProperty(1);
                unitProperties[2] = new ArmorUnitProperty(0.1);
                unitProperties[3] = new AttackDamageUnitProperty(1000);
                unitProperties[4] = new MovementUnitSpeedProperty(60);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                break;
            case Infantry:
                positions.radius = 3;
                rangeRadius = 50;
                canAttack = new UnitType[]{UnitType.Soldier};
                unitProperties[0] = new HealthUnitProperty(250);
                unitProperties[1] = new AttackSpeedUnitProperty(1.5);
                unitProperties[2] = new ArmorUnitProperty(0.2);
                unitProperties[3] = new AttackDamageUnitProperty(50);
                unitProperties[4] = new MovementUnitSpeedProperty(10);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = LowestHealthAttackStrategy.getInstance();
                break;
            case GrizzlyTank:
                positions.radius = 20;
                rangeRadius = 250;
                canAttack = new UnitType[]{UnitType.Soldier, UnitType.Tank, UnitType.Structure};
                unitProperties[0] = new HealthUnitProperty(1000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.6);
                unitProperties[2] = new ArmorUnitProperty(0.4);
                unitProperties[3] = new AttackDamageUnitProperty(250);
                unitProperties[4] = new MovementUnitSpeedProperty(30);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = LowestHealthAttackStrategy.getInstance();
                break;
            case NavySeal:
                positions.radius = 3;
                rangeRadius = 50;
                canAttack = new UnitType[]{UnitType.Soldier, UnitType.Tank};
                unitProperties[0] = new HealthUnitProperty(400);
                unitProperties[1] = new AttackSpeedUnitProperty(2);
                unitProperties[2] = new ArmorUnitProperty(0.2);
                unitProperties[3] = new AttackDamageUnitProperty(60);
                unitProperties[4] = new MovementUnitSpeedProperty(20);
                unitGrid = OthersUnitGrid.getInstance();
                unitScanner = AllUnitScanner.getInstance();
                attackStrategy = LowestHealthAttackStrategy.getInstance();
                break;
            case TankDestroyer:
                positions.radius = 20;
                rangeRadius = 150;
                canAttack = new UnitType[]{UnitType.Tank};
                unitProperties[0] = new HealthUnitProperty(1000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.6);
                unitProperties[2] = new ArmorUnitProperty(0.5);
                unitProperties[3] = new AttackDamageUnitProperty(400);
                unitProperties[4] = new MovementUnitSpeedProperty(20);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                break;
            case PrismTank:
                positions.radius = 20;
                rangeRadius = 150;
                canAttack = new UnitType[]{UnitType.Soldier, UnitType.Tank, UnitType.Structure};
                unitProperties[0] = new HealthUnitProperty(1000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.6);
                unitProperties[2] = new ArmorUnitProperty(0.35);
                unitProperties[3] = new AttackDamageUnitProperty(300);
                unitProperties[4] = new MovementUnitSpeedProperty(20);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                break;
            case Pillbox:
                positions.radius = 40;
                rangeRadius = 200;
                canAttack = new UnitType[]{UnitType.Soldier};
                unitProperties[0] = new HealthUnitProperty(4000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.7);
                unitProperties[2] = new ArmorUnitProperty(0.7);
                unitProperties[3] = new AttackDamageUnitProperty(100);
                unitProperties[4] = new MovementUnitSpeedProperty(0);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                movement = null;
                break;
            case PrismTower:
                positions.radius = 30;
                rangeRadius = 200;
                canAttack = new UnitType[]{UnitType.Soldier, UnitType.Tank};
                unitProperties[0] = new HealthUnitProperty(4000);
                unitProperties[1] = new AttackSpeedUnitProperty(0.5);
                unitProperties[2] = new ArmorUnitProperty(0.7);
                unitProperties[3] = new AttackDamageUnitProperty(100);
                unitProperties[4] = new MovementUnitSpeedProperty(0);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                movement = null;
                break;
            case GrandCannon:
                positions.radius = 40;
                rangeRadius = 400;
                canAttack = new UnitType[]{UnitType.Soldier, UnitType.Tank};
                unitProperties[0] = new HealthUnitProperty(6500);
                unitProperties[1] = new AttackSpeedUnitProperty(0.9);
                unitProperties[2] = new ArmorUnitProperty(0.3);
                unitProperties[3] = new AttackDamageUnitProperty(150);
                unitProperties[4] = new MovementUnitSpeedProperty(0);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = LowestHealthAttackStrategy.getInstance();
                movement = null;
                break;
            case MainBase:
                positions.radius = 50;
                rangeRadius = 0;
                canAttack = null;
                unitProperties[0] = new HealthUnitProperty(10000);
                unitProperties[1] = null;
                unitProperties[2] = new ArmorUnitProperty(0.5);
                unitProperties[3] = null;
                unitProperties[4] = null;
                movement = null;
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = GroundUnitScanner.getInstance();
                attackStrategy = null;
                break;
            case BlackEagle:
                positions.radius = 0;
                rangeRadius = 30;
                canAttack = new UnitType[]{UnitType.MainBase};
                unitProperties[0] = new HealthUnitProperty(1500);
                unitProperties[1] = new AttackSpeedUnitProperty(0.0);//فراغ
                unitProperties[2] = new ArmorUnitProperty(0.0);
                unitProperties[3] = new AttackDamageUnitProperty(400);
                unitProperties[4] = new MovementUnitSpeedProperty(100);
                unitGrid = OthersUnitGrid.getInstance();
                unitScanner = AllUnitScanner.getInstance();
                attackStrategy = LowestHealthAttackStrategy.getInstance();
                break;
            case System:
                positions.radius = 40;
                rangeRadius = 400;
                canAttack = new UnitType[]{UnitType.Airplane};
                unitProperties[0] = new HealthUnitProperty(2500);
                unitProperties[1] = new AttackSpeedUnitProperty(0.9);
                unitProperties[2] = new ArmorUnitProperty(0.2);
                unitProperties[3] = new AttackDamageUnitProperty(50);
                unitProperties[4] = new MovementUnitSpeedProperty(0);
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = AllUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                movement = null;
                break;
            case River:
                positions.radius = 40;
                rangeRadius = 40;
                canAttack = null;
                unitProperties[0] = null;
                unitProperties[1] = null;
                unitProperties[2] = null;
                unitProperties[3] = null;
                unitGrid = SeizerUnitGrid.getInstance();
                unitScanner = AllUnitScanner.getInstance();
                attackStrategy = HighestDamageAttackStrategy.getInstance();
                unitAttack = new SlowingUnitAttack();
                movement = null;

                break;
            default:
                return null;
        }
        Unit UnitTemp = new Unit(positions, unitType, rangeRadius, canAttack, unitProperties, movement, unitGrid,
                attackStrategy, unitScanner, unitAttack);

        unitGrid.put(UnitTemp);
        return UnitTemp;
    }
}
