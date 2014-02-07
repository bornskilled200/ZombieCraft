package zombiecraft;

import zombiecraft.human.Base;
import zombiecraft.human.Human;
import zombiecraft.unit.MainBuilding;
import zombiecraft.zombie.Brain;
import zombiecraft.zombie.Zombie;

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
        public List<Trait> getStartingTraits() {
            return null;
        }
    }, HUMAN {
        @Override
        public MainBuilding getMainBuilding() {
            return new Base();
        }

        @Override
        public List<Trait> getStartingTraits() {
            return null;
        }
    };

    public abstract MainBuilding getMainBuilding();

    public abstract List<Trait> getStartingTraits();
}
