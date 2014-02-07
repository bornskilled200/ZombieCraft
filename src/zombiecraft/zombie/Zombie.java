package zombiecraft.zombie;


import zombiecraft.Unit;
import zombiecraft.UnitData;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/4/14
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Zombie extends UnitData
{

    public static final int RANGE = 64;
    public static final String NAME = "Zombie";

    public Zombie()
    {
        super(75, NAME, "The remains of a deceased living being made autonomous", false);
    }

    @Override
    public float offsetDirection(int time, Unit unit)
    {
        return time==0?0:((time/40*877+70)%140-70);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, Unit unit)
    {
        return 25;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, Unit unit, Unit target)
    {
        float dx = unit.getX() - target.getX();
        float dy = unit.getY() - target.getY();
        float v = dx * dx + dy * dy;
        int i = RANGE * RANGE + RANGE * RANGE;
        if (v < i)
            return 1;
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
