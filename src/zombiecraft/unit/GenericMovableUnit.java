package zombiecraft.unit;


import zombiecraft.GameModel;
import zombiecraft.Player;
import zombiecraft.Unit;
import zombiecraft.UnitData;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericMovableUnit extends MovableUnit
{
    private UnitData unitData;
    private int currenttime;
    private float generalDirection;
    private float generalVelocity;

    public GenericMovableUnit(UnitData unitData)
    {
        super(unitData.getName());
        this.unitData = unitData;
        setHealth(unitData.getHealth());
    }

    public UnitData getUnitData()
    {
        return unitData;
    }

    @Override

    public void act(int time, GameModel gameModel)
    {
        Player player = gameModel.getPlayerMap().get(this);
        for (Unit unit : gameModel.getUnits())
        {
            if (gameModel.getPlayerMap().get(unit) == player)
                continue;

            //System.out.println(player + " vs " + gameModel.getPlayerMap().get(unit));
            int damage = unitData.doDamage(time, this, unit);
            if (damage != 0)
            {
                System.out.println(this + " damaged " + unit + " for " + damage);
                unit.setHealth(unit.getHealth() - damage);
                if (unitData.isMovingWhenDoingDamgage()==false)
                {
                    super.setVelocity(0);
                    super.act(time, gameModel);
                    return;
                }
            }
        }

        super.setVelocity(generalVelocity + unitData.offsetVelocity(currenttime, this));
        super.setDirection(generalDirection + unitData.offsetDirection(currenttime, this));
        currenttime++;
        super.act(time, gameModel);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void setVelocity(float velocity)
    {
        generalVelocity = velocity;
    }

    @Override
    public void setDirection(float direction)
    {
        generalDirection = direction;
    }
}
