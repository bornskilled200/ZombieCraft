package zombiecraft;

import zombiecraft.screen.GameMap;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

import java.util.List;

/**
 * Created by David Park on 1/7/14.
 */
public abstract class Player {
    public final Race race;

    public Player(Race race) {
        this.race = race;
    }

    public abstract void poll(GameMap gameMap, MainBuilding mainBuilding);
}
