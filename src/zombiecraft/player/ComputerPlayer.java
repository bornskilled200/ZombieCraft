package zombiecraft.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import zombiecraft.*;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;


/**
 * Created by David Park on 1/7/14.
 */
public class ComputerPlayer extends Player {
    Queue<GenericMovableUnit> rememberedUnits;
    float degreeOfFreedom = 360;
    float currentDirection;

    public ComputerPlayer(Race race) {
        super(race);
        switch (race) {
            case ZOMBIE:
                break;
            default:
                Gdx.app.error("Computer", "ComputerPlayer does not support AI for " + race);
                break;
        }
        rememberedUnits = new ArrayDeque<GenericMovableUnit>();
    }

    public void poll(GameModel gameModel, ViewModel viewModel) {
        if (race != Race.ZOMBIE)
            return;
        boolean targetFound = false;
        List<Unit> units = gameModel.getUnits();
        for (Unit unit : units) {
            if (unit instanceof GenericMovableUnit && gameModel.getPlayerMap().get(unit) == this) {
                GenericMovableUnit genericMovableUnit = (GenericMovableUnit) unit;
                for (Unit other : units) {
                    if (unit == other || gameModel.getPlayerMap().get(unit) == gameModel.getPlayerMap().get(other))
                        continue;
                    int i = genericMovableUnit.getUnitData()
                            .doDamage(gameModel.getCurrentUpdate(), genericMovableUnit, other);
                    if (i > 0) {
                        if (rememberedUnits.contains(genericMovableUnit))
                            degreeOfFreedom *= .90;
                        else
                            degreeOfFreedom *= .98;
                        currentDirection = genericMovableUnit.getGeneralDirection() - degreeOfFreedom / 2;
                        targetFound = true;
                    }
                }
            }
        }
        if (targetFound == false && degreeOfFreedom < 360)
            degreeOfFreedom += .2f;


        MainBuilding mainBuilding = gameModel.getMainBuildingMap().get(this);

        if (getProductionDelay() <= 0)
            for (Unit unit : units) {
                if (gameModel.getPlayerMap().get(unit) != this) {
                    if (UnitData.isVisible(mainBuilding, unit)) {
                        float angle =
                                (float) Math.atan2(unit.getY() - mainBuilding.getY(), unit.getX() - mainBuilding.getX()) *
                                        MathUtils.radiansToDegrees;
                        if (angle < 0)
                            angle += 360;
                        for (Unit unit1 : units) {
                            if (gameModel.getPlayerMap().get(unit1) == this && unit1 instanceof GenericMovableUnit) {
                                GenericMovableUnit genericMovableUnit = (GenericMovableUnit) unit1;
                                genericMovableUnit.setDirection(angle);
                            }
                        }
                        setProductionDelay(200);
                        setProductionDelayLength(200);
                        break;
                    }
                }
            }


        if (getProductionDelay() <= 0) {
            UnitData unitData = mainBuilding.buildableUnits().get(0);
            setProductionDelayLength(unitData.getProductionDelay());
            setProductionDelay(unitData.getProductionDelay());

            GenericMovableUnit genericMovableUnit = new GenericMovableUnit(unitData);
            genericMovableUnit.setDirection((float) (currentDirection + (degreeOfFreedom * Math.random())) % 360);
            genericMovableUnit.setPosition(mainBuilding.getX(), mainBuilding.getY());
            rememberedUnits.offer(genericMovableUnit);
            if (rememberedUnits.size() > 6)
                rememberedUnits.poll();
            gameModel.addUnit(this, genericMovableUnit);
        }
    }
}
