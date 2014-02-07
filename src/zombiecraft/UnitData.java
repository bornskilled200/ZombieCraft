package zombiecraft;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class UnitData {
    private int health;
    private String name;
    private String description;
    private boolean isMovingWhenDoingDamgage;

    protected UnitData(int health, String name, String description, boolean movingWhenDoingDamgage) {

        this.health = health;
        this.name = name;
        this.description = description;
        isMovingWhenDoingDamgage = movingWhenDoingDamgage;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract float offsetDirection(int time, Unit unit);

    public abstract float offsetVelocity(int time, Unit unit);

    /**
     * if float is not 0, the unit will move, otherwise it will move
     * @param time
     * @param unit
     * @param target
     * @return
     */
    public abstract int doDamage(int time, Unit unit, Unit target);

    public boolean isMovingWhenDoingDamgage()
    {
        return isMovingWhenDoingDamgage;  //To change body of created methods use File | Settings | File Templates.
    }
}
