package zombiecraft.human;


import zombiecraft.Unit;
import zombiecraft.UnitData;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/6/14
 * Time: 10:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Scout extends UnitData
{

    public static final String NAME = "Scout";

    public Scout()
    {
        super(25, NAME, "Straight path unit that brings back other units.", false);
    }

    @Override
    public float offsetDirection(int time, Unit unit)
    {
        return 0;
    }

    @Override
    public float offsetVelocity(int time, Unit unit)
    {
        return 6;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, Unit unit, Unit target)
    {
        return 0;
    }
}
