package project5;

public class AttackSpeedUnitProperty extends UnitProperty {

    public AttackSpeedUnitProperty(double amount) {
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

}