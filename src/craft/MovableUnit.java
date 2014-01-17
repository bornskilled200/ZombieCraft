package craft;


/**
 * Created by David Park on 1/10/14.
 */
public abstract class MovableUnit extends Unit {
    private float previousX;
    private float previousY;
    private float dx;
    private float dy;
    private int time;

    @Override
    public void act(int time) {
        previousX = getX();
        previousY = getY();

        float velocity = getVelocity(time - this.time);
        if (velocity == 0)
            dx = dy = 0;
        else {
            float direction = getDirection(time - this.time);
            //if (direction!=0), optimization?

            dx = (float) (StrictMath.cos(direction)) * direction * velocity;
            dy = (float) (StrictMath.sin(direction)) * direction * velocity;
        }

        setX(getX() + dx);
        setY(getY() + dy);
    }

    public abstract float getDirection(int time);

    public abstract float getVelocity(int time);

    public void setTime(int time) {
        this.time = time;
    }

    public float getPreviousX() {
        return previousX;
    }

    public float getPreviousY() {
        return previousY;
    }
}
