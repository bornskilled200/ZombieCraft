package zombiecraft.zombie;


import zombiecraft.GameModel;
import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by David Park on 1/7/14.
 */
public class  Brain extends MainBuilding
{
    public static final String NAME = "Brain";
    private ArrayList<UnitData> allUnits;

    public Brain()
    {
        super(NAME, 20);
        setHealth(250);
        allUnits = new ArrayList<UnitData>();
        allUnits.add(new Zombie());
        allUnits.add(new Crasher());
    }

    @Override
    public GenericMovableUnit createUnit(UnitData unitData)
    {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<UnitData> buildableUnits()
    {
        return allUnits;
    }

    @Override
    public int getVisionRadius()
    {
        return 128;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
