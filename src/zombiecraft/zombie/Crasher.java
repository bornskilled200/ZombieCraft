package zombiecraft.zombie;


import zombiecraft.GameModel;
import zombiecraft.Unit;
import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/6/14
 * Time: 3:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Crasher extends UnitData
{

    protected Crasher()
    {
        super(200, "Crasher", "Big mean thingamabob.", true, 22, 20);
    }

    @Override
    public int getHurtRadius()
    {
        return 22;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetDirection(int time, GenericMovableUnit unit)
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, GenericMovableUnit unit)
    {
        return 80;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getVisionRadius()
    {
        return 32;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, GenericMovableUnit unit, Unit target)
    {
        if (isAttackable(unit,target))
        {
            return 2;
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
