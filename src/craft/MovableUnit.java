package craft;

/**
 * Created by David Park on 1/10/14.
 */
public abstract class MovableUnit extends Unit {

    public abstract int getX(int time);

    public abstract int getY(int time);

    public abstract float getPreviousX();

    public abstract float getPreviousY();
}
