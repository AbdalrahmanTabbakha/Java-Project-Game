package project5;


import java.util.ArrayList;

public class Team {

	// remove teamUnit!
    private int numberPlayers;
    private int teamId;
    private ArrayList<Unit>teamUnit;
    public int coins=3000*teamUnit.size();
    public void addToTeam(Unit unit)
    {
      teamUnit.add(unit);
    }
    public void setTeamId(int a)
    {
       teamId=a; 
    }
     public int getTeamId( )
    {
       return teamId; 
    }
     public void setNumberPlayers(int b)
    {
       numberPlayers=b; 
    }
     public int getNumberPlayers( )
    {
       return numberPlayers; 
    }
     public int getCoins( )
    {
       return coins; 
    }
   // private UnitPosition teamUnitsIntializationPositions[];

}