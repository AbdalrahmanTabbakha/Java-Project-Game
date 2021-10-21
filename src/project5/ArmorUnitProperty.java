package project5;

public class ArmorUnitProperty extends UnitProperty {
  public ArmorUnitProperty(double amount) {
    propertyvalue = cons = amount;
  }
  
    public void set_property_value(double amount) {
        propertyvalue = amount;
    }

    public double get_property_value() {
        return propertyvalue;
    }
}