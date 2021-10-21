package project5;


public class OthersUnitGrid extends UnitGrid {
	private static OthersUnitGrid othersUnitGrid;
	
	private OthersUnitGrid() {
		gridBlock = new GridBlock(OthersGridController.getInstance());
	}
	
	public static OthersUnitGrid getInstance() { 
		if (othersUnitGrid == null) othersUnitGrid = new OthersUnitGrid();
		return othersUnitGrid;
	}
}
