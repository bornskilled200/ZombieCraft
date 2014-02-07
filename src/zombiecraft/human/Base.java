package zombiecraft.human;


import zombiecraft.GameModel;
import zombiecraft.UnitData;
import zombiecraft.unit.MainBuilding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by David Park on 1/7/14.
 */
public class Base extends MainBuilding
{
    public static final String NAME = "Base";
    private List<UnitData> allUnits;


    public Base()
    {
        super(NAME);
        setHealth(250);

        allUnits = new ArrayList<UnitData>();
        allUnits.add(new Human());
        allUnits.add(new Scout());
    }

    @Override
    public List<UnitData> buildableUnits()
    {
        return allUnits;
    }

    @Override
    public void act(int time, GameModel gameModel)
    {

    }
}
