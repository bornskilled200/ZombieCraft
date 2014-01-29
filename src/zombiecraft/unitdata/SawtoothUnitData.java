package zombiecraft.unitdata;


import zombiecraft.Unit;
import zombiecraft.UnitData;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SawtoothUnitData extends UnitData {
    private final int triangleLength;
    private int velocity;

    protected SawtoothUnitData(int health, String name, String description, int triangleLength, int velocity) {
        super(health, name, description);
        this.triangleLength = triangleLength;
        this.velocity = velocity;
    }

    @Override
    public float offsetDirection(int time, Unit unit) {
        if (time < triangleLength)
            return 0;
        return (time / triangleLength) % 2 == 0 ? 45 : -45;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, Unit unit) {
        return velocity;
    }
}
