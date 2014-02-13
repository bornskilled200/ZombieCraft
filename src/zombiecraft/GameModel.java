package zombiecraft;


import zombiecraft.unit.MainBuilding;

import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 * User: David
 * Date: 2/4/14
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public interface GameModel
{

    Map<Player, MainBuilding> getMainBuildingMap();

    List<MainBuilding> getMainBuildings();

    List<Unit> getUnits();

    Map<Unit, Player> getPlayerMap();

    List<Player> getPlayers();

    void addUnit(Player player, Unit unit);

    public float getSecondsPerUpdate();

    public int getCurrentUpdate();
}
