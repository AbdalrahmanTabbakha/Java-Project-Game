package project5;

import java.util.NavigableMap;
import java.util.TreeMap;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UnitType[] t = { UnitType.TeslaTank, UnitType.BlackEagle, UnitType.GrizzlyTank};
		
		for (int i = 0; i < t.length; ++i)
			try {
				if (t[i].getAbstraction() == UnitType.Airplane) System.out.println(true);
				else System.out.println(false);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
