package zombiecraft;


/**
 * Created by David Park on 1/6/14.
 */
public strictfp abstract class Unit
{
    private String name;
    private int hitRadius;
    private int health;
    private float x;
    private float y;

    public Unit(String name, int hitRadius)
    {
        this.name = name;
        this.hitRadius = hitRadius;
    }

    public abstract int getVisionRadius();

    public int getHitRadius()
    {
        return hitRadius;
    }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public void setPosition(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public int getHealth()
    {
        return health;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public boolean isAlive()
    {
        return getHealth() > 0;
    }

    public abstract void act(int time, GameModel gameModel);

    public String getName()
    {
        return name;
    }

    @Override
    public String toString()
    {
        return "Unit{" +
               "name='" + name + '\'' +
               ", health=" + health +
               ", x=" + x +
               ", y=" + y +
               '}';
    }
}
