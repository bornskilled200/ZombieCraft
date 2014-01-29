package zombiecraft.unit;


import zombiecraft.UnitData;

/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericMovableUnit extends MovableUnit {
    private UnitData unitData;
    private int currenttime;
    private float generalDirection;
    private float generalVelocity;

    public UnitData getUnitData() {
        return unitData;
    }

    @Override

    public void act(int time) {
        currenttime = time - getTime();

        super.setVelocity(generalVelocity += unitData.addVelocity(currenttime, this));
        super.setDirection(generalDirection += unitData.addDirection(currenttime, this));

        super.act(time);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void setVelocity(float velocity) {
        generalVelocity = velocity;
    }

    @Override
    public void setDirection(float direction) {
        generalDirection = direction;
    }

    public GenericMovableUnit(UnitData unitData) {
        super(unitData.getName());
        this.unitData = unitData;
        setHealth(unitData.getHealth());
    }
}
