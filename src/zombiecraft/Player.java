package zombiecraft;


import zombiecraft.screen.GameMap;
import zombiecraft.unit.MainBuilding;


/**
 * Controller, accesses both the View and Model
 * Created by David Park on 1/7/14.
 */
public abstract class Player
{
    public final Race race;
    public int selection;
    //0 when it can produce a unit
    public int productionDelay;
    public int productionDelayLength;

    public Race getRace()
    {
        return race;
    }

    public int getProductionDelayLength()
    {
        return productionDelayLength;
    }

    public void setProductionDelayLength(int productionDelayLength)
    {
        if (productionDelayLength<getProductionDelay())
            throw new IllegalArgumentException();
        this.productionDelayLength = productionDelayLength;
    }

    public Player(Race race)
    {
        this.race = race;
    }

    public int getSelection()
    {
        return selection;
    }

    public void setSelection(int selection)
    {
        this.selection = selection;
    }

    public int getProductionDelay()
    {
        return productionDelay;
    }

    public void setProductionDelay(int productionDelay)
    {
        this.productionDelay = productionDelay;
    }



    public abstract void poll(GameMap gameMap, MainBuilding mainBuilding);
}
