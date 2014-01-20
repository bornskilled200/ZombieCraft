package zombiecraft;

/**
 * Created by David Park on 1/6/14.
 */
public strictfp abstract class Unit {
    private String name;

    public Unit(String name) {
        this.name = name;
    }

    private int health;
    private float x;
    private float y;


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return getHealth() <= 0;
    }

    public abstract void act(int time);

    public String getName() {
        return name;
    }
}
