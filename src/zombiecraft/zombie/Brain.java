package zombiecraft.zombie;


import zombiecraft.GameModel;
import zombiecraft.UnitData;
import zombiecraft.unit.MainBuilding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David Park on 1/7/14.
 */
public class Brain extends MainBuilding
{
    public static final String NAME = "Brain";
    private ArrayList<UnitData> allUnits;

    public Brain()
    {
        super(NAME);
        setHealth(250);
        allUnits = new ArrayList<UnitData>();
        allUnits.add(new Zombie());
        allUnits.add(new Crasher());
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
