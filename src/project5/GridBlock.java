package project5;

import java.util.NavigableMap;

public class GridBlock {
	private GridController gridController;
	
	public GridBlock(GridController gc) { gridController = gc; }

	public void remove(ComplexBlock blk, Object obj) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getFirst().getReal(); i < blk.getSecond().getReal(); ++i)
			for (int j = blk.getFirst().getImag(); j < blk.getSecond().getImag(); ++j)
				gridController.remove(new Complex(i, j), obj);
	}
	
	public void rRemove(ComplexBlock blk, Object obj) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getSecond().getReal(); i > blk.getFirst().getReal(); --i)
			for (int j = blk.getSecond().getImag(); j > blk.getFirst().getImag(); --j)
				gridController.remove(new Complex(i, j), obj);
	
	}
	
	public void put(ComplexBlock blk, Object obj) {
		blk.orderBottomLeftToTopRight();
		
		for (int i = blk.getFirst().getReal(); i < blk.getSecond().getReal(); ++i)
			for (int j = blk.getFirst().getImag(); j < blk.getSecond().getImag(); ++j)
				gridController.put(new Complex(i, j), obj);
		
	}
	
	public void rPut(ComplexBlock blk, Object obj) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getSecond().getReal(); i > blk.getFirst().getReal(); --i)
			for (int j = blk.getSecond().getImag(); j > blk.getFirst().getImag(); --j)
				gridController.put(new Complex(i, j), obj);

	}
	
	public boolean isEmpty(ComplexBlock blk) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getFirst().getReal(); i < blk.getSecond().getReal(); ++i)
			for (int j = blk.getFirst().getImag(); j < blk.getSecond().getImag(); ++j) 
				if (!gridController.isEmpty(new Complex(i, j))) return false;

		return true;
	}
	
	public boolean rIsEmpty(ComplexBlock blk) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getSecond().getReal(); i > blk.getFirst().getReal(); --i)
			for (int j = blk.getSecond().getImag(); j > blk.getFirst().getImag(); --j)
				if (!gridController.isEmpty(new Complex(i, j))) return false;

		return true;
	}
	
	public boolean isFilled(ComplexBlock blk) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getFirst().getReal(); i < blk.getSecond().getReal(); ++i)
			for (int j = blk.getFirst().getImag(); j < blk.getSecond().getImag(); ++j) 
				if (gridController.isFilled(new Complex(i, j))) return true;

		return false;
	}
	
	public boolean rIsFilled(ComplexBlock blk) {
		blk.orderBottomLeftToTopRight();

		for (int i = blk.getSecond().getReal(); i > blk.getFirst().getReal(); --i)
			for (int j = blk.getSecond().getImag(); j > blk.getFirst().getImag(); --j)
				if (gridController.isFilled(new Complex(i, j))) return true;

		return false;
	}
	
	public boolean contains(ComplexBlock blk, Object obj) {
	
		for (int i = blk.getFirst().getReal(); i < blk.getSecond().getReal(); ++i)
			for (int j = blk.getFirst().getImag(); j < blk.getSecond().getImag(); ++j) 
				if (!gridController.contains(new Complex(i, j), obj)) return false;
		
		return true;
	}
        public void PrintInArea(Complex c1,Complex c2)
       {
           for(int i=c1.getReal();i<c2.getReal()+1;i++)
               for(int j=c1.getImag();j<c2.getImag()+1;j++)
               {   Complex c = null;
                   c.setReal(i);
                   c.setImag(j);
                   if(gridController.grid.get(c)!=null)
                   {
                       Unit temp=(Unit) gridController.grid.get(c);
                       System.out.println("The Unit Type :"+temp.getArmorUnitProperty());
                       System.out.println("The Health for Unit :"+temp.getHealthProperty());
                       System.out.println("The Attack Damage for Unit :"+temp.getAttackDamageUnitProperty());
                       System.out.println("The Armor for Unit :"+temp.getArmorUnitProperty());
                       System.out.println("The Attack Speed for Unit :"+temp.getAttackSpeedUnitProperty());
                       System.out.println("The Movement Speed for Unit :"+temp.getMovementUnitSpeedProperty());
                       
                   }
                   
                
               }
       }
	
	

	public static void main(String[] args) {
		
	}
	
}
