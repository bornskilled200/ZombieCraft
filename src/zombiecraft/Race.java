package zombiecraft;

import zombiecraft.human.Base;
import zombiecraft.human.Human;
import zombiecraft.unit.MainBuilding;
import zombiecraft.zombie.Brain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Park on 1/7/14.
 */
public enum Race {
    ZOMBIE {
        @Override
        public MainBuilding getMainBuilding() {
            return new Brain();
        }

        @Override
        public List<UnitData> getAllUnits() {
            return null;
        }

        @Override
        public List<Trait> getStartingTraits() {
            return null;
        }
    }, HUMAN {
        @Override
        public MainBuilding getMainBuilding() {
            return new Base();
        }

        private List<UnitData> allUnits;

        @Override
        public List<UnitData> getAllUnits() {
            if (allUnits == null) {
                allUnits = new ArrayList<UnitData>();
                allUnits.add(new Human());
            }
            return allUnits;
        }

        @Override
        public List<Trait> getStartingTraits() {
            return null;
        }
    };

    public abstract MainBuilding getMainBuilding();

    public abstract List<UnitData> getAllUnits();

    public abstract List<Trait> getStartingTraits();
}
