package zombiecraft.human;


import zombiecraft.GameModel;
import zombiecraft.Unit;
import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/12/14
 * Time: 8:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class Mercenary extends UnitData
{
    public static final String NAME = "Mercenary";

    protected Mercenary()
    {
        super(25, NAME, "Pay me and I will get rid of your enemies. My services are not cheap.", false, 32,
              125);
    }

    @Override
    public int getHurtRadius()
    {
        return 48;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetDirection(int time, GenericMovableUnit unit)
    {
        return time < 80 ? 0 : (90+(time - 80) *
                                .6f);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float offsetVelocity(int time, GenericMovableUnit unit)
    {
        return 20;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getVisionRadius()
    {
        return 48;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int doDamage(int time, GenericMovableUnit unit, Unit target)
    {
        if (isAttackable(unit, target))
            return 2;  //To change body of implemented methods use File | Settings | File Templates.
        return 0;
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
