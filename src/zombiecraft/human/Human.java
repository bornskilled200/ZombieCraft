package zombiecraft.human;


import zombiecraft.Unit;
import zombiecraft.unitdata.SawtoothUnitData;


/**
 * Created by David Park on 1/20/14.
 */
public class Human extends SawtoothUnitData
{

    public static final String DESCRIPTION =
            "A regular human that moves forward changing left and right. Like a drunken lizard that is searching for truth in a garbage can.";
    public static final String NAME = "Human";


    public Human()
    {
        super(100, NAME, DESCRIPTION, 40,40);
    }

    @Override
    public int doDamage(int time, Unit unit, Unit target)
    {
        float dx = unit.getX() - target.getX();
        float dy = unit.getY() - target.getY();
        float v = dx * dx + dy * dy;
        int i = 64 * 64 + 64 * 64;
        if (v < i)
        {
            return 1;
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
