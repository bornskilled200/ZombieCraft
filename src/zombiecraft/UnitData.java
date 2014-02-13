package zombiecraft;


import zombiecraft.unit.GenericMovableUnit;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 1/16/14
 * Time: 4:43 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class UnitData
{
    private int health;
    private String name;
    private String description;
    private boolean isMovingWhenDoingDamage;
    private int hitRadius;
    private int productionDelay;

    protected UnitData(int health, String name, String description, boolean movingWhenDoingDamage, int hitRadius, int productionDelay)
    {

        this.health = health;
        this.name = name;
        this.description = description;
        isMovingWhenDoingDamage = movingWhenDoingDamage;
        this.hitRadius = hitRadius;
        this.productionDelay = productionDelay;
    }

    public static boolean isDistanceShorterThan(float x, float y, float otherX, float otherY, float distance)
    {
        float dx = x - otherX;
        float dy = y - otherY;
        return dx * dx + dy * dy < distance * distance + distance * distance;
    }

    public static boolean isColliding(Unit unit, Unit other)
    {
        float dx = unit.getX() - other.getX();
        float dy = unit.getY() - other.getY();
        int hitRadius = unit.getHitRadius() + other.getHitRadius();
        return dx * dx + dy * dy < hitRadius * hitRadius;
    }

    public static boolean isAttackable(GenericMovableUnit unit, Unit other)
    {
        float dx = unit.getX() - other.getX();
        float dy = unit.getY() - other.getY();
        int hitHurtRadius = unit.getHurtRadius() + other.getHitRadius();
        return dx * dx + dy * dy < hitHurtRadius * hitHurtRadius;
    }

    public int getProductionDelay()
    {
        return productionDelay;
    }

    public abstract int getHurtRadius();

    public int getHitRadius()
    {
        return hitRadius;
    }

    public int getHealth()
    {
        return health;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public abstract float offsetDirection(int time, GenericMovableUnit unit);

    public abstract float offsetVelocity(int time, GenericMovableUnit unit);

    public abstract int getVisionRadius();

    /**
     * if float is not 0, the unit will move, otherwise it will move. This is also based on, isMovingWhenDoingDamage.
     *
     * @param time
     * @param unit
     * @param target
     * @return
     */
    public abstract int doDamage(int time, GenericMovableUnit unit, Unit target);

    public boolean isMovingWhenDoingDamage()
    {
        return isMovingWhenDoingDamage;  //To change body of created methods use File | Settings | File Templates.
    }

    public abstract void doCollision(int time, GenericMovableUnit unit, Unit target);

    public abstract void preAct(int time, GenericMovableUnit unit, GameModel gameModel);

    public abstract void postAct(int time, GenericMovableUnit unit, GameModel gameModel);
}
