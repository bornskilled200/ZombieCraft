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
    private final float triangleLength;
    private final float velocity;

    protected SawtoothUnitData(int health, String name, String description, float triangleLength, float velocity) {
        super(health, name, description);
        this.triangleLength = triangleLength;
        this.velocity = velocity;
    }

    @Override
    public float addDirection(int time, Unit unit) {
        if (time == 0)
            return 45;
        return time % triangleLength == 0 ? (time / triangleLength % 2 == 0 ? 90 : -90) : 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float addVelocity(int time, Unit unit) {
        return time == 0 ? velocity : 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
