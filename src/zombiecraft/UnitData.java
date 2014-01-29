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

    protected UnitData(int health, String name, String description) {

        this.health = health;
        this.name = name;
        this.description = description;
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

    public abstract float addDirection(int time, Unit unit);

    public abstract float addVelocity(int time, Unit unit);
}
