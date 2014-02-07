package zombiecraft.zombie;


import zombiecraft.Unit;
import zombiecraft.UnitData;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/6/14
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Crasher extends UnitData
{
    private static final int RANGE = 32;

    protected Crasher()
    {
        super(200, "Crasher", "Big mean thingamabob.", true);
    }

    @Override
    public float offsetDirection(int time, Unit unit)
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, Unit unit)
    {
        return 40;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, Unit unit, Unit target)
    {
        float dx = unit.getX() - target.getX();
        float dy = unit.getY() - target.getY();
        float v = dx * dx + dy * dy;
        int i = RANGE * RANGE + RANGE * RANGE;
        if (v < i)
        {
            System.out.println(unit + " vs " + target);
            return 2;
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
