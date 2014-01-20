package zombiecraft.human;

import zombiecraft.unit.MainBuilding;
import zombiecraft.UnitData;

import java.util.Collections;
import java.util.List;

/**
 * Created by David Park on 1/7/14.
 */
public class Base extends MainBuilding {
    public static final String NAME = "Base";

    public Base() {
        super(NAME);
        setHealth(100);
    }

    @Override
    public List<UnitData> buildableUnits() {
        return Collections.emptyList();
    }

    @Override
    public void act(int time) {

    }
}
