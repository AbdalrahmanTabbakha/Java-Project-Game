package project5;


public class SeizerUnitGrid extends UnitGrid {
	private static SeizerUnitGrid seizerUnitGrid;
	
	private SeizerUnitGrid() {
		gridBlock = new GridBlock(SeizerGridController.getInstance());
	}
	
	public static SeizerUnitGrid getInstance() { 
		if (seizerUnitGrid == null) seizerUnitGrid = new SeizerUnitGrid();
		return seizerUnitGrid;
	}
}
