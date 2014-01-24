package zombiecraft.human;

import zombiecraft.unitdata.SawtoothUnitData;

/**
 * Created by David Park on 1/20/14.
 */
public class Human extends SawtoothUnitData {

    public static final String DESCRIPTION = "A regular human that moves forward changing left and right. Like a drunken lizard that is searching for truth in a garbage can.";
    public static final String NAME = "Human";

    public Human() {
        super(100, NAME, DESCRIPTION, 20, 6f);
    }
}
