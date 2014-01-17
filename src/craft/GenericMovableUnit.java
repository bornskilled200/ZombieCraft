package craft;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class GenericMovableUnit extends MovableUnit {
    private UnitData unitData;

    @Override
    public void act(int time) {
        super.act(time);    //To change body of overridden methods use File | Settings | File Templates.

    }

    public GenericMovableUnit(UnitData unitData) {
        this.unitData = unitData;
        setHealth(unitData.getHealth());


    }

    @Override
    public float getDirection(int time) {
        return unitData.getDirection(time);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public float getVelocity(int time) {
        return unitData.getVelocity(time);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
