package zombiecraft.unit;


import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import zombiecraft.Unit;


/**
 * Created by David Park on 1/10/14.
 */
public abstract class MovableUnit extends Unit
{
    private float previousX;
    private float previousY;
    private float dx;
    private float dy;
    private float direction;
    private float velocity;
    private int time;

    public MovableUnit(String name)
    {
        super(name);
    }

    @Override
    public void act(int time)
    {
        previousX = getX();
        previousY = getY();

        setX(previousX + getVelocityX());
        setY(previousY + getVelocityY());
    }

    public void setVelocity(float dx, float dy)
    {
        this.dx = dx;
        this.dy = dy;
        float angle = (float) Math.atan2(new Vector2(dx, dy).y, new Vector2(dx, dy).x) * MathUtils.radiansToDegrees;
        if (angle < 0)
            angle += 360;
        direction = angle;
        velocity = (float) Math
                .sqrt(new Vector2(dx, dy).x * new Vector2(dx, dy).x + new Vector2(dx, dy).y * new Vector2(dx, dy).y);
    }

    public float getVelocity()
    {
        return velocity;
    }

    public void setVelocity(float velocity)
    {
        this.velocity = velocity;
        dx = MathUtils.cosDeg(direction) * velocity;
        dy = MathUtils.sinDeg(direction) * velocity;
    }

    public float getDirection()
    {
        return direction;
    }

    public void setDirection(float direction)
    {
        this.direction = direction;
        dx = MathUtils.cosDeg(direction) * velocity;
        dy = MathUtils.sinDeg(direction) * velocity;
    }

    public float getVelocityY()
    {
        return dy;
    }

    public float getVelocityX()
    {
        return dx;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public float getPreviousX()
    {
        return previousX;
    }

    public float getPreviousY()
    {
        return previousY;
    }
}
