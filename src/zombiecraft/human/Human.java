package zombiecraft.human;


import zombiecraft.GameModel;
import zombiecraft.Unit;
import zombiecraft.unit.GenericMovableUnit;
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
        super(50, NAME, DESCRIPTION, 20, 200, 80, 20, 70);
    }

    @Override
    public int getHurtRadius()
    {
        return 36;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, GenericMovableUnit unit)
    {
        return 40;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getVisionRadius()
    {
        return 64;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, GenericMovableUnit unit, Unit target)
    {
        if (isAttackable(unit,target))
        {
            return 4;
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doCollision(int time, GenericMovableUnit unit, Unit target)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void preAct(int time, GenericMovableUnit unit, GameModel gameModel)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void postAct(int time, GenericMovableUnit unit, GameModel gameModel)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
