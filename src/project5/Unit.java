package project5;


import java.util.logging.Logger;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import java.util.HashSet;
import java.lang.System;
import javafx.scene.shape.Circle;

public class Unit implements Runnable {
	private static final Log log = Log.getInstance();
	
	private int rangeRadius;
	private Movement movement;
	private UnitType unitType;
	private UnitType[] canAttack;
	private UnitPosition positions;
	private UnitProperty[] unitProperties;
	private UnitAttack unitAttack;
	private UnitScanner unitScanner;
	private UnitGrid unitGrid;
	private AttackStrategy attackStrategy;
       private Circle c;
	private Unit targetedUnit;
	private Set<Unit> unitObservers;

	private boolean pauseFlag;
	private boolean stopFlag;

	private Thread thread;

	private ReentrantLock observersLock;

	public Unit(UnitPosition unitPosition, UnitType unittype, int rangeRadius, UnitType[] canAttack,
			UnitProperty[] unitProperties, Movement movement, UnitGrid unitGrid, AttackStrategy attackStrategy,
			UnitScanner unitScanner, UnitAttack unitAttack) {
		this.movement = movement;
		this.canAttack = canAttack;
		this.positions = unitPosition;
		// squaresPerMove = 5;
		this.rangeRadius = rangeRadius;
		this.attackStrategy = attackStrategy;
		this.unitProperties = unitProperties;
		this.unitType = unittype;
		this.unitGrid = unitGrid;
		this.unitObservers = new HashSet<Unit>();
		this.unitScanner = unitScanner;
		this.unitAttack = unitAttack;
		this.thread = new Thread(this);
		this.observersLock = new ReentrantLock();
              this.circle.setCenterX((double) this.positions.getCenter().getReal());
              this.circle.setCenterY((double) this.positions.getCenter().getImag());
              this.circle.setRadius((double) this.positions.radius);  
	}

	public void setMovement(Movement m) {
		movement = m;
	}

	public void setTargetedUnit(Unit unit) {
		targetedUnit = unit;
	}

	public void setHealthProperty(double amount) {
		unitProperties[0].set_property_value(amount);
	}

	public void setAttackSpeedUnitProperty(double amount) {
		unitProperties[1].set_property_value(amount);
	}

	public void setArmorUnitProperty(double amount) {
		unitProperties[2].set_property_value(amount);
	}

	public void setAttackDamageUnitProperty(double amount) {
		unitProperties[3].set_property_value(amount);
	}

	public void setMovementUnitSpeedProperty(double amount) {
		unitProperties[4].set_property_value(amount);
	}

	public void resetMovementUnitSpeedProperty(UnitProperty unitProperty) {
		unitProperties[4] = unitProperty;
	}

	public Movement getMovement() {
		return movement;
	}

	public UnitPosition getPositions() {
		return positions;
	}

	public UnitProperty[] getProperties() { return unitProperties; }
	public int getRangeRadius() {
		return rangeRadius;
	}
         public Circle getCircleUnit() {
        return circle;
    }


	public AttackStrategy getAttackStrategy() {
		return attackStrategy;
	}

