package zombiecraft;

import com.badlogic.gdx.math.Vector3;

/**
 * Created by David Park on 2/9/14.
 */
public interface ViewModel {

    public void setUnprojectPosition(float x, float y);

    public float getUnprojectX();
    public float getUnprojectY();
}
