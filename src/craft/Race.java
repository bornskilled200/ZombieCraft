package craft;

import craft.human.Base;
import craft.zombie.Brain;

import java.util.List;

/**
 * Created by David Park on 1/7/14.
 */
public enum Race {
    ZOMBIE {
        @Override
        public Unit getMainBuilding() {
            return new Brain();
        }

        @Override
        public List<Unit> getBuildableUnits() {
            return null;
        }

        @Override
        public List<Trait> getStartingTraits() {
            return null;
        }
    }, HUMAN {
        @Override
        public Unit getMainBuilding() {
            return new Base();
        }

        @Override
        public List<Unit> getBuildableUnits() {
            return null;
        }

        @Override
        public List<Trait> getStartingTraits() {
            return null;
        }
    };

    public abstract Unit getMainBuilding();

    public abstract List<Unit> getBuildableUnits();

    public abstract List<Trait> getStartingTraits();
}