	public Unit getTargetedUnit() {
		return targetedUnit;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public UnitGrid getUnitGrid() {
		return unitGrid;
	}

	public UnitScanner getUnitScanner() {
		return unitScanner;
	}

	public double getHealthProperty() {
		return unitProperties[0].get_property_value();
	}

	public double getAttackSpeedUnitProperty() {
		return unitProperties[1].get_property_value();
	}

	public double getArmorUnitProperty() {
		return unitProperties[2].get_property_value();
	}

	public double getAttackDamageUnitProperty() {
		return unitProperties[3].get_property_value();
	}

	public double getMovementUnitSpeedProperty() {
		return unitProperties[4].get_property_value();
	}

	public void move() {
		System.out.println("m");
		movement.move(this);
	}

	public boolean canAttack(Unit targetedUnit) {
		for (UnitType unitType : canAttack)
			try {
				if (unitType == targetedUnit.getUnitType().getAbstraction())
					return true;
			} catch (Exception e) {
				System.out.println("ERROR: Units musn't be ABSTRACT TYPES!");
				e.printStackTrace();
				System.exit(-1);
			}
		return false;
	}

	public void acceptDamage(double damage) {
		System.out.println(this.getHealthProperty());
		double temp = 0;
		if (this.unitProperties[2].propertyvalue != 0)
			temp = this.unitProperties[2].propertyvalue * damage;
		damage -= temp;
		this.unitProperties[0].propertyvalue -= damage;

		synchronized (this) {
			if (this.getHealthProperty() <= 0 && this.isAlive()) {
				this.stop();
				this.onDestroy();
			}

		}
	}

	public boolean containsObserver(Unit unit) {
		return unitObservers.contains(unit);
	}

	public void attack() {
		System.out.println("a");
		unitAttack.performAttack(this);
	}

	public void start() {
		stopFlag = false;
		this.thread.start();
	}

	public void stop() {
		stopFlag = true;
	}

	public void onDestroy() {
		observersLock.lock();

		try {
			unitGrid.remove(this);
			Set<Unit> newUnitObservers = new HashSet<Unit>();

			for (Unit obj : this.unitObservers) {
				if (notify(obj))
					newUnitObservers.add(obj);
			}
			this.unitObservers = newUnitObservers;
		} finally {
			observersLock.unlock();
		}
	}

	public boolean isInXRange(int x) {
		return x >= positions.getCenter().getReal() - rangeRadius && x <= positions.getCenter().getReal() + rangeRadius;
	}

	public boolean isInYRange(int y) {
		return y >= positions.getCenter().getImag() - rangeRadius && y <= positions.getCenter().getImag() + rangeRadius;
	}

	public boolean isInRange(Complex c) {
		return isInXRange(c.getReal()) && isInYRange(c.getImag());
	}

	public boolean isInRange(Unit unit) {
		if (unit == null || unit.positions == null)
			return false;

		return isInRange(unit.findNearestPoint(this.getCenter()));
	}

	public Complex findNearestPoint(Complex point) {
		Complex c = new Complex(0, 0);

		if (point.getReal() <= positions.getRightX() && point.getReal() >= positions.getLeftX()) {
			c.setReal(point.getReal());
		} else if (point.getReal() > positions.getRightX()) {
			c.setReal(positions.getRightX());
		} else {
			c.setReal(positions.getLeftX());
		}

		if (point.getImag() <= positions.getTopY() && point.getImag() >= positions.getBottomY()) {
			c.setImag(point.getImag());
		} else if (point.getImag() > positions.getTopY()) {
			c.setImag(positions.getTopY());
		} else {
			c.setImag(positions.getBottomY());
		}

		return c;
	}

	public boolean isAlive() {
		return unitGrid.isCenterOnGrid(this);
	}

	public Complex getCenter() {
		return positions.getCenter();
	}

	public boolean findTarget() {
		targetedUnit = attackStrategy.prioritrizeUnitToAttack(this, unitScanner.getAllUnitsInRange(this));

		if (targetedUnit != null) {
			request(targetedUnit);
			return true;
		}

		return false;
	}

	public void request(Unit target) {
		target.unitObservers.add(this);
	}

	public boolean notify(Unit targeter) {
		if (this.isAlive() && targeter.isInRange(this))
			return true;

		targeter.setTargetedUnit(null);
		return false;
	}

	synchronized void mysuspend() {
		pauseFlag = true;
	}

	synchronized void myresume() {
		pauseFlag = false;
		notify();
	}

	@Override
	public void run() {
		if (UnitType.Test == unitType) {
			System.out.println();
		}
		while (!stopFlag) {

			if (this.attackStrategy != null && (this.targetedUnit != null || this.findTarget())) {
				this.attack();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (this.movement != null) {
				try {
					System.out.print(this.getCenter());
					this.move();
					log.updateUnitMovement(this);
					
					Set<Unit> newUnitObservers = new HashSet<Unit>();

					for (Unit unit : unitObservers) {
						boolean bind = notify(unit);

						if (bind) {
							newUnitObservers.add(unit);
						} else if (!bind) {

							if (unit.getUnitType() == UnitType.River && !bind) {
								System.out.println("River (unbind)");
								this.resetMovementUnitSpeedProperty(
										((SteppedOnRiverResult)this.getProperties()[4]).unwrap());								
							}
						}

					}
					unitObservers = newUnitObservers;

					Unit river = this.unitGrid.getCurrentRiver(this);
					if (river != null && !this.containsObserver(river)) {
						
						synchronized (river) {
							river.request(this);
							this.resetMovementUnitSpeedProperty(new SteppedOnRiverResult(
									this.getProperties()[4]));
						}
					}

					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();

				}
			}
		}

		System.out.println(unitType + " has died");
//            synchronized (this) {
//                while (pauseFlag) {
//                    try {
//                        wait();
//                    } catch (InterruptedException ex) {
//                        //Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//            }
	}

	public static void main(String[] args) {

		UnitFactory unitFactory = UnitFactory.getInstance();
		AttackStrategy unitStrategy = HighestDamageAttackStrategy.getInstance();
		Grid grid = Grid.getInstance();

		try {
			Unit a = unitFactory.createUnit(UnitType.Test, new Complex(-10, -10), 1);
			Unit b = unitFactory.createUnit(UnitType.Test, new Complex(0, 0), 1);
			Unit c = unitFactory.createUnit(UnitType.Test, new Complex(0, -5), 1);
			
			Unit d = unitFactory.createUnit(UnitType.BlackEagle, new Complex (0, 0), 1);
			Unit dd = unitFactory.createUnit(UnitType.BlackEagle, new Complex (0, 0), 1);

			Unit base = unitFactory.createUnit(UnitType.MainBase, new Complex(100, 100), 1);
			grid.setBase(base);

//			Unit river = unitFactory.createUnit(UnitType.River, new Complex(0, 0), 1);

			base.start();
//			a.setMovement(null);
//			river.setMovement(null);
			a.start();
//			System.out.println(a.getMovementUnitSpeedProperty());

			b.start();
			c.start();
//			river.start();
//			System.out.pr
			Thread.sleep(5000);
			//return;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

//    	// testing movement
//    	UnitFactory unitFactory = UnitFactory.getInstance();
//    	AttackStrategy unitStrategy = HighestDamageAttackStrategy.getInstance();
//    	try {
//			Unit a = unitFactory.createUnit(UnitType.Test, new Complex(0, 0), unitStrategy, 1);
//			a.unitGrid.put(a);
//			Unit b = unitFactory.createUnit(UnitType.Test, new Complex(15, 15), unitStrategy, 1);
//			b.unitGrid.put(b);
//			
//			Unit c = unitFactory.createUnit(UnitType.Test, new Complex(0, -5), unitStrategy, 1);
//			c.unitGrid.put(c);
//			
//			try {
//				c.move();				
//			} catch (Exception e) {}
//			try {
//				b.unitGrid.moveUnit(b, new Complex(-10, -10));				
//			} catch (Exception e) {}
//			try {
//				a.unitGrid.moveUnit(a, new Complex(10, 10));
//			} catch (Exception e) {}
//			System.out.println(a.getPositions().getCenter());
//			System.out.println(b.getPositions().getCenter());
//			System.out.println(c.getPositions().getCenter());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			
//			e.printStackTrace();
//		}
// 
//    	// testing attacking
//    	UnitFactory unitFactory = UnitFactory.getInstance();
//    	try {
//    		Unit a = unitFactory.createUnit(UnitType.Test, new Complex(0, 0));
//    		a.unitGrid.put(a);
//    		
//    		Unit b = unitFactory.createUnit(UnitType.Test, new Complex(0, 10));
//    		b.unitGrid.put(b);
//    		
//    		Unit c = unitFactory.createUnit(UnitType.Test, new Complex(-10, 0));
//    		c.unitGrid.put(c);
//    		
//    		System.out.println(a.findTarget());
//    		while (a.targetedUnit != null) {
//    			System.out.println("before, c's health = " + b.unitProperties[0].propertyvalue);
//    			System.out.println(a.getTargetedUnit().getCenter());
//    			a.attack();
//    			System.out.println("after, c's health = " + b.unitProperties[0].propertyvalue); 
//    			Thread.sleep(1000);
//    		}
//
//    	} catch (Exception e) {
//    		e.printStackTrace();
//    	}

	}

}

