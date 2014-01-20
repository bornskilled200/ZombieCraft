package zombiecraft.unit;

import zombiecraft.Unit;
import zombiecraft.UnitData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Park on 1/10/14.
 */
public abstract class MainBuilding extends Unit {

    private List<Unit> units;

    public MainBuilding(String name) {
        super(name);
        this.units = new ArrayList<Unit>();
    }

    public List<Unit> enteredUnits() {
        return units;
    }

    public abstract List<UnitData> buildableUnits();
}
