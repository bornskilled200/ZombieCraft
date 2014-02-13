package zombiecraft.unitdata;


import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class SawtoothUnitData extends UnitData {
    private final int triangleLength;
    private int striaghtLine;
    private int angle;

    protected SawtoothUnitData(int health, String name, String description, int hitRadius, int productionDelay, int triangleLength, int striaghtLine, int angle) {
        super(health, name, description, false, hitRadius, productionDelay);
        this.triangleLength = triangleLength;
        this.striaghtLine = striaghtLine;
        this.angle = angle;
    }

    @Override
    public float offsetDirection(int time, GenericMovableUnit unit) {
        if (time < striaghtLine)
            return 0;
        return (time / (triangleLength-striaghtLine)) % 2 == 0 ? angle : -angle;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
