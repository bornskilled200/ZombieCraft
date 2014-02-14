package zombiecraft.human;


import zombiecraft.GameModel;
import zombiecraft.UnitData;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

import java.util.ArrayList;
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
        super(NAME, 22);
        setHealth(250);

        allUnits = new ArrayList<UnitData>();
        allUnits.add(new Human());
        allUnits.add(new Scout());
        allUnits.add(new Mercenary());
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
