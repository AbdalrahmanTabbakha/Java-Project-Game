package project5;

import java.util.AbstractCollection.*;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoDGameManager {
 private Grid grid;
 private Unit mainBase;
 private double remainingTime;
 private int remainingAttackerUnits;
 private ArrayList<Team>team;
 private UnitFactory unitFactory;
 private ArrayList<Unit>AllUnit;
 public void readFromFile(  )
 {
     try {  
         OutputStream os = new FileOutputStream("C:\\Users\\ASUS\\Desktop");
         Scanner Sc=new Scanner((Readable) os);
         remainingTime=Sc.nextDouble();
         int teamId=Sc.nextInt();
         team.get(teamId).setTeamId(teamId);
         int n=Sc.nextInt();
         team.get(teamId).setNumberPlayers(n);
         int numberUnit=Sc.nextInt();
         for(int i=0;i<n;i++)
         {
             for(int j=0;j<numberUnit;j++)
         {   int x=Sc.nextInt();
             int y=Sc.nextInt();
            Complex c = null;
            c.setImag(x);
            c.setReal(y);
            String unitType;
            unitType=Sc.nextLine();
            UnitType unitType1;
            unitType1=UnitType.getTypeInFile(unitType);
            buyUnit( c, unitType1, team.get(teamId));
            
         }
             
         }
         int teamId1=Sc.nextInt();
         team.get(teamId1).setTeamId(teamId1);
         int m=Sc.nextInt();
         team.get(teamId1).setNumberPlayers(m);
         int numberUnit1=Sc.nextInt();
         for(int i=0;i<m;i++)
         {
             for(int j=0;j<numberUnit1;j++)
         {   int x=Sc.nextInt();
             int y=Sc.nextInt();
            Complex c = null;
            c.setImag(x);
            c.setReal(y);
            String unitType;
            unitType=Sc.nextLine();
             UnitType unitType2;
            unitType2=UnitType.getTypeInFile(unitType);
            buyUnit( c, unitType2, team.get(teamId1));
            
         }
             
         }
         
     } catch (FileNotFoundException ex) {
         Logger.getLogger(DoDGameManager.class.getName()).log(Level.SEVERE, null, ex);
     } catch (Exception ex) {
         Logger.getLogger(DoDGameManager.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
 public void buyUnit(Complex c,UnitType unitType,Team team1) throws Exception
 {
  
   team1.addToTeam(unitFactory.createUnit(unitType, c,team1.getTeamId()));
   team1.coins=team1.coins-unitType.getCost();
   
 }
 public void addToArray(Unit unit)
 {
     AllUnit.add(unit);
 }
 public ArrayList<Unit> getArray()
 {
     return AllUnit;
 }
 public DoDGameManager(Grid grid1,Unit mainBase1,double remainingTime1,int remainingAttackerUnits1,UnitFactory unitFactory1 )
 {
     grid=grid1;
     mainBase=mainBase1;
     remainingTime=remainingTime1;
     remainingAttackerUnits=remainingAttackerUnits1;
     unitFactory=unitFactory1;
     
 }
 
 
}