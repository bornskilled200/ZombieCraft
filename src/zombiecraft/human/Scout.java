package zombiecraft.human;


import zombiecraft.GameModel;
import zombiecraft.Unit;
import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;


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
        super(25, NAME, "Straight path unit that brings back other units.", false, 20, 20);
    }

    @Override
    public int getHurtRadius()
    {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetDirection(int time, GenericMovableUnit unit)
    {
        return 0;
    }

    @Override
    public float offsetVelocity(int time, GenericMovableUnit unit)
    {
        return 50;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getVisionRadius()
    {
        return 96;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, GenericMovableUnit unit, Unit target)
    {
        return 0;
    }

    @Override
    public void doCollision(int time, GenericMovableUnit unit, Unit target)
    {
        if (target instanceof GenericMovableUnit && isColliding(unit, target))
        {
            GenericMovableUnit genericMovableUnit = (GenericMovableUnit) target;
            unit.getStates().add(GenericMovableUnit.State.RETREAT);
            genericMovableUnit.getStates().add(GenericMovableUnit.State.RETREAT);
        }
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
