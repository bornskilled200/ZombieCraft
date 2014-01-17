package craft;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 6:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class SawtoothUnitData extends UnitData {
    private final float triangleLength;
    private final int velocity;

    protected SawtoothUnitData(int health, String name, String description, float triangleLength, int velocity) {
        super(health, name, description);
        this.triangleLength = triangleLength;
        this.velocity = velocity;
    }

    @Override
    public float getDirection(int time) {
        return time % triangleLength == 0 ? 90 : -90;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getVelocity(int time) {
        return velocity;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
