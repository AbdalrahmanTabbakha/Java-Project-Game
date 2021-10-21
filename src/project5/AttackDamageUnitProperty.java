package project5;

public class AttackDamageUnitProperty extends UnitProperty {

    public AttackDamageUnitProperty(double pv) {
        propertyvalue = pv;
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