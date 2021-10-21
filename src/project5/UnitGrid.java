package project5;


public abstract class UnitGrid {
	protected GridBlock gridBlock;
	
	protected UnitGrid() {
//		gridBlock = GridBlock()
	}
	
	public boolean put(Unit unit) {
		ComplexBlock blk = new ComplexBlock(
				Complex.subAll(unit.getPositions().getCenter(), unit.getPositions().getRadius()),
				Complex.addAll(unit.getPositions().getCenter(), unit.getPositions().getRadius() + 1)
		);
		
		if (!gridBlock.isFilled(blk)) {
			gridBlock.put(blk, unit);
			return true;
		} else {
			return false;
		}
	}


	public void remove(Unit unit) {
		ComplexBlock blk = new ComplexBlock(
				Complex.subAll(unit.getPositions().getCenter(), unit.getPositions().getRadius()),
				Complex.addAll(unit.getPositions().getCenter(), unit.getPositions().getRadius() + 1)
		);

		gridBlock.remove(blk, unit);
	}
	
	public boolean isCenterOnGrid(Unit unit) {
		ComplexBlock blk = new ComplexBlock(
				unit.getCenter(),
				Complex.addAll(unit.getCenter(), 1)
		);
		
		return gridBlock.contains(blk, unit);
	}
	
	public Unit getCurrentRiver(Unit unit) {
//		ComplexBlock blk = new ComplexBlock(
//				unit.getCenter(),
//				Complex.addAll(unit.getCenter(), 1)
//		);

		Cell cell = (Cell) Grid.getInstance().get(unit.getCenter());
		if (cell != null && cell.getOthersSize() > 0 &&
				cell.getOthers().get(0).getUnitType() == UnitType.River) {
			return cell.getOthers().get(0);
		}
		
		return null;
}
	
	private boolean moveUnitX(Unit unit, Complex movement) {
		Complex oldCenter = unit.getPositions().getCenter();
		Complex newCenter = Complex.add(oldCenter, movement);
		
		int radius = unit.getPositions().getRadius();
		
		ComplexBlock rightBlock = new ComplexBlock(
				new Complex(oldCenter.getReal() + radius, oldCenter.getImag() + radius),
				new Complex(newCenter.getReal() + radius, newCenter.getImag() - radius - 1)
		);
		
		ComplexBlock leftBlock = new ComplexBlock(
				new Complex(oldCenter.getReal() - radius, oldCenter.getImag() + radius + 1),
				new Complex(newCenter.getReal() - radius, newCenter.getImag() - radius)
		);
	
		if (movement.getReal() > 0) {
			if (gridBlock.rIsFilled(rightBlock)) return false;
			gridBlock.rPut(rightBlock, unit);
			
			gridBlock.remove(leftBlock, unit);
		} else {
			if (gridBlock.isFilled(leftBlock)) return false;
			gridBlock.put(leftBlock, unit);
			
			gridBlock.rRemove(rightBlock, unit);
		}
		
		unit.getPositions().addX(movement.getReal());
		return true;
	}
	
	private boolean moveUnitY(Unit unit, Complex movement) {
		Complex oldCenter = unit.getPositions().getCenter();
		Complex newCenter = Complex.add(oldCenter, movement);
		
		int radius = unit.getPositions().getRadius();

		ComplexBlock topBlock = new ComplexBlock(
				new Complex(oldCenter.getReal() - radius - 1, oldCenter.getImag() + radius),
				new Complex(newCenter.getReal() + radius, newCenter.getImag() + radius)
		);
		
		ComplexBlock bottomBlock = new ComplexBlock(
				new Complex(oldCenter.getReal() - radius, oldCenter.getImag() - radius),
				new Complex(newCenter.getReal() + radius + 1, newCenter.getImag() - radius)
		);

		
		if (movement.getImag() > 0) {
			if (gridBlock.rIsFilled(topBlock)) return false; 
			gridBlock.rPut(topBlock, unit);
			
			gridBlock.remove(bottomBlock, unit);
		} else {
			if (gridBlock.isFilled(bottomBlock)) return false;
			gridBlock.put(bottomBlock, unit);
			
			gridBlock.rRemove(topBlock, unit);
		}		
		
		unit.getPositions().addY(movement.getImag());
		return true;
	}
	
	public void moveUnit(Unit unit, Complex movement) throws IllegalMovement {
		Complex cX = movement.getReal() > 0 ? new Complex(1, 0) : new Complex(-1, 0);
		Complex cY = movement.getImag() > 0 ? new Complex(0, 1) : new Complex(0, -1);
		
		Complex end = movement.abs();
		
		while (true) {
			if (end.getReal() <= 0 && end.getImag() <= 0) break;
			
			synchronized (unit) {
				if (end.getReal() > 0 && end.getImag() > 0) {
					if (!moveUnitX(unit, cX) || !moveUnitY(unit, cY))
						throw new IllegalMovement("Unit is stuck!\nCan't move unit from: " +
								unit.getCenter() + " to: " + 
								Complex.add(cY, Complex.add(unit.getCenter(), cX)) + ".");
					end.addReal(-1).addImag(-1);
				} else if (end.getReal() > 0) {
					moveUnitX(unit, cX);
					end.addReal(-1);
				} else if (end.getImag() > 0) {
					moveUnitY(unit, cY);
					end.addImag(-1);
				}
			}
		}
	}

}