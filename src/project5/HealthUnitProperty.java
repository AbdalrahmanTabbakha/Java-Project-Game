package project5;

public class HealthUnitProperty extends UnitProperty {
  
  public HealthUnitProperty(double amount) {
    propertyvalue = cons = amount;
  }
  
   @Override
    public void set_property_value(double amount) {
        propertyvalue = amount;
    }

    @Override
    public double get_property_value() {
        return propertyvalue;
    }
     public void set_cons_value(double amount)
     {
        cons=amount; 
     }
  
}