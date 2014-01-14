package craft;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Park on 1/10/14.
 */
public abstract class MainBuilding extends Unit {

    private List<Unit> units;

    protected MainBuilding() {
        this.units = new ArrayList<Unit>();
        ;
    }

    public List<Unit> enteredUnits() {
        return units;
    }

    public abstract List<Unit> buildableUnits();
}
