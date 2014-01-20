package zombiecraft.player;

import zombiecraft.Player;
import zombiecraft.Race;
import zombiecraft.screen.GameMap;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

/**
 * Created by David Park on 1/7/14.
 */
public class ComputerPlayer extends Player {
    public ComputerPlayer(Race race) {
        super(race);
    }


    public void poll(GameMap gameMap, MainBuilding mainBuilding) {
    }
}
