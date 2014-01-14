package craft.zombie;

import craft.MainBuilding;
import craft.Unit;

import java.util.Collections;
import java.util.List;

/**
 * Created by David Park on 1/7/14.
 */
public class Brain extends MainBuilding {
    public Brain() {
        setHealth(100);
    }

    @Override
    public List<Unit> buildableUnits() {
        return Collections.emptyList();
    }

    @Override
    public void act() {

    }
}
