package zombiecraft.player;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import zombiecraft.*;
import zombiecraft.human.Scout;
import zombiecraft.unit.GenericMovableUnit;
import zombiecraft.unit.MainBuilding;

import java.util.List;


/**
 * Created by David Park on 1/7/14.
 */
public class HumanPlayer extends Player {


    private boolean abilityUsed;
    private UnitData unitData;

    public HumanPlayer(Race race) {
        super(race);
        unitData = new Scout();
    }


    public void poll(GameModel gameModel, ViewModel viewModel) {
        if (getProductionDelay() <= 0)
            abilityUsed = false;
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            gameModel.getMainBuildingMap().get(this).setHealth(0);
            return;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            setSelection(0);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            setSelection(1);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
            setSelection(2);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
            setSelection(3);

        MainBuilding mainBuilding = gameModel.getMainBuildingMap().get(this);
        List<UnitData> unitDatas = mainBuilding.buildableUnits();
        if (getSelection() >= unitDatas.size())
            setSelection(Math.max(unitDatas.size() - 1, 0));
        if (getProductionDelay() <= 0) {
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                viewModel.setUnprojectPosition(Gdx.input.getX(), Gdx.input.getY());
                UnitData unitData = unitDatas.get(getSelection());
                GenericMovableUnit genericMovableUnit = new GenericMovableUnit(unitData);
                float x = mainBuilding.getX() + 32;
                float y = mainBuilding.getY() + 32;
                float angle = (float) Math.atan2(viewModel.getUnprojectY() - y, viewModel.getUnprojectX() - x) * MathUtils.radiansToDegrees;
                if (angle < 0)
                    angle += 360;
                genericMovableUnit.setDirection(angle);
                genericMovableUnit.setPosition(x - 32, y - 32);
                setProductionDelay(unitData.getProductionDelay());
                setProductionDelayLength(unitData.getProductionDelay());
                gameModel.addUnit(this, genericMovableUnit);
            }
        }


        if (Gdx.input.isKeyPressed(Input.Keys.Q) && abilityUsed == false) {
            if (race == Race.ZOMBIE) {
                setProductionDelay(getProductionDelay() + 200);
                setProductionDelayLength(getProductionDelayLength() + 200);
                abilityUsed = true;
                List<Unit> units = gameModel.getUnits();
                viewModel.setUnprojectPosition(Gdx.input.getX(), Gdx.input.getY());
                float x = mainBuilding.getX() + 32;
                float y = mainBuilding.getY() + 32;
                for (Unit unit1 : units) {
                    if (gameModel.getPlayerMap().get(unit1) == this && unit1 instanceof GenericMovableUnit) {
                        GenericMovableUnit genericMovableUnit = (GenericMovableUnit) unit1;
                        float angle =
                                (float) Math.atan2(viewModel.getUnprojectY() - y, viewModel.getUnprojectX() - x) *
                                        MathUtils.radiansToDegrees;
                        if (angle < 0)
                            angle += 360;
                        genericMovableUnit.setDirection(angle);
                    }
                }
            } else if (race == Race.HUMAN) {
                viewModel.setUnprojectPosition(Gdx.input.getX(), Gdx.input.getY());
                GenericMovableUnit genericMovableUnit = new GenericMovableUnit(unitData);
                abilityUsed = true;
                float x = mainBuilding.getX() + 32;
                float y = mainBuilding.getY() + 32;
                float angle = (float) Math.atan2(viewModel.getUnprojectY() - y, viewModel.getUnprojectX() - x) * MathUtils.radiansToDegrees;
                if (angle < 0)
                    angle += 360;
                genericMovableUnit.setDirection(angle);
                genericMovableUnit.setPosition(x - 32, y - 32);
                setProductionDelay(getProductionDelay() + 20);
                setProductionDelayLength(getProductionDelayLength() + 20);
                gameModel.addUnit(this, genericMovableUnit);
            }
        }
    }
}
