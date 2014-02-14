package zombiecraft.unit;

import zombiecraft.GameModel;
import zombiecraft.Player;
import zombiecraft.Unit;
import zombiecraft.UnitData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Park on 1/10/14.
 */
public abstract class MainBuilding extends Unit {

    public MainBuilding(String name, int hitRadius) {
        super(name, hitRadius);
    }

    @Override
    public void act(int time, GameModel gameModel)
    {
        Player player = gameModel.getPlayerMap().get(this);
        if (player.getProductionDelay()>0)
            player.setProductionDelay(player.getProductionDelay()-1);
        for (Unit unit : gameModel.getUnits())
        {
            if (UnitData.isColliding(this,unit) && unit instanceof GenericMovableUnit)
            {
                GenericMovableUnit genericMovableUnit = (GenericMovableUnit) unit;
                if (genericMovableUnit.getStates().contains(GenericMovableUnit.State.RETREAT))
                {
                    unit.setHealth(0);
                    player.setProductionDelay(player.getProductionDelay() - genericMovableUnit.getUnitData().getProductionDelay());
                }
            }
        }
    }

    public abstract GenericMovableUnit createUnit(UnitData unitData);

    public abstract List<UnitData> buildableUnits();
}
