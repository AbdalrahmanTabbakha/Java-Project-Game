package project5;


public enum UnitType {
	Test, Tank, TeslaTank, MirageTank, PrismTank, TankDestroyer,
	Soldier, Sniper, Infantry, NavySeal, GrizzlyTank,
	Structure, Pillbox, PrismTower, GrandCannon, MainBase, System, 
	Airplane, BlackEagle,
	NaturalObject, River;

	public static UnitType getTypeInFile(String type) throws IllegalUnitType
    {        switch (type) {
             case "Test": return Test;
             case "Tank": return Tank;
             case "TeslaTank": return TeslaTank;
             case "MirageTank": return MirageTank;
             case "PrismTank": return PrismTank;
             case "TankDestroyer": return TankDestroyer;
             case "Soldier": return Soldier;
             case "Sniper": return Sniper;
             case "Infantry": return Infantry;
             case "NavySeal": return NavySeal;
             case "GrizzlyTank": return GrizzlyTank;
             case "Structure": return Structure;
             case "Pillbox": return Pillbox;
             case "PrismTower": return PrismTower;
             case "GrandCannon": return GrandCannon;
             case "MainBase": return MainBase;
             case "System": return System; 
             case "Airplane": return Airplane;
             case "BlackEagle": return BlackEagle;  
             case "River": return River;
             default:
            	 throw new IllegalUnitType("Can't this type.");
    	}
     
    }
	
	public UnitType getAbstraction() throws IllegalUnitType {
		switch (this) {
		case TeslaTank:
		case MirageTank:
		case PrismTank:
		case TankDestroyer:
		case Test:
			return Tank;
		case Sniper:
		case Infantry:
		case NavySeal:
		case GrizzlyTank:
			return Soldier;
		case Pillbox:
		case PrismTower:
		case GrandCannon:
		case MainBase:
		case System:
			return Structure;
		case BlackEagle:
			return Airplane;
		case River:
			return NaturalObject;
		default:
			throw new IllegalUnitType("Can't abstract.");
		}
	}
	
	public int getCost() throws IllegalUnitType { 
		switch (this){
		case TeslaTank: 	return 50;
		case Sniper:		return 5;
		case MirageTank: 	return 70;
		case Infantry: 		return 3;
		case GrizzlyTank: 	return 50;
		case NavySeal: 		return 10;
		case TankDestroyer: return 50;
		case PrismTank: 	return 60;
		case Pillbox: 		return 150;
		case PrismTower: 	return 150;
		case GrandCannon: 	return 200;
		case BlackEagle: 	return 75;
		case System: 		return 175;
		default: 			throw new IllegalUnitType("Can't get cost.");
		}
	}       
	       
}
