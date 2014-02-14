package zombiecraft.unit;


import com.badlogic.gdx.math.MathUtils;
import zombiecraft.GameModel;
import zombiecraft.Player;
import zombiecraft.Unit;
import zombiecraft.UnitData;

import java.util.EnumSet;


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
    private boolean isPatternUsed;
    private EnumSet<State> states;

    public GenericMovableUnit(UnitData unitData)
    {
        super(unitData.getName(), unitData.getHitRadius());
        this.unitData = unitData;
        setHealth(unitData.getHealth());
        states = EnumSet.noneOf(State.class);

    }

    public EnumSet<State> getStates()
    {
        return states;
    }

    public boolean isPatternUsed()
    {
        return isPatternUsed;
    }

    public void setPatternUsed(boolean patternUsed)
    {
        isPatternUsed = patternUsed;
    }

    public UnitData getUnitData()
    {
        return unitData;
    }

    @Override
    public int getVisionRadius()
    {
        return unitData.getVisionRadius();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override

    public void act(int time, GameModel gameModel)
    {
        if (states.contains(State.RETREAT) == false)
        {
            Player player = gameModel.getPlayerMap().get(this);
            for (Unit unit : gameModel.getUnits())
            {
                if (gameModel.getPlayerMap().get(unit) == player)
                {
                    if (this!=unit)
                    unitData.doCollision(time,this,unit);
                }
                else
                {
                    //System.out.println(player + " vs " + gameModel.getPlayerMap().get(unit));
                    int damage = unitData.doDamage(time, this, unit);
                    if (damage != 0)
                    {
                        System.out.println(this + " damaged " + unit + " for " + damage);
                        unit.setHealth(unit.getHealth() - damage);
                        if (unitData.isMovingWhenDoingDamage() == false)
                        {
                            super.setVelocity(0);
                            float x = getX() + 32;
                            float y = getY() + 32;
                            float angle = (float) Math.atan2(unit.getY()+32 - y, unit.getX()+32 - x) * MathUtils.radiansToDegrees;
                            if (angle < 0)
                                angle += 360;
                            super.setDirection(angle);
                            super.act(time, gameModel);
                            return;
                        }
                    }
                }
            }

            super.setVelocity(generalVelocity + unitData.offsetVelocity(currenttime, this));
            super.setDirection(generalDirection + unitData.offsetDirection(currenttime, this));
            currenttime++;
            super.act(time, gameModel);    //To change body of overridden methods use File | Settings | File Templates.
        }
        else
        {
            MainBuilding mainBuilding = gameModel.getMainBuildingMap().get(gameModel.getPlayerMap().get(this));
            float angle = (float) Math.atan2(mainBuilding.getY() - getY(), mainBuilding.getX() - getX()) *
                          MathUtils.radiansToDegrees;
            if (angle < 0)
                angle += 360;
            super.setVelocity(50);
            super.setDirection(angle);
            super.act(time,gameModel);
        }
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

    public int getHurtRadius()
    {
        return unitData.getHurtRadius();
    }

    public float getGeneralDirection()
    {
        return generalDirection;
    }

    /**
     * A horrible hack for not being able to have state information for each individual unit
     */
    public enum State
    {
        RETREAT, GENERIC1, GENERIC2, GENERIC3, GENERIC4, GENERIC5, GENERIC6, GENERIC7

    }
}
