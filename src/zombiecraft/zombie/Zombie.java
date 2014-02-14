package zombiecraft.zombie;


import zombiecraft.GameModel;
import zombiecraft.Unit;
import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/4/14
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Zombie extends UnitData
{
    public static final String NAME = "Zombie";

    public Zombie()
    {
        super(75, NAME, "The remains of a deceased living being made autonomous", false, 20, 25);
    }

    @Override
    public int getHurtRadius()
    {
        return 20;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetDirection(int time, GenericMovableUnit unit)
    {
        return time==0?0:((time/40*877+70)%140-70);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, GenericMovableUnit unit)
    {
        return 25;  //To change body of implemented methods use File | Settings | File Templates.
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
            return 2;
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
